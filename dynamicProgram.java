import java.io.*;
import java.util.*;

public class dynamicProgram {
	
	public static void main(String[] args){
		String s=new String() ;
		ArrayList<StringBuilder> res = new ArrayList<StringBuilder>();
		int[] A={2,3,1,1,4};
		//jump(A);
		//numDecodings("126");
		
		//S = "rabbbit", T = "rabbit" return 3
		//String S = "abbbi"; String T = "ai"; //rab,bit,bbit,
		//System.out.println("rst="+numDistinct(S,T));
		//System.out.println(maxSubArr(new int[]{-1,2,-2,4}));
		//System.out.println(LCSeq("abcdef","acbcf"));
		//System.out.println(LCStr("abcdef","acbcdf"));
//		System.out.println(LCStr("abcdef","ab"));
	//	System.out.println( LIS(new int[]{2,6,5}) ) ;
		System.out.println(minJump(new int[]{2,1,1,1}) );
	}
	
	public int lp(String s){
		int n=s.length();
		boolean[][] t=new boolean[n][n];
		
		for(int i=n-1;i>=0;i--){
			for(int j=i;j<n;j++){
				if(s.charAt(i)==s.charAt(j)) {
					if(j-i<=2 || t[i+1][j-1]==true ){
						t[i][j] = true;
					}
				}
			}
		}
	}
	
	public int maxSubarr(int[] A){
		int maxSum=0;
		int pre=0;
		int n=A.length;
		for(int i=0;i<n;i++){
			pre=Math.max(pre+A[i], A[i]);
			if(pre>maxSum) maxSum=pre;
		}
		return maxSum;
	}
	
	
	// T[] = T[]+1
	public static int minJump(int A[]){
		int n=A.length;
		int[] T=new int[n];
		for(int i=1;i<n;i++){
			for(int j=0;j<i;j++){
				if(j+A[j]>=i) {
					T[i]=T[j]+1;
					break;
				}
			}
		}
		return T[n-1];
	}
	
	//longest increasing sequence
	public static int LIS(int[] A){
		// 
		int n=A.length ;
		int[] T=new int[n];
		for(int i=0;i<n;i++){
			T[i]=1;
		}
		for(int i=1;i<n;i++){
			for(int j=0;j<i;j++){
				if(A[j]<=A[i] && T[i]<T[j]+1){
					T[i]=T[j]+1;
				}
			}
		}
		int maxLen=0;
		for(int k=0;k<n;k++){
			if(T[k]>maxLen) maxLen=T[k];
		}
		return maxLen;
	}
	
	public static int LCStr(String s1, String s2){
		int m = s1.length();
		int n = s2.length();
		int maxLen=0;
		int[][] T = new int[m + 1][n + 1]; 
		
		for(int i=0;i<=m;i++){
			for(int j=0;j<=n;j++){
				if(i==0 || j==0) T[i][j] = 0;
				else if(s1.charAt(i-1) == s2.charAt(j-1)){
					T[i][j] = T[i-1][j-1] + 1;
					maxLen=Math.max(maxLen,T[i][j]);
				}
				else{
					T[i][j] = 0;
				}
			}
		}
		return  maxLen;
	}
	
	/*Longest common subsequence
	if(s1[i]==s2[j]) T[i][j] = T[i-1][j-1] + 1
	else T[i][j] = Math.max(T[i-1][j], T[i][j-1])
	*/
	public static int LCSeq(String s1, String s2){
		int m = s1.length();
		int n = s2.length();
		int[][] T = new int[m + 1][n + 1]; 
		
		for(int i=0;i<=m;i++){
			for(int j=0;j<=n;j++){
				if(i==0 || j==0) T[i][j] = 0;
				else if(s1.charAt(i-1) == s2.charAt(j-1)){
					T[i][j] = T[i-1][j-1] + 1;
				}
				else{
					T[i][j] = Math.max(T[i-1][j], T[i][j-1]);
				}
			}
		}
		return  T[m][n];
	}
	
	
	// 
	public static int maxSubArr(int[] A){
		int maxSum = A[0];
		int maxByI = A[0];
		for(int i=1;i<A.length;i++){
			maxByI = Math.max(maxByI+A[i],A[i]);
			maxSum = Math.max(maxSum, maxByI);
			System.out.println(maxByI +" "+ maxSum);
		}
		return maxSum;
	}
	
	//
	public static int numDistinct(String S, String T) {
		System.out.println("S.length()="+S.length()+" T.length()="+T.length());
	    if(T.length()==0)
	    {
	        return 1;
	    }
	    if(S.length()==0)
	        return 0;
	    int[] res = new int[T.length()+1];
	    res[0] = 1;
	    com.ptrArr(res);
	    for(int i=0;i<S.length();i++)
	    {
	        for(int j=T.length()-1;j>=0;j--)
	        {
	        	System.out.println("i="+i+" j="+j + " S.charAt(i)"+S.charAt(i)+" T.charAt(j)"+T.charAt(j));
	        	System.out.println("bef:res[j]="+res[j]+" res[j+1]="+res[j+1]);
	            //res[j+1] = (S.charAt(i)==T.charAt(j) ? res[j] : 0) + res[j+1];
	            if(S.charAt(i)==T.charAt(j)){
	            	res[j+1]=res[j]+res[j+1];
	            }
	            System.out.println("aft:res[j]="+res[j]+" res[j+1]="+res[j+1]);
	        }
	    }
	    return res[T.length()];
	}
	
	
	public static int jump(int[] A) {
	    if(A==null || A.length==0)
	        return 0;
	    int lastReach = 0;
	    int reach = 0;
	    int step = 0;
	    for(int i=0;i<=reach&&i<A.length;i++)
	    {
	        if(i>lastReach)
	        {
	            step++;
	            lastReach = reach;
	        }
	        reach = Math.max(reach,A[i]+i);
	    }
	    if(reach<A.length-1)
	        return 0;
	    return step;
	}
	

	
	/*
	 * Climbing Stairs Total Accepted: 39367 Total Submissions: 115445 My Submissions Question Solution 
You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
	 * 这道题目是求跑楼梯的可行解法数量。每一步可以爬一格或者两个楼梯，可以发现，递推式是f(n)=f(n-1)+f(n-2)，
	 * 也就是等于前一格的可行数量加上前两格的可行数量。熟悉的朋友可能发现了，这个递归式正是斐波那契数列的定义，不熟悉的朋友可以看看Wiki - 斐波那契数列。
	 * 根据这个定义，其实很容易实现，可以用递归或者递推都是比较简单的，下面列举一下递推的代码： 
	 */
	// http://blog.csdn.net/linhuanmars/article/details/23976963
	public int climbStairs(int n) {
	    int f1 = 1;
	    int f2 = 2;
	    if(n==1)
	        return f1;
	    if(n==2)
	        return f2;
	    for(int i=3;i<=n;i++)
	    {
	        int f3 = f1+f2;
	        f1 = f2;
	        f2 = f3;
	    }
	    return f2;
	}
	
	/*Triangle Total Accepted: 29929 Total Submissions: 111093 My Submissions Question Solution 
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
	 * 
	 * 这是一道动态规划的题目，求一个三角形二维数组从顶到低端的最小路径和。思路是维护到某一个元素的最小路径和，那么在某一个元素i，j的最小路径和就是它上层对应的相邻两个元素的最小路径和加上自己的值，递推式是sum[i][j]=min(sum[i-1][j-1],sum[i-1][j])+triangle[i][j]。最后扫描一遍最后一层的路径和，取出最小的即可。每个元素需要维护一次，总共有1+2+...+n=n*(n+1)/2个元素，所以时间复杂度是O(n^2)。而空间上每次只需维护一层即可（因为当前层只用到上一层的元素），所以空间复杂度是O(n)。代码如下： 
[java] view plaincopy在CODE上查看代码片派生到我的代码片
public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {  
    if(triangle == null || triangle.size() == 0)  
        return 0;  
    if(triangle.size()==1)  
        return triangle.get(0).get(0);  
    int[] sums = new int[triangle.size()];  
    sums[0] = triangle.get(0).get(0);  
    for(int i=1;i<triangle.size();i++)  
    {  
        sums[i] = sums[i-1]+triangle.get(i).get(i);  
        for(int j=i-1;j>=1;j--)  
        {  
            sums[j] = (sums[j]<sums[j-1]?sums[j]:sums[j-1]) + triangle.get(i).get(j);  
        }  
        sums[0] = sums[0]+triangle.get(i).get(0);  
          
    }  
    int minimum = sums[0];  
    for(int i=1;i<sums.length;i++)  
    {  
        if(sums[i]<minimum)  
        {  
            minimum = sums[i];  
        }  
    }  
    return minimum;  
}  
上述代码实现时要注意每层第一个和最后一个元素特殊处理一下。


换个角度考虑一下，如果这道题不自顶向下进行动态规划，而是放过来自底向上来规划，递归式只是变成下一层对应的相邻两个元素的最小路径和加上自己的值，原理和上面的方法是相同的，这样考虑到优势在于不需要最后对于所有路径找最小的，而且第一个元素和最后元素也不需要单独处理了，所以代码简洁了很多。代码如下：

[java] view plaincopy在CODE上查看代码片派生到我的代码片
public int minimumTotal(List<List<Integer>> triangle) {  
    if(triangle.size() == 0)  
        return 0;  
    int[] res = new int[triangle.size()];  
    for(int i=0;i<triangle.size();i++)  
    {  
        res[i] = triangle.get(triangle.size()-1).get(i);  
    }  
    for(int i=triangle.size()-2;i>=0;i--)  
    {  
        for(int j=0;j<=i;j++)  
        {  
            res[j] = Math.min(res[j],res[j+1])+triangle.get(i).get(j);   
        }  
    }  
    return res[0];  
}  
这道题是不错的动态规划题目，模型简单，比较适合在电面中出现。
	 *//////////////////////////
	
	
	
	// Word Break II -- LeetCode 
	//http://blog.csdn.net/linhuanmars/article/details/22452163
	////////////////////////////////////////////////////
	/*Word Break Total Accepted: 37652 Total Submissions: 171163 My Submissions Question Solution 
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
	 *这道题仍然是动态规划的题目，我们总结一下动态规划题目的基本思路。首先我们要决定要存储什么历史信息以及用什么数据结构来存储信息。然后是最重要的递推式，就是如从存储的历史信息中得到当前步的结果。最后我们需要考虑的就是起始条件的值。
接下来我们套用上面的思路来解这道题。首先我们要存储的历史信息res[i]是表示到字符串s的第i个元素为止能不能用字典中的词来表示，我们需要一个长度为n的布尔数组来存储信息。然后假设我们现在拥有res[0,...,i-1]的结果，我们来获得res[i]的表达式。思路是对于每个以i为结尾的子串，看看他是不是在字典里面以及他之前的元素对应的res[j]是不是true，如果都成立，那么res[i]为true，写成式子是
假设总共有n个字符串，并且字典是用HashSet来维护，那么总共需要n次迭代，每次迭代需要一个取子串的O(i)操作，然后检测i个子串，而检测是constant操作。所以总的时间复杂度是O(n^2)（i的累加仍然是n^2量级），而空间复杂度则是字符串的数量，即O(n)。代码如下：

[java] view plaincopy
public boolean wordBreak(String s, Set<String> dict) {  
    if(s==null || s.length()==0)  
        return true;  
    boolean[] res = new boolean[s.length()+1];  
    res[0] = true;  
    for(int i=0;i<s.length();i++)  
    {  
        StringBuilder str = new StringBuilder(s.substring(0,i+1));  
        for(int j=0;j<=i;j++)  
        {  
            if(res[j] && dict.contains(str.toString()))  
            {  
                res[i+1] = true;  
                break;  
            }  
            str.deleteCharAt(0);  
        }  
    }  
    return res[s.length()];  
}  
动态规划的题目在LeetCode中占有相当的比例，不过却没有什么通法，因为每道题会有不同的性质和获取信息的角度。但是总体来说基本思路就如同我上面介绍的那样，根据步骤出来之后基本上问题也就解决了，大家可以多练习熟悉一下哈。 
	 */
	
	
	
/*Unique Paths II Total Accepted: 25316 Total Submissions: 90649 My Submissions Question Solution 
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
 * 	
 * 这道题跟Unique Paths非常类似，只是这道题给机器人加了障碍，不是每次都有两个选择（向右，向下）了。因为有了这个条件，所以Unique Paths中最后一个直接求组合的方法就不适用了，这里最好的解法就是用动态规划了。递推式还是跟Unique Paths一样，只是每次我们要判断一下是不是障碍，如果是，则res[i][j]=0，否则还是res[i][j]=res[i-1][j]+res[i][j-1]。实现中还是只需要一个一维数组，因为更新的时候所需要的信息足够了。这样空间复杂度是是O(n)（如同Unique Paths中分析的，如果要更加严谨，我们可以去行和列中小的那个，然后把小的放在内层循环，空间复杂度就是O(min(m,n))，时间复杂度还是O(m*n)。代码如下：
[java] view plaincopy
public int uniquePathsWithObstacles(int[][] obstacleGrid) {  
    if(obstacleGrid == null || obstacleGrid.length==0 || obstacleGrid[0].length==0)  
        return 0;  
    int[] res = new int[obstacleGrid[0].length];  
    res[0] = 1;  
    for(int i=0;i<obstacleGrid.length;i++)  
    {  
        for(int j=0;j<obstacleGrid[0].length;j++)  
        {  
            if(obstacleGrid[i][j]==1)  
            {  
                res[j]=0;  
            }  
            else  
            {  
                if(j>0)  
                    res[j] += res[j-1];  
            }  
        }  
    }  
    return res[obstacleGrid[0].length-1];  
}  
这里就不列出brute force递归方法的代码了，递归式和结束条件跟动态规划很近似，有兴趣的朋友可以写一下哈。	
 */
	/*Unique Paths Total Accepted: 33031 Total Submissions: 101952 My Submissions Question Solution 
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?
Above is a 3 x 7 grid. How many possible unique paths are there?
Note: m and n will be at most 100.
	 * 这道题是比较典型的动态规划的题目。模型简单，但是可以考核动态规划的思想。
我们先说说brute force的解法，比较容易想到用递归，到达某一格的路径数量等于它的上面和左边的路径数之和，结束条件是走到行或者列的边缘。因为每条路径都会重新探索，时间复杂度是结果数量的量级，不是多项式的复杂度。代码如下： 
public int uniquePaths(int m, int n) {
    return helper(1,1,m,n);
}
private int helper(int row, int col, int m, int n)
{
    if(row==m && col==n)
        return 1;
    if(row>m || col>n)
        return 0;
    return helper(row+1,col,m,n)+helper(row,col+1,m,n);
} 上面的代码放到LeetCode中跑会超时，因为不是多项式量级的。其实上一步中递推式已经出来了，就是res[i][j]=res[i-1][j]+res[i][j-1]，这样我们就可以用一个数组来保存历史信息，也就是在i行j列的路径数，这样每次就不需要重复计算，从而降低复杂度。用动态规划我们只需要对所有格子进行扫描一次，到了最后一个得到的结果就是总的路径数，所以时间复杂度是O(m*n)。而对于空间可以看出我们每次只需要用到上一行当前列，以及前一列当前行的信息，我们只需要用一个一维数组存上一行的信息即可，然后扫过来依次更替掉上一行对应列的信息即可（因为所需要用到的信息都还没被更替掉），所以空间复杂度是O(n)（其实如果要更加严谨，我们可以去行和列中小的那个，然后把小的放在内层循环，这样空间复杂度就是O(min(m,n))，下面的代码为了避免看起来过于繁杂，就不做这一步了，有兴趣的朋友可以实现一下，比较简单，不过代码有点啰嗦）。实现的代码如下：
public int uniquePaths(int m, int n) {
    if(m<=0 || n<=0)
        return 0;
    int[] res = new int[n];
    res[0] = 1;
    for(int i=0;i<m;i++)
    {
        for(int j=1;j<n;j++)
        {
           res[j] += res[j-1];
        }
    }
    return res[n-1];
} 上面的方法用动态规划来求解，如果我们仔细的看这个问题背后的数学模型，其实就是机器人总共走m+n-2步，其中m-1步往下走，n-1步往右走，本质上就是一个组合问题，也就是从m+n-2个不同元素中每次取出m-1个元素的组合数。根据组合的计算公式，我们可以写代码来求解即可。代码如下：
public int uniquePaths(int m, int n) {
    double dom = 1;
    double dedom = 1;
    int small = m<n? m-1:n-1;
    int big = m<n? n-1:m-1;
    for(int i=1;i<=small;i++)
    {
        dedom *= i;
        dom *= small+big+1-i;
    }
    return (int)(dom/dedom);
} 上面的代码求解了组合的结果，只需要做一次行或者列的扫描，所以时间复杂度是O(min(m,n))，而空间复杂度是O(1)。比起上面的两种解法更优。不过这里有个弊端，就是如果代码中的dom和dedom如果不是double，而是用int，那么会很容易越界，因为这是一个阶乘，所以大家在面试中讨论这种方法要注意和提及越界的问题。
上面介绍了几种方法来求解这个问题，其实都是比较简单的模型，只是提供了不同的思路。Unique Paths II是这道题的扩展，给机器人增加了障碍，有兴趣的朋友可以联系一下。
	 */
	
/*Minimum Path Sum Total Accepted: 28128 Total Submissions: 88943 My Submissions Question Solution 
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
 * 这道题是动态规划的经典题型，模型足够简单，所以可能在简单的面试（比如电面）中出现。总体来说还是比较容易的，递推式比较直观。
 * 	这道题跟Unique Paths，Unique Paths II是相同类型的。事实上，这道题是上面两道题的general版本，是寻找带权重的path。
 * 在Unique Paths中，我们假设每个格子权重都是1，而在Unique Paths II中我们假设障碍格子的权重是无穷大，所以永远不会选择。
 * 剩下的区别就是这道题寻找这些路径中权重最小的，而不是总路径数。其实方法是一样的，还是使用动态规划，只是现在维护的不是路径数，
 * 而是到达某一个格子的最短路径（也就是权重之和最小）。而这个当前最短路径可以取前面一格和上面一格中小的最短路径长度得到，
 * 因此递推式是res[i][j]=min(res[i-1][j], res[i][j-1])+grid[i][j]。总共进行两层循环，时间复杂度是O(m*n)。
 * 而空间复杂度仍是使用Unique Paths中的方法来省去一维，是O(m)，不了解的朋友可以看看哈。代码如下：
 */
	public int minPathSum(int[][] grid) {
	    if(grid == null || grid.length==0 || grid[0].length==0)
	        return 0;
	    int[] res = new int[grid[0].length];
	    res[0] = grid[0][0];
	    for(int i=1;i<grid[0].length;i++)
	    {
	        res[i] = res[i-1]+grid[0][i];
	    }
	    for(int i=1;i<grid.length;i++)
	    {
	        for(int j=0;j<grid[0].length;j++)
	        {
	            if(j==0)
	                res[j] += grid[i][j];
	            else
	                res[j] = Math.min(res[j-1], res[j])+grid[i][j];
	        }
	    }
	    return res[grid[0].length-1];
	}
	
	
	/*Decode Ways Total Accepted: 28276 Total Submissions: 172604 My Submissions Question Solution 
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
	 * 这道题要求解一个数字串按照字符串编码方式可解析方式的数量。看到这种求数量的，我们很容易想到动态规划来存储前面信息，然后迭代得到最后结果。
我们维护的量res[i]是表示前i个数字有多少种解析的方式，接下来来想想递归式，有两种方式：
第一种新加进来的数字不然就是自己比较表示一个字符，那么解析的方式有res[i-1]种，
第二种就是新加进来的数字和前一个数字凑成一个字符，解析的方式有res[i-2]种（因为上一个字符和自己凑成了一个）。
当然这里要判断前面说的两种情况能否凑成一个字符，也就是范围的判断，如果可以才有对应的解析方式，如果不行，那么就是0。
最终结果就是把这两种情况对应的解析方式相加。这里可以把范围分成几个区间：
（1）00：res[i]=0（无法解析，没有可行解析方式）；
（2）10, 20：res[i]=res[i-2]（只有第二种情况成立）；
（3）11-19, 21-26：res[i]=res[i-1]+res[i-2]（两种情况都可行）；
（4）01-09, 27-99：res[i]=res[i-1]（只有第一种情况可行）；
递推式就是按照上面的规则来得到，接下来我们只要进行一遍扫描，然后依次得到维护量就可以了，算法的时间复杂度是O(n)。空间上可以看出我们每次只需要前两位的历史信息，所以只需要维护三个变量然后迭代赋值就可以了，所以空间复杂度是O(1)。代码如下：
这道题是一维动态规划的题目，递推式关系来说是比较容易得到的，主要是要对这些两位数进行划分有一些细节，容易出小错误。
	 */
	public static int numDecodings(String s) {
	    if(s==null || s.length()==0 || s.charAt(0)=='0')
	    {
	        return 0;
	    }
	    int num1=1; //res[i-2]
	    int num2=1; //res[i-1]
	    int num3=1; //res[i], add a new elem, the num of ways
	    for(int i=1;i<s.length();i++)
	    {
	        if(s.charAt(i)=='0')
	        {
	            if(s.charAt(i-1)=='1' || s.charAt(i-1)=='2')
	                num3 = num1;
	            else
	                return 0;
	        }
	        else
	        {
	            if(s.charAt(i-1)=='0' || s.charAt(i-1)>='3')
	                num3 = num2;
	            else
	            {
	                if(s.charAt(i-1)=='2' && s.charAt(i)>='7' && s.charAt(i)<='9')
	                    num3 = num2;
	                else
	                    num3 = num1+num2;
	            }
	        }
	        num1 = num2;
	        num2 = num3;
	    }
	    return num2;
	}
	
	
	
	/*
	 * 可以很容易判断，上面代码的时间复杂度是O(n)，面试一般都会实现一下，不过还没完，面试官会接着问一下，有没有更好的解法？还真有，斐波那契数列其实是有O(logn)的解法的。根据wiki我们知道，斐波那契数列是有通项公式的，如下：
所以如果我们用Pow(x, n)中介绍的分治法来求解这个n次幂的话可以完成O(logn)的求解。还有另一种理解方法就是斐波那契数列的线性代数解法（参见Wiki - 斐波那契数列），可以看到迭代是一个二乘二的简单矩阵，数列的第n个数就是求解这个矩阵的n-2次幂，同样用分治法就可以完成O(logn)的求解。
这是对于斐波那契数列问题的一般面试过程，先实现一下通常的O(n)的解法，然后再了解一下是否知道有O(logn)的解法，一般不要求实现，知道就行，不过其实实现也不是很难，有兴趣的朋友可以练习一下哈。
	 */
	
	/*Best Time to Buy and Sell Stock III Total Accepted: 24673 Total Submissions: 107169
	 * Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete at most two transactions.
Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
	 这道题是Best Time to Buy and Sell Stock的扩展，现在我们最多可以进行两次交易。我们仍然使用动态规划来完成，事实上可以解决非常通用的情况，也就是最多进行k次交易的情况。
这里我们先解释最多可以进行k次交易的算法，然后最多进行两次我们只需要把k取成2即可。我们还是使用“局部最优和全局最优解法”。我们维护两种量，一个是当前到达第i天可以最多进行j次交易，最好的利润是多少（global[i][j]），
另一个是当前到达第i天，最多可进行j次交易，并且最后一次交易在当天卖出的最好的利润是多少（local[i][j]）。下面我们来看递推式，全局的比较简单，
global[i][j]=max(local[i][j],global[i-1][j])，
也就是去当前局部最好的，和过往全局最好的中大的那个（因为最后一次交易如果包含当前天一定在局部最好的里面，否则一定在过往全局最优的里面）。对于局部变量的维护，递推式是
local[i][j]=max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)，
也就是看两个量，第一个是全局到i-1天进行j-1次交易，然后加上今天的交易，如果今天是赚钱的话（也就是前面只要j-1次交易，最后一次交易取当前天），第二个量则是取local第i-1天j次交易，然后加上今天的差值（这里因为local[i-1][j]比如包含第i-1天卖出的交易，所以现在变成第i天卖出，并不会增加交易次数，而且这里无论diff是不是大于0都一定要加上，因为否则就不满足local[i][j]必须在最后一天卖出的条件了）。
上面的算法中对于天数需要一次扫描，而每次要对交易次数进行递推式求解，所以时间复杂度是O(n*k)，如果是最多进行两次交易，那么复杂度还是O(n)。空间上只需要维护当天数据皆可以，所以是O(k)，当k=2，则是O(1)。代码如下： 
	 */
	public int maxProfitIII(int[] prices) {
	    if(prices==null || prices.length==0)
	        return 0;
	    int[] local = new int[3];
	    int[] global = new int[3];
	    for(int i=0;i<prices.length-1;i++)
	    {
	        int diff = prices[i+1]-prices[i];
	        for(int j=2;j>=1;j--)
	        {
	            local[j] = Math.max(global[j-1]+(diff>0?diff:0), local[j]+diff);
	            global[j] = Math.max(local[j],global[j]);
	        }
	    }
	    return global[2];
	}
	/*
	 * 可以看到，这里的模型是比较复杂的，主要是在递推式中，local和global是交替求解的。不过理清思路之后，代码是非常简练的，不禁感叹算法真是牛逼哈，这么个复杂生活问题几行代码就解决了。
	 */
	
	
	
	/*Best Time to Buy and Sell Stock II Total Accepted: 36043 Total Submissions: 96616 My Submissions Question Solution 
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
(ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions 
at the same time (ie, you must sell the stock before you buy again).
	 * 这道题跟Best Time to Buy and Sell Stock类似，求最大利润。区别是这里可以交易无限多次（当然我们知道交易不会超过n-1次，也就是每天都进行先卖然后买）。
	 * 既然交易次数没有限定，可以看出我们只要对于每次两天差价大于0的都进行交易，就可以得到最大利润。
	 * 因此算法其实就是累加所有大于0的差价既可以了，非常简单。如此只需要一次扫描就可以了，时间复杂度是O(n)，空间上只需要O(1)存一个累加结果即可。代码如下： 
	 */
	public int maxProfitII(int[] prices) {

	    if(prices == null || prices.length==0)
	        return 0;
	    int res = 0;
	    for(int i=0;i<prices.length-1;i++)
	    {
	        int diff = prices[i+1]-prices[i];
	        if(diff>0)
	            res += diff;
	    }
	    return res;
	}
	
	
/* Best Time to Buy and Sell Stock Total Accepted: 37803 Total Submissions: 118288 My Submissions Question Solution 
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
design an algorithm to find the maximum profit.
 * 这道题求进行一次交易能得到的最大利润。如果用brute force的解法就是对每组交易都看一下利润，取其中最大的，总用有n*(n-1)/2个可能交易，所以复杂度是O(n^2)。
很容易感觉出来这是动态规划的题目，其实跟Maximum Subarray非常类似，用“局部最优和全局最优解法”。
思路是维护两个变量，一个是到目前为止最好的交易，另一个是在当前一天卖出的最佳交易（也就是局部最优）。
递推式是local[i+1]=max(local[i]+prices[i+1]-price[i],0), global[i+1]=max(local[i+1],global[i])。
这样一次扫描就可以得到结果，时间复杂度是O(n)。而空间只需要两个变量，即O(1)。代码如下：
 */
	public int maxProfit(int[] prices) {
	    if(prices==null || prices.length==0)
	        return 0;
	    int local = 0;
	    int global = 0;
	    for(int i=0;i<prices.length-1;i++)
	    {
	        local = Math.max(local+prices[i+1]-prices[i],0);
	        global = Math.max(local, global);
	    }
	    return global;
	}
	/*
	 * 这种题目的解法非常经典，不过是比较简单的动态规划。这道题目还有两个变体，
	 * Best Time to Buy and Sell Stock II和Best Time to Buy and Sell Stock III，
	 * 虽然题目要求比较像，但是思路却变化比较大，Best Time to Buy and Sell Stock II可以交易无穷多次，思路还是比较不一样，
	 * 而Best Time to Buy and Sell Stock III则限定这道题交易两次，其实还可以general到限定交易k次，会在这道题目中仔细讲解，有兴趣的朋友可以看看哈。
	 * 
	 */
}