# # Matrix Factorization based on spark
# Author: liping xiong

from pyspark import SparkContext
import logging
import numpy as np
import sys
import os
import datetime

# usage
if len(sys.argv) != 9:
    print 'Usage: spark-submit dsgd_mf.py <num_factors> <num_workers> <num_iterations> \
    <beta_value> <lambda_value> \
    <inputV_filePath> <outputW_filepath> <outputH_filepath>'
    sys.exit(-1)

sc = SparkContext(appName="DSGD_MF")
num_factors=int(sys.argv[1])
num_workers=int(sys.argv[2])
num_iterations=int(sys.argv[3])
beta=float(sys.argv[4])
lamb=float(sys.argv[5])
in_Vfile=sys.argv[6]
out_Wfile=sys.argv[7]
out_Hfile=sys.argv[8]
tempdir="./"

starttime = datetime.datetime.now()

filePath = os.path.join(tempdir, in_Vfile)
lines = sc.textFile(filePath)
# arrRdd is the RDD with data format of an array [i,j,rating]
arrRdd=lines.map(lambda line : map(int,line.split(','))).cache()
# get list of Ni and Nj
Ni_li=arrRdd.map(lambda x : (x[0],1)).reduceByKey(lambda x,y:x+y).collect()
Nj_li=arrRdd.map(lambda x : (x[1],1)).reduceByKey(lambda x,y:x+y).collect()
# get max user id and max movie id
maxUid=arrRdd.map(lambda x : x[0]).reduce(lambda x,y:max(x,y))
maxMid=arrRdd.map(lambda x : x[1]).reduce(lambda x,y:max(x,y))

# initialize W and H by random floats. W: M*F, H: F*N
matrixW= np.random.random((maxUid,num_factors)) 
matrixH= np.random.random((num_factors,maxMid))
# transform the matrixW and matrixH to dict for the later use;{i:W[i]},{j:H[j]}
W_dict={}
H_dict={}
for i in range(maxUid):
	W_dict[i+1]=matrixW[i]
for j in range(maxMid):
	H_dict[j+1]=matrixH.T[j]
# transform the list of Ni and Nj to dict for the later use, {i:Ni},{j:Nj}
Ni_dict={}
Nj_dict={}
for e in Ni_li:
	Ni_dict[e[0]]=e[1]
for e in Nj_li:
	Nj_dict[e[0]]=e[1]
# divide the
liColInterval = []
blockColSize = int( (1+maxUid) / num_workers)
for i in xrange(num_workers):
	if i == num_workers -1:
		liColInterval.append((blockColSize*i,maxUid))
	else:
		liColInterval.append((blockColSize*i,blockColSize*(i+1)-1))

# broadcast variables
Nibd=sc.broadcast(Ni_dict)
Njbd=sc.broadcast(Nj_dict)
liColIntervalbd=sc.broadcast(liColInterval)

def sgd(idx,D):
    # get W ,H
	W=Wbd.value
	H=Hbd.value
    # get Ni, Nj
	Ni=Nibd.value
	Nj=Njbd.value
	mCnt=mCntbd.value
    # get the permutation sequence for this partition
	permArr=permbd.value
	perm=permArr[idx]

    # this two dicts are used to store the updated W_I and H_J
	WI_new={}
	HJ_new={}
    # calculate step size
	T0=500
	T=Tbd.value
	epsilon = (T0 + mCnt + T)**(-beta)
    
	curM=0
    liColInterval=liColIntervalbd.value
	for d in D:
		i=int(d[1][0])		
		j=int(d[1][1])
		rating=float(d[1][2])
		#if(j in J):
		if(j>=liColInterval[perm][0] and j<= liColInterval[perm][1]):
			dotV=np.dot(W[i],H[j])
			eij = rating - dotV
			logW=-2*eij * H[j] + 2*lamb/Ni[i] * W[i]
			logH=-2*eij * W[i] + 2*lamb/Nj[j] * H[j]
			WI_new[i]=W[i] - epsilon*logW
			HJ_new[j]=H[j] - epsilon*logH
			curM+=1
	return [('w',WI_new),('h',HJ_new),('m',curM)]

def partFunc(key):
	block_rowsize = int( maxUid + 1) / num_workers
	return key/block_rowsize
partRdd=arrRdd.map(lambda x:(x[0],x)).partitionBy(num_workers,partFunc).cache()
#begin iteration
m=0
for t in range(num_iterations):
	Wbd=sc.broadcast(W_dict)
	Hbd=sc.broadcast(H_dict)
	#permutation
	permArr=np.random.permutation(num_workers)
	#mCntbd=sc.broadcast( (mTot/num_workers) * (t+1) )
	#Tbd=sc.broadcast(mTot/(num_workers*num_workers)  )
	mCntbd=sc.broadcast(m)
	Tbd=sc.broadcast(t)
	permbd=sc.broadcast(permArr)
	#broadcase W,H,permutation
	newWH=partRdd.mapPartitionsWithIndex(sgd,True).collect()
	for e in newWH:
		#logging.debug("e[0=]%s"%e.type)
		if(e[0]=='w'):
			W_dict.update(e[1])
		elif(e[0]=='h'):
			H_dict.update(e[1])
		elif(e[0]=='m'):
			m+=e[1]
endtime = datetime.datetime.now()
#print "%s"%(endtime-starttime).seconds

#loss
V=np.zeros((maxUid,maxMid))
#print "maxUid=%d,maxMid=%d"%(maxUid,maxMid)
file = open(filePath)
L=0
for line in file:
	i,j,rating=map(int,line.split(','))
	V[i-1][j-1]=float(rating)
	L+=(rating- np.inner(W_dict[i],H_dict[j] )  )**2
#print "loss=%s"% L
print L


"""
#output
outw = open(out_Wfile, 'w+')
outh = open(out_Hfile, 'w+')
for i in range(maxUid):
	i=i+1
	if(W_dict.has_key(i)):
		newList=[str(x) for x in W_dict[i]]
		s=','.join(newList)
		outw.write(s+'\n')
	else:
		newList=['0']*num_factors
		outw.write(','.join(newList)+'\n')
	
tmp=matrixH.T
for j in range(maxMid):
	if(H_dict.has_key(j+1)):
		tmp[j]=H_dict[j+1]
		#print H_dict[j+1]
	else:
		tmp[j]=[0]*num_factors
final=tmp.T
for j in range(num_factors):
	newList=[str(x) for x in final[j]]
	s=','.join(newList)
	#print s
	outh.write(s+'\n')



