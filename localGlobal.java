import java.io.*;
import java.util.*;

public class localGlobal {
	
	public static void main(String[] args){
		int A[]={2,3,1,1,4};
		jump(A);
		//int A[] ={ 1,1,1,1,1};
		boolean ret = canJump(A);
	}
	
	/*
	 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Your goal is to reach the last index in the minimum number of jumps.
For example:
Given array A = [2,3,1,1,4]
The minimum number of jumps to reach the last index is 2. 
(Jump 1 step from index 0 to 1, then 3 steps to the last index.)
这道题是Jump Game的扩展，区别是这道题不仅要看能不能到达终点，而且要求到达终点的最少步数。其实思路和Jump Game还是类似的，
只是原来的全局最优现在要分成step步最优和step-1步最优（假设当前步数是step）。
当走到超过step-1步最远的位置时，说明step-1不能到达当前一步，我们就可以更新步数，将step+1。时间复杂度仍然是O(n)，空间复杂度也是O(1)。
	 */
	public static int jump(int[] A) {
	    if(A==null || A.length==0)
	        return 0;
	    int lastReach = 0;
	    int reach = 0;
	    int step = 0;
	    for(int i=0;i<=reach&&i<A.length;i++)
	    {
	    	System.out.println('\n'+"i="+i);
	        if(i>lastReach)
	        {
	            step++;
	            lastReach = reach;
	            System.out.println("lastReach="+lastReach+" i="+i + " step="+step );
	            
	        }
	        reach = Math.max(reach,A[i]+i);
	        System.out.println("reach="+reach);
	    }
	    if(reach<A.length-1)
	        return 0;
	    return step;
	}
	
	/*
	 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Determine if you are able to reach the last index.
For example:
A = [2,3,1,1,4], return true.
A = [3,2,1,0,4], return false.
	这道题是动态规划的题目，所用到的方法跟是在Maximum Subarray中介绍的套路，用“局部最优和全局最优解法”。
	我们维护一个到目前为止能跳到的最远距离，以及从当前一步出发能跳到的最远距离。局部最优local=A[i]+i，
	而全局最优则是global=Math.max(global, local)。递推式出来了，代码就比较容易实现了。
	因为只需要一次遍历时间复杂度是O(n)，而空间上是O(1)。
	*/
    public static boolean canJump(int[] A) {
        int local = 0+A[0];
        int global= local;
        if(global>=A.length-1) return true;
        // global is the max idx that can be reached. local: max idx can be reached from cur index i.
        //if global can reach the array's end, then true; 
        for(int i=1;i<A.length&&i<=global;i++){            
            local = i+A[i];
            global=Math.max(global,local);
            if(global>=A.length-1) return true;
        }
        return false;
    }
    
    
    /*  Maximum Product Subarray 
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
    */
    public int maxProduct(int[] A) {
        int minNeg=A[0];int local=A[0];int global=A[0];
        
        for(int i=1;i<A.length;i++){
            int tmp=local;
            local=Math.max(Math.max(tmp*A[i],minNeg*A[i]),A[i] );
            minNeg=Math.min(Math.min(tmp*A[i],minNeg*A[i]),A[i]);
            
            global=Math.max(local,global);
        }
        return global;
        
    }
	/*
	Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
	For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
	the contiguous subarray [4,−1,2,1] has the largest sum = 6.
	*/
    /*
    -2,1,-3,4,-1,2,1,-5
    l=-1,-2,4,5,,
    g=-1,-1,1,3,3,
    [-1], 
    这是一道非常经典的动态规划的题目，用到的思路我们在别的动态规划题目中也很常用，以后我们称为”局部最优和全局最优解法“。
基本思路是这样的，在每一步，我们维护两个变量，一个是全局最优，就是到当前元素为止最优的解是，一个是局部最优，就是必须包含当前元素的最优的解。
接下来说说动态规划的递推式（这是动态规划最重要的步骤，递归式出来了，基本上代码框架也就出来了）。
假设我们已知第i步的global[i]（全局最优）和local[i]（局部最优），那么第i+1步的表达式是：
local[i+1]=Math.max(A[i], local[i]+A[i])，就是局部最优是一定要包含当前元素，所以不然就是上一步的局部最优local[i]+当前元素A[i]
（因为local[i]一定包含第i个元素，所以不违反条件），但是如果local[i]是负的，那么加上他就不如不需要的，所以不然就是直接用A[i]；
global[i+1]=Math(local[i+1],global[i])，有了当前一步的局部最优，那么全局最优就是当前的局部最优或者还是原来的全局最优
（所有情况都会被涵盖进来，因为最优的解如果不包含当前元素，那么前面会被维护在全局最优里面，如果包含当前元素，那么就是这个局部最优）。

接下来我们分析一下复杂度，时间上只需要扫描一次数组，所以时间复杂度是O(n)。
空间上我们可以看出表达式中只需要用到上一步local[i]和global[i]就可以得到下一步的结果，
所以我们在实现中可以用一个变量来迭代这个结果，不需要是一个数组，也就是如程序中实现的那样，所以空间复杂度是两个变量（local和global），即O(2)=O(1)。
代码如下： 
    */
    
    public int maxSubArray(int[] A) {
        int local =A[0];
        int global= A[0];
        
        for(int i=1;i<A.length;i++){
            local = Math.max(local+A[i],A[i]);//就是必须包含当前元素的最优的解,如果local[i]是负的，那么加上他就不如不需要的，所以不然就是直接用A[i]
            global=Math.max(local,global);
        }
        return global;
    }
    /*
     * 这道题虽然比较简单，但是用到的动态规划方法非常的典型，我们在以后的题目中还会遇到，
     * 大家还是要深入理解一下哈。我现在记得的用到的题目是Jump Game，以后有统计一下再继续更新。
     */
}