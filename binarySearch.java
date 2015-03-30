import java.util.* ;
import java.io.* ;

public class binarySearch {
	public static void main(String[] args){
		int[] A = {5, 7, 7, 8, 8, 10};
		searchRange(A,8);
	}

	
	 /* Search a 2D Matrix
	 
	Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

		Integers in each row are sorted from left to right.
		The first integer of each row is greater than the last integer of the previous row.
		For example,

		Consider the following matrix:

		[
		  [1,   3,  5,  7],
		  [10, 11, 16, 20],
		  [23, 30, 34, 50]
		]
		Given target = 3, return true.
		
		这道题是二分查找Search Insert Position的题目，因为矩阵是行有序并且列有序，查找只需要先按行查找，定位出在哪一行之后再进行列查找即可，
		所以就是进行两次二分查找。时间复杂度是O(logm+logn)，空间上只需两个辅助变量，因而是O(1)，代码如下：
		二分查找是面试中出现频率不低的问题，但是很少直接考二分查找，会考一些变体，除了这道题，还有Search in Rotated Sorted Array和Search for a Range，
		思路其实差不多，稍微变化一下即可，有兴趣可以练习一下哈。
				*/
	public boolean searchMatrix(int[][] matrix, int target) {
	    if(matrix == null || matrix.length==0 || matrix[0].length==0)
	        return false;
	    int l = 0;
	    int r = matrix.length-1;
	    
	    while(l<=r)
	    {
	        int mid = (l+r)/2;
	        if(matrix[mid][0] == target) return true;
	        if(matrix[mid][0] > target)
	        {
	            r = mid-1;
	        }
	        else
	        {
	            l = mid+1;
	        }
	    }
	    //找到行之后找列
	    int row = r;
	    if(row<0)
	        return false;
	    l = 0;
	    r = matrix[0].length-1;
	    while(l<=r)
	    {
	        int mid = (l+r)/2;
	        if(matrix[row][mid] == target) return true;
	        if(matrix[row][mid] > target)
	        {
	            r = mid-1;
	        }
	        else
	        {
	            l = mid+1;
	        }
	    }   
	    return false;
	}	
	
	
	/*
	 * Implement int sqrt(int x).
Compute and return the square root of x.
这是一道数值处理的题目，和Divide Two Integers不同，这道题一般采用数值中经常用的另一种方法：二分法。基本思路是跟二分查找类似，
要求是知道结果的范围，取定左界和右界，然后每次砍掉不满足条件的一半，知道左界和右界相遇。算法的时间复杂度是O(logx)，空间复杂度是O(1)。代码如下：

实际面试遇到的题目可能不是对一个整数开方，而是对一个实数。方法和整数其实是一致的，只是结束条件换成左界和右界的差的绝对值小于某一个epsilon（极小值）即可。
这里注意一个小问题，就是在java中我们可以用==来判断两个double是否相等，而在C++中我们则需要通过两个数的绝对值差小于某个极小值来判断两个double的相等性。
实际上两个double因为精度问题往往是不可能每一位完全相等的，java中只是帮我们做了这种判定。
比较典型的数值处理的题目还有Divide Two Integers，Pow(x,n)等，其实方法差不多，一般就是用二分法或者以2为基进行位处理的方法。

	 */
	public int sqrt(int x) {
	    if(x<0) return -1;
	    if(x==0) return 0;
	    int l=1;
	    int r=x/2+1;
	    while(l<=r)
	    {
	        int m = (l+r)/2;
	        if(m<=x/m && x/(m+1)<m+1)
	            return m;
	        if(x/m<m)
	        {
	            r = m-1;
	        }
	        else
	        {
	            l = m+1;
	        }
	    }
	    return 0;
	}
	
	
/*Search for a Range 
 * Given a sorted array of integers, find the starting and ending position of a given target value.
Your algorithm's runtime complexity must be in the order of O(log n).
If the target is not found in the array, return [-1, -1].
For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
	这道题是二分查找Search Insert Position的变体，思路并不复杂，就是先用二分查找找到其中一个target，
	然后再往左右找到target的边缘。找边缘的方法跟二分查找仍然是一样的，只是切半的条件变成相等，
	或者不等（往左边找则是小于，往右边找则是大于）。这样下来总共进行了三次二分查找，
	所以算法的时间复杂度仍是O(logn)，空间复杂度是O(1)。 代码如下：
	*/
	
	//by discuss 
	/*NOTE: 下面这个实现，也是分别用binary search去找lowerbound, higher bound, 但找higher bound时候，找的是target + 1
	 * basically it is the same idea with using separate binary search for left and right bounds. 
	 * The good point here is the lower_bound and the search for (target+1)
	 */
	   public static int[] searchRange(int[] A, int target) {
	        int start = firstGreaterEqual(A, target);
	        if (start == A.length || A[start] != target) {
	            return new int[]{-1, -1};
	        }
	        return new int[]{start, firstGreaterEqual(A, target + 1) - 1};
	    }
	   /*
	    * Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
	    */

	    //find the first number that is greater than or equal to target.
	    //could return A.length if target is greater than A[A.length-1].
	    //actually this is the same as lower_bound in C++ STL.
	    private static int firstGreaterEqual(int[] A, int target) {
	        int low = 0, high = A.length;
	        while (low < high) {
	            int mid = low + ((high - low) >> 1);
	            //low <= mid < high
	            if (A[mid] < target) {
	                low = mid + 1;
	            } else {
	                //should not be mid-1 when A[mid]==target.
	                //could be mid even if A[mid]>target because mid<high.
	                high = mid;
	            }
	        }
	        return low;
	    }
	    
    public int[] searchRange2(int[] A, int target) {
        int[] res = new int[2];
        res[0] = res[1] = -1;
        //find the first index
        res[0] = findIdx(A, 0, A.length -1,target,true);
        //find the second index 
        res[1] = findIdx(A, 0 , A.length -1,target,false);
        return res;
    }
    private int findIdx(int[] A,int l, int r,int target,boolean isLower){
        while(l<r){
        int m = (l+r)/2;
        if(target < A[m]) r = m-1;
        else if(target > A[m]) l = m+1;
        else {
            if(isLower){
                r = m; // go left
            }
            else{
                if(m == l) {// only two elems
                    return (A[r] == target) ? r : l;
                }
                l = m; // go right
            }
        }
        }
       return (A[l]==target) ? l : -1;
        
    }
    
    
	
	
	
	/*Find Minimum in Rotated Sorted Array
	 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.
You may assume no duplicate exists in the array.
	 */
	
	/*Search in Rotated Sorted Array II
	 * Search in Rotated Sorted Array II Total Accepted: 27188 Total Submissions: 86824 My Submissions Question Solution 
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?
Would this affect the run-time complexity? How and why?
Write a function to determine if a given target is in the array.
	 */
	

	
	
	/*
	 * Search in Rotated Sorted Array Total Accepted: 41117 Total Submissions: 142288 My Submissions Question Solution 
Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.
	 * 这道题是二分查找Search Insert Position的变体，看似有点麻烦，其实理清一下还是比较简单的。因为rotate的缘故，当我们切取一半的时候可能会出现误区，所以我们要做进一步的判断。具体来说，假设数组是A，每次左边缘为l，右边缘为r，还有中间位置是m。在每次迭代中，分三种情况：
（1）如果target==A[m]，那么m就是我们要的结果，直接返回；
（2）如果A[m]<A[r]，那么说明从m到r一定是有序的（没有受到rotate的影响），那么我们只需要判断target是不是在m到r之间，如果是则把左边缘移到m+1，否则就target在另一半，即把右边缘移到m-1。
（3）如果A[m]>=A[r]，那么说明从l到m一定是有序的，同样只需要判断target是否在这个范围内，相应的移动边缘即可。
根据以上方法，每次我们都可以切掉一半的数据，所以算法的时间复杂度是O(logn)，空间复杂度是O(1)。代码如下：

注意在这道题中我们假设了这个数组中不会出现重复元素。如果允许出现重复元素，那么我们要对中间和边缘的相等的情况继续处理，进而影响到时间复杂度，
这个问题会在Search in Rotated Sorted Array II中具体讨论，大家有兴趣可以看看。
	 */
	public int searchRotatedSortedArray(int[] A, int target) {
	    if(A==null || A.length==0)
	        return -1;
	    int l = 0;
	    int r = A.length-1;
	    while(l<=r)
	    {
	        int m = (l+r)/2;
	        if(target == A[m]) //如果target==A[m]，那么m就是我们要的结果，直接返回；
	            return m;
	        //如果A[m]<A[r],那么说明从m到r一定是有序的（没有受到rotate的影响），
	        //那么我们只需要判断target是不是在m到r之间，如果是则把左边缘移到m+1，否则就target在另一半，即把右边缘移到m-1。
	        if(A[m]<A[r])
	        {
	            if(target>A[m] && target<=A[r])
	                l = m+1;
	            else
	                r = m-1;
	        }
	        //如果A[m]>=A[r]，那么说明从l到m一定是有序的，同样只需要判断target是否在这个范围内，相应的移动边缘即可。
	        else
	        {
	            if(target>=A[l] && target<A[m])
	                r = m-1;
	            else
	                l = m+1;                    
	        }
	    }
	    return -1;
	}
	
	
	/*
	 * 这道题比较简单，就是二分查找。思路就是每次取中间，如果等于目标即返回，否则根据大小关系切去一半。因此算法复杂度是O(logn)，空间复杂度O(1)。代码如下： 
	 * 注意以上实现方式有一个好处，就是当循环结束时，如果没有找到目标元素，那么l一定停在恰好比目标大的index上，
	 * r一定停在恰好比目标小的index上，所以个人比较推荐这种实现方式。
二分查找是一个非常经典的方法，不过一般在面试中很少直接考二分查找，会考一些变体，
例如Search in Rotated Sorted Array，Search for a Range和Search a 2D Matrix，思路其实是类似的，稍微变体一下即可，有兴趣可以练习一下哈。
	 */
	//by code ganker
	public int searchInsert(int[] A, int target) {
	    if(A == null || A.length == 0)
	    {
	        return 0;
	    }
	    int l = 0;
	    int r = A.length-1;
	    while(l<=r)
	    {
	        int mid = (l+r)/2;
	        if(A[mid]==target)
	            return mid;
	        if(A[mid]<target)
	            l = mid+1;
	        else
	            r = mid-1;
	    }
	    return l;
	}
	
	
}