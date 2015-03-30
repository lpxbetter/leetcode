import java.io.*;
import java.util.*;
//import commLib.*;

public class arrayRelated {
	public static void main(String[] args){
		StringBuilder res = new StringBuilder();
		res.append("hello".charAt(0));
		//System.out.println(res);
		int[] A={1,1,1,2,2};
		//int idx=removeDuplicates(A);
		//System.out.println(idx);
		int[][] m={{1,1,1,1},{1,1,0,1},{1,1,1,1},{0,1,0,0}};
		//setZeroes(m);
		//longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
		//threeSum(new int[] {-1,1,0,2,-1,-4});
		twoSum(new int[]{3,2,4},6);
	}
	
    public static int[] twoSum(int[] numbers, int target) {
        Arrays.sort(numbers);
        int l=0;
        int r=numbers.length-1;
       
        while(l<r){
            int x=numbers[l]+numbers[r];
            if(x==target) {
                return new int[]{l+1,r+1};
            }
            else if(x>target){
                r--;
            }
            else {
                l++;
            }
        }
       return null; 
    }
    
	//注意，在这里，输出结果改成了满足相加等于target的两个数，而不是他们的index。因为要排序，如果要输出index，需要对原来的数的index进行记录，方法是构造一个数据结构，包含数字的值和index，然后排序。所以从这个角度来看，这道题第二种解法并没有第一种解法好，空间也是O(n). 在LeetCode原题中是假设结果有且仅有一个的，一般来说面试时会要求出所有的结果，这个时候会涉及到重复pair的处理，相关的内容会在3Sum那道题目中涉及到，请参见3Sum -- LeetCode.
	/*
	 * 3Sum Total Accepted: 39301 Total Submissions: 234400 My Submissions Question Solution 
	Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
	Find all unique triplets in the array which gives the sum of zero.
	Note:
	Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
	The solution set must not contain duplicate triplets.
	    For example, given array S = {-1 0 1 2 -1 -4},

	    A solution set is:
	    (-1, 0, 1)
	    (-1, -1, 2)
	 */
	/*这道题是Two Sum的扩展，brute force时间复杂度为O(n^3), 对每三个数进行比较。这道题和Two Sum有所不同，使用哈希表的解法并不是很方便，
	 * 因为结果数组中元素可能重复，如果不排序对于重复的处理将会比较麻烦，因此这道题一般使用排序之后夹逼的方法，
	 * 总的时间复杂度为O(n^2+nlogn)=(n^2),空间复杂度是O(n),代码如下：
	*/
	
	public static ArrayList<ArrayList<Integer>> threeSum(int[] num)  
	{  
	    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
	    if(num==null || num.length<=2)  
	        return res;  
	    Arrays.sort(num);  
	    //
	    for(int i=num.length-1;i>=2;i--) 
	    {  
	    	//skip same elements
	        if(i<num.length-1 && num[i]==num[i+1])  
	            continue;  
	         //num[i], 从剩下的0 - (i-1)  中找 -num[i], two sum. 
	         ArrayList<ArrayList<Integer>> curRes = twoSumFor3Sum(num,i-1,-num[i]);  
	         //比如num[i] =2, 找到curRes= [[-1,-1] ], 把num[i] 加入，得到 [[-1,-1,2] ]
	         for(int j=0;j<curRes.size();j++)  
	         {  
	             curRes.get(j).add(num[i]);  
	         }
	         
	         res.addAll(curRes);  
	    }  
	    return res;  
	}  

	private static ArrayList<ArrayList<Integer>> twoSumFor3Sum(int[] num, int end,int target)  
	{  
	    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
	    if(num==null || num.length<=1)  
	        return res;  
	    int l = 0;  
	    int r = end;  
	    while(l<r)  
	    {  
	        if(num[l]+num[r]==target)  
	        {  
	            ArrayList<Integer> item = new ArrayList<Integer>();  
	            item.add(num[l]);  
	            item.add(num[r]);  
	            res.add(item);  
	            l++;  
	            r--;  
	            while(l<r&&num[l]==num[l-1])  
	                l++;  
	            while(l<r&&num[r]==num[r+1])  
	                r--;  
	        }  
	        else if(num[l]+num[r]>target) //太大了，往左边走,r--
	        {  
	            r--;  
	        }  
	        else  
	        {  
	            l++;  
	        }  
	    }  
	    return res;  
	}  

	/*注意，在这里为了避免重复结果，对于已经判断过的数会skip掉，这也是排序带来的方便。 
	 * 这道题考察的点其实和Two Sum差不多，Two Sum是3Sum的一个subroutine, 不过更加综合一些，实现上更加全面，需要注意细节，面试中比较常见的一道题。
	 * 此题更加复杂的扩展是4Sum，请参见4Sum -- LeetCode。
	*/
	
	public static int longestConsecutive(int[] num) {
	    if(num == null || num.length == 0)
	        return 0;
	    HashSet<Integer> set = new HashSet<Integer>();
	    int res = 1;
	    for(int i=0;i<num.length;i++)
	    {
	        set.add(num[i]);
	    }
	    while(!set.isEmpty())
	    {
	        Iterator iter = set.iterator();
	        int item = (Integer)iter.next();
	        set.remove(item);
	        int len = 1;
	        int i = item-1;
	        while(set.contains(i))
	        {
	            set.remove(i--);
	            len++;
	        }
	        i = item+1;
	        while(set.contains(i))
	        {
	            set.remove(i++);
	            len++;
	        }
	        if(len>res)
	            res = len;
	    }
	    return res;
	}
	
	public static void setZeroes(int[][] matrix) {
	    if(matrix==null || matrix.length==0 || matrix[0].length==0)
	        return;
	    boolean rowFlag = false;
	    boolean colFlag = false;
	    for(int i=0;i<matrix.length;i++)
	    {
	        if(matrix[i][0]==0)
	        {
	            colFlag = true;
	            break;
	        }
	    }
	    for(int i=0;i<matrix[0].length;i++)
	    {
	        if(matrix[0][i]==0)
	        {
	            rowFlag = true;
	            break;
	        }
	    }      
	    for(int i=1;i<matrix.length;i++)
	    {
	        for(int j=1;j<matrix[0].length;j++)
	        {
	            if(matrix[i][j]==0)
	            {
	                matrix[i][0] = 0;
	                matrix[0][j] = 0;
	            }
	        }
	    }
	    for(int i=1;i<matrix.length;i++)
	    {
	        for(int j=1;j<matrix[0].length;j++)
	        {
	            if(matrix[i][0]==0 || matrix[0][j]==0)
	                matrix[i][j] = 0;
	        }
	    }
	    if(colFlag)
	    {
	        for(int i=0;i<matrix.length;i++)
	        {
	            matrix[i][0] = 0;
	        }
	    }
	    if(rowFlag)
	    {
	        for(int i=0;i<matrix[0].length;i++)
	        {
	            matrix[0][i] = 0;
	        }
	    }
	}
	
	
	
public static int removeDuplicates(int[] A) {
    if(A==null || A.length==0)
        return 0;
    int idx = 0;
    int count = 0;
    for(int i=0;i<A.length;i++)
    {
        if(i>0 && A[i]==A[i-1])
        {
            count++;
            if(count>=3)
                continue;
        }
        else
        {
            count = 1;
        }
        A[idx++]=A[i];
    }
    return idx;
}

}