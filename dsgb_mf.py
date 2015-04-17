from pyspark import SparkContext 
import logging
import numpy as np
#from pyspark import *
import sys
import os
#from common import *

"""
spark-submit dsgd_mf.py <num_factors> <num_workers> <num_iterations> \
		<beta_value> <lambda_value> \
		<inputV_filepath> <outputW_filepath> <outputH_filepath>

spark-submit dsgd_mf.py 100 10 50 0.8 1.0 test.csv w.csv h.csv
"""

#The typical solution would be mapPartitions so for each worker the function you give mapPartitions is executed in parallel but inside each worker you define a method that sequentially performs the sgd updates within an active block
sc = SparkContext(appName="PythonPi")
num_factors=int(sys.argv[1])
num_workers=int(sys.argv[2])
num_iterations=int(sys.argv[3])
beta=float(sys.argv[4])
lamb=float(sys.argv[5])
in_Vfile=sys.argv[6]
out_Wfile=sys.argv[7]
out_Hfile=sys.argv[8]
tempdir="./"
#file=sys.argv[8]

path = os.path.join(tempdir, in_Vfile)
V = sc.textFile(path)
#logging.debug( "count=%d"% V.count())

#tmpRdd=V.map(lambda line : (line.split(",")[0],line.split(",")[1], line.split(",")[2]) ).cache()
arrRdd=V.map(lambda line : map(int,line.split(','))).cache()
Ni_li=arrRdd.map(lambda x : (x[0],1)).reduceByKey(lambda x,y:x+y).collect()
Nj_li=arrRdd.map(lambda x : (x[1],1)).reduceByKey(lambda x,y:x+y).collect()
maxRow=arrRdd.map(lambda x : x[0]).reduce(lambda x,y:max(x,y))
maxCol=arrRdd.map(lambda x : x[1]).reduce(lambda x,y:max(x,y))

block_rowsize=maxRow/num_workers
block_colsize=maxCol/num_workers
#W: M*F, H: F*N
matrixW= np.random.random((maxRow,num_factors)) 
matrixH= np.random.random((num_factors,maxCol)) 
W_dict={}
H_dict={}
for i in range(maxRow):
	W_dict[i+1]=matrixW[i]
for j in range(maxCol):
	H_dict[j+1]=matrixH.T[j]
mTot=0
Ni_dict={}
Nj_dict={}
for e in Ni_li:
	Ni_dict[e[0]]=e[1]
	mTot+=e[1]
for e in Nj_li:
	Nj_dict[e[0]]=e[1]

Nibd=sc.broadcast(Ni_dict)
Njbd=sc.broadcast(Nj_dict)
mPerStrata=0
#for e in Ni_li:
#	mPerStrata+=Ni_li[]

def sgd(idx,D):
	key=idx
	#logging.debug( "key=%s"%key)
	W=Wbd.value
	H=Wbd.value
	Ni=Nibd.value
	Nj=Njbd.value
	mCnt=mCntbd.value
	permArr=permbd.value
	perm=permArr[key]
	J=[]
	for j in range(block_colsize):
		J.append(block_colsize*perm+j+1)
	WI_new={}
	HJ_new={}
	T0=100
	T=Tbd.value
	epsilon = (100 + mCnt + T)**(-beta)
	loss=0
	for d in D:
		#print d
		i=int(d[1][0])		
		j=int(d[1][1])
		rating=float(d[1][2])
		if(j in J):
			dotV=np.dot(W[i],H[j])
			eij = rating - dotV
			logW=-2*eij * H[j] + 2*lamb/Ni[i] * W[i]
			logH=-2*eij * W[i] + 2*lamb/Nj[j] * H[j]
			WI_new[i]=W[i] - epsilon*logW
			HJ_new[j]=H[j] - epsilon*logH
			loss+=eij
	return [('w',WI_new),('h',HJ_new),('l',loss)]

def partFunc(key):
	return key/block_rowsize
partRdd=arrRdd.map(lambda x:(x[0],x)).partitionBy(num_workers,partFunc).cache()
#begin iteration
mBef=0
for t in range(num_iterations):
	Wbd=sc.broadcast(W_dict)
	Hbd=sc.broadcast(H_dict)
	#permutation
	permArr=np.random.permutation(num_workers)
	mCntbd=sc.broadcast( (mTot/num_workers) * (t+1) )
	Tbd=sc.broadcast(mTot/(num_workers*num_workers)  )
	permbd=sc.broadcast(permArr)
	#broadcase W,H,permutation
	newWH=partRdd.mapPartitionsWithIndex(sgd,True).collect()
	for e in newWH:
		#logging.debug("e[0=]%s"%e.type)
		if(e[0]=='w'):
			W_dict.update(e[1])
		if(e[0]=='h'):
			H_dict.update(e[1])

#output
outw = open(out_Wfile, 'w+')
outh = open(out_Hfile, 'w+')
for i in range(maxRow):
	i=i+1
	if(W_dict.has_key(i)):
		newList=[str(x) for x in W_dict[i]]
		s=','.join(newList)
		outw.write(s+'\n')
	else:
		newList=['0']*num_factors
		outw.write(','.join(newList)+'\n')
	
tmp=matrixH.T
for j in range(maxCol):
	if(H_dict.has_key(j+1)):
		tmp[j]=H_dict[j+1]
		print H_dict[j+1]
	else:
		tmp[j]=[0]*num_factors
final=tmp.T
for j in range(num_factors):
	newList=[str(x) for x in final[j]]
	s=','.join(newList)
	print s
	outh.write(s+'\n')


	







