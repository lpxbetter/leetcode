import java.io.*;
import java.util.*;
//import commLib.*;

public class backtracking {
	public static void main(String[] args){
		String[] strs = {"hello"};
		ArrayList<String> res = new ArrayList<String>(2);
		res.add(strs[0]);
		System.out.println(res);
		System.out.println(res.size());
		
		//solveNQueens(4);
		
		//uniquePaths(2,3);
		//uniquePaths2(3,2);
		int[] num={2,3,6,5,4,1};
		//int[] num={1,2,4,3};
		//nextPermutation(num);
		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		//spiralOrder(matrix);
		//int n[]={1,0,1};
		//sortColors(n);
		//int n[] = {4,2,1,6};
		//int n[]={1,2,3,1};
		//findPeakElement(n);
		
		//combine(3,2);
		
		int n[]={0,1,2};
		//subsets(n);
		//subsetsInte(n);
		
		//int n[]={1,2,2};
		//subset2(n);
		//subsetsRecur(n);
		
		//int A[]={2,3,6,7};
		//combinationSum(A,7);
		/*
		int A[]={1,1,2};
		ArrayList<ArrayList<Integer>> rst = combinationSumII(A,3);
		System.out.println(rst);
		*/
		//letterCombinations("23");
		//lengthOfLongestSubstring("bbdbc");
		//StringBuilder newRes = new StringBuilder();
		//newRes
		//String res=multiply2("53", "124");
		//com.out(res);
		//getPermutation(3,6);
		//partition("aab");
		
		char board[ ][ ]={{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		//exist(board,"ABCCED");
		//exist(board,"SEE");
		
		//permute(new int[]{1,2,3});
		//parlindromePartition("aab");
		generateParenthesis(2);
		//getPermutation(4,21);
		//getDict("aabcb");
		//combSum(new int[]{2,3,7},7);
	}
	
	public static int count=0;
	public static void combSum(int[] S,int T) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		HashMap<Integer,Integer> map=new HashMap<>();
	    if(S==null || S.length==0)
	        return;
	    combhelper(S, T,new ArrayList<Integer>(), res,map);
	    System.out.println(count);
	}
	private static void combhelper(int[] S,int T, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res,HashMap<Integer,Integer> map)
	{
		if(T<0) return;
		if(T==0) {
			count++;
			res.add(new ArrayList<Integer>(item));
			System.out.println(res);
			return;
		}
		for(int i=0;i<S.length;i++){
			item.add(S[i]);
			combhelper(S,T-S[i],item,res,map);
			map.put(T-S[i],count);
			ptrMap(map);
			item.remove(item.size()-1);
		}
	}
	
	private static void ptrMap(HashMap<Integer,Integer> map){
	//Map map = new HashMap(); 
	Iterator iter = map.entrySet().iterator();
	while (iter.hasNext()) { 
	    Map.Entry entry = (Map.Entry) iter.next(); 
	    Object key = entry.getKey(); 
	    Object val = entry.getValue(); 
	    System.out.println(key+":"+val);
	}
	}
	
	public static boolean exist(char[][] board, String word) {
	    if(word==null || word.length()==0)
	        return true;
	    if(board==null || board.length==0 || board[0].length==0)
	        return false;
	    boolean[][] used = new boolean[board.length][board[0].length];
	    for(int i=0;i<board.length;i++)
	    {
	        for(int j=0;j<board[0].length;j++)
	        {
	            if(search(board,word,0,i,j,used))
	                return true;
	        }
	    }
	    return false;
	}
	
	private static boolean search(char[][] board, String word, int index, int i, int j, boolean[][] used)
	{
		System.out.println("index="+index + " i="+i +" j="+j);
		
	    if(index == word.length()){
	    	System.out.println("index==word.length,return true.");
	        return true;
	    }
	    //System.out.println("used[i][j]="+used[i][j]);
	    System.out.println(" word.charAt(index)=" + word.charAt(index) );
	    if(i>=0&&j>=0&&i<=board.length && j<=board[0].length){
	    	System.out.println("used[i][j]="+used[i][j]+ "board[i][j]=" + board[i][j] + " word.charAt(index)=" + word.charAt(index));
	    }
	    
	    if(i<0 || j<0 || i>=board.length || j>=board[0].length || used[i][j] || board[i][j]!=word.charAt(index)){
	    	System.out.println("return false.");
	        return false;
	    }
	    used[i][j] = true;
	    //System.out.println("used[i][j]="+used[i][j]+ "board[i][j]=" + board[i][j] + " word.charAt(index)=" + word.charAt(index));
	    System.out.println("begin search index+1="+(index+1));
	    boolean res = search(board,word,index+1,i-1,j,used) 
	                || search(board,word,index+1,i+1,j,used)
	                || search(board,word,index+1,i,j-1,used) 
	                || search(board,word,index+1,i,j+1,used);
	    used[i][j] = false;
	    return res;
	}
	
	
	
	
	/*Palindrome Partitioning Total Accepted: 30221 Total Submissions: 115851 My Submissions Question Solution 
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.
For example, given s = "aab",
Return
  [
    ["aa","b"],
    ["a","a","b"]
  ]
   * 
	这道题是求一个字符串中回文子串的切割，并且输出切割结果，其实是Word Break II和Longest Palindromic Substring结合，该做的我们都做过了。首先我们根据Longest Palindromic Substring中的方法建立一个字典，得到字符串中的任意子串是不是回文串的字典，不熟悉的朋友可以先看看哈。接下来就跟Word Break II一样，根据字典的结果进行切割，然后按照循环处理递归子问题的方法，如果当前的子串满足回文条件，就递归处理字符串剩下的子串。如果到达终点就返回当前结果。算法的复杂度跟Word Break II一样，取决于结果的数量，最坏情况是指数量级的。代码如下：
	同样，这里同Word Break II一样也可以使用动态规划的方法，但是要对所有中间结果进行存储，花费大量的空间，这里就不列举代码了。这道题扩展还有Palindrome Partitioning II，虽然求解的问题类似，但是因为一些细节的不同，复杂度会有很大的变化，有兴趣的朋友可以看看哈。
	*/
	//by code ganker
	public static ArrayList<ArrayList<String>> parlindromePartition(String s) {
	    ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
	    if(s==null || s.length()==0)
	        return res;
	    parlindromePartition(s, getDict(s),0,new ArrayList<String>(), res);
	    return res;
	}
	private static void parlindromePartition(String s, boolean[][] dict, int start, ArrayList<String> item, ArrayList<ArrayList<String>> res)
	{
	    if(start==s.length())
	    {
	        res.add(new ArrayList<String>(item));
	        return;
	    }
	    for(int i=start;i<s.length();i++)
	    {
	        if(dict[start][i]) 
	        {
	            item.add(s.substring(start,i+1));
	            parlindromePartition(s,dict,i+1,item,res);
	            item.remove(item.size()-1);
	        }
	    }
	}
	private static boolean[][] getDict(String s)
	{
	    boolean[][] dict = new boolean[s.length()][s.length()];
	    for(int i=s.length()-1;i>=0;i--)
	    {
	    	System.out.println();
	        for(int j=i;j<s.length();j++)
	        {
	        	System.out.println("i="+i+" j="+j);
	            if(s.charAt(i)==s.charAt(j) && ((j-i<2)||dict[i+1][j-1]))
	            {
	            	System.out.println("i="+i+" j="+j+ "  true");
	                dict[i][j] = true;
	            }
	        }
	    }
	    return dict;
	}
	
	
	
	//by discuss;  https://oj.leetcode.com/discuss/9623/my-java-dp-only-solution-without-recursion-o-n-2
    public static List<List<String>> partition2(String s) {
    int len = s.length();
        List<List<String>>[] result = new List[len + 1];
        result[0] = new ArrayList<List<String>>();
        result[0].add(new ArrayList<String>());
        System.out.println("result[0]="+result[0]);
        
        boolean[][] pair = new boolean[len][len];
        for(int i=0; i<s.length(); i++){
        	System.out.println("i="+i);
            result[i+1] = new ArrayList<List<String>>();
            System.out.println("i+1="+(i+1)+" result[i+1]="+result[i+1]);
            
            char c = s.charAt(i);
            for(int j=0; j<=i; j++){
            	System.out.println("j="+j);
                if(j == i)
                    pair[j][i] = true;
                else{
                    if(s.charAt(j) != c)
                        continue;
                    if(j == i-1)
                        pair[j][i] = true;
                    else
                        pair[j][i] = pair[j+1][i-1];
                }
                if(pair[j][i]){
                    String str = s.substring(j, i+1);
                    System.out.println("str="+str);
                    for(List<String> r : result[j]){
                        List<String> ri = new ArrayList<String>(r);
                        ri.add(str);
                        result[i+1].add(ri);
                        System.out.println("i+1="+(i+1)+" result[i+1]="+result[i+1]);
                    }
                }
            }
        }

        return result[len];        
    }

	
	

	/*Word Break II Total Accepted: 25561 Total Submissions: 149850 My Submissions Question Solution 
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.
For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].
A solution is ["cats and dog", "cat sand dog"].
	 * 这道题目要求跟Word Break比较类似，不过返回的结果不仅要知道能不能break，如果可以还要返回所有合法结果。
	 * 一般来说这种要求会让动态规划的效果减弱很多，因为我们要在过程中记录下所有的合法结果，中间的操作会使得算法的复杂度不再是动态规划的两层循环，
	 * 因为每次迭代中还需要不是constant的操作，最终复杂度会主要取决于结果的数量，而且还会占用大量的空间，因为不仅要保存最终结果，
	 * 包括中间的合法结果也要一一保存，否则后面需要历史信息会取不到。所以这道题目我们介绍两种方法，一种是直接brute force用递归解，
	 * 另一种是跟Word Break思路类似的动态规划。
对于brute force解法，代码比较简单，每次维护一个当前结果集，然后遍历剩下的所有子串，如果子串在字典中出现，则保存一下结果，
并放入下一层递归剩下的字符。思路接近于我们在N-Queens这些NP问题中经常用到的套路。代码如下：
[java] view plaincopy在CODE上查看代码片派生到我的代码片

*/
public ArrayList<String> wordBreakII(String s, Set<String> dict) {  
    ArrayList<String> res = new ArrayList<String>();  
    if(s==null || s.length()==0)  
        return res;  
    helper(s,dict,0,"",res);  
    return res;  
}  
private void helper(String s, Set<String> dict, int start, String item, ArrayList<String> res)  
{  
    if(start>=s.length())  
    {  
        res.add(item);  
        return;  
    }  
    StringBuilder str = new StringBuilder();  
    for(int i=start;i<s.length();i++)  
    {  
        str.append(s.charAt(i));  
        if(dict.contains(str.toString()))  
        {  
            String newItem = item.length()>0?(item+" "+str.toString()):str.toString();  
            helper(s,dict,i+1,newItem,res);  
        }  
    }  
    
}  /*
接下来我们列出动态规划的解法，递推式跟Word Break是一样的，只是现在不只要保存true或者false，还需要保存true的时候所有合法的组合，以便在未来需要的时候可以得到。不过为了实现这点，代码量就增大很多，需要一个数据结构来进行存储，同时空间复杂度变得非常高，因为所有中间组合都要一直保存。时间上还是有提高的，就是当我们需要前面到某一个元素前的所有合法组合时，我们不需要重新计算了。不过最终复杂度还是主要取决于结果的数量。代码如下：
[java] view plaincopy在CODE上查看代码片派生到我的代码片
class Interval {  
    int start;  
    int end;  
    public Interval(int start, int end) {  
        this.start = start; this.end = end;  
    }  
    public Interval(Interval i) {  
        start = i.start;  
        end = i.end;  
 }  
}  
ArrayList<ArrayList<Interval>> deepCopy(ArrayList<ArrayList<Interval>> paths) {  
    if (paths==null) return null;  
    ArrayList<ArrayList<Interval>> res = new ArrayList<ArrayList<Interval>>(paths.size());  
    for (int i=0; i<paths.size(); i++) {  
     ArrayList<Interval> path = paths.get(i);  
     ArrayList<Interval> copy = new ArrayList<Interval>(path.size());  
        for (int j=0; j<path.size(); j++) {  
         copy.add(new Interval(path.get(j)));  
     }  
     res.add(copy);  
    }  
    return res;  
}  
public ArrayList<String> wordBreak(String s, Set<String> dict) {  
    ArrayList<String> res = new ArrayList<String>();  
    if (s==null || s.length()==0) return res;  
    Map<Integer, ArrayList<ArrayList<Interval>>> dp = new HashMap<Integer, ArrayList<ArrayList<Interval>>>();  
    dp.put(0, new ArrayList<ArrayList<Interval>>());  
    dp.get(0).add(new ArrayList<Interval>());  
    for (int i=1; i<=s.length(); i++) {  
        for (int j=0; j<i; j++) {  
            String cur = s.substring(j, i);  
            ArrayList<ArrayList<Interval>> paths = null;  
            if (dp.containsKey(j) && dict.contains(cur)) {  
                paths = deepCopy(dp.get(j));  
                Interval interval = new Interval(j, i);  
                for (ArrayList<Interval> path:paths) {  
                    path.add(interval);  
                }  
            }  
            if (paths != null) {  
                if (!dp.containsKey(i)) {  
                    dp.put(i, paths);  
                }   
                else {  
              dp.get(i).addAll(paths);  
             }  
            }  
        }  
    }  
    if (!dp.containsKey(s.length())) {  
        return res;  
    }  
    ArrayList<ArrayList<Interval>> paths = dp.get(s.length());  
    for (int j=0; j<paths.size(); j++) {  
        ArrayList<Interval> path = paths.get(j);  
        StringBuilder str = new StringBuilder();  
        for (int i=0; i<path.size(); i++) {  
            if (i!=0) {str.append(" ");}  
            int start = path.get(i).start;  
            int end = path.get(i).end;  
            str.append(s.substring(start, end));  
        }  
        res.add(str.toString());  
    }  
    return res;  
}  
/*
可以看出，用动态规划的代码复杂度要远远高于brute force的解法，而且本质来说并没有很大的提高，甚至空间上还是一个暴涨的情况。所以这道题来说并不是一定要用动态规划，有一个朋友在面Amazon时遇到这道题，面试官并没有要求动态规划，用brute force即可，不过两种方法时间上和空间上的优劣还是想清楚比较好，面试官可能想听听理解。实现的话可能主要是递归解法。
还有一点需要指出的是，上面的两个代码放到LeetCode中都会超时，原因是LeetCode中有一个非常tricky的测试case，其实是不能break的，但是又很长，出现大量的记录和回溯，因此一般通过这个的解法是把Word Break先跑一遍，判断是不是能break，如果可以再跑上面的代码。这样做法其实比较傻，但是为了过这个只能这样了，这一点我觉得LeetCode没必要把超时设得这么严格，实际意义不大，只是把AC率给拉了下来哈。
	 */
	
	
/*
 * Word Break Total Accepted: 37688 Total Submissions: 171284 My Submissions Question Solution 
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code"
这道题仍然是动态规划的题目，我们总结一下动态规划题目的基本思路。首先我们要决定要存储什么历史信息以及用什么数据结构来存储信息。然后是最重要的递推式，就是如从存储的历史信息中得到当前步的结果。最后我们需要考虑的就是起始条件的值。
接下来我们套用上面的思路来解这道题。首先我们要存储的历史信息res[i]是表示到字符串s的第i个元素为止能不能用字典中的词来表示，我们需要一个长度为n的布尔数组来存储信息。然后假设我们现在拥有res[0,...,i-1]的结果，我们来获得res[i]的表达式。思路是对于每个以i为结尾的子串，看看他是不是在字典里面以及他之前的元素对应的res[j]是不是true，如果都成立，那么res[i]为true，写成式子是

假设总共有n个字符串，并且字典是用HashSet来维护，那么总共需要n次迭代，每次迭代需要一个取子串的O(i)操作，然后检测i个子串，而检测是constant操作。所以总的时间复杂度是O(n^2)（i的累加仍然是n^2量级），而空间复杂度则是字符串的数量，即O(n)。代码如下：

[java] view plaincopy
*/
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
/* 
动态规划的题目在LeetCode中占有相当的比例，不过却没有什么通法，因为每道题会有不同的性质和获取信息的角度。但是总体来说基本思路就如同我上面介绍的那样，根据步骤出来之后基本上问题也就解决了，大家可以多练习熟悉一下哈。
 */
//by discuss ; https://oj.leetcode.com/discuss/18904/java-implementation-using-dp-in-two-ways 
public boolean wordBreak2(String s, Set<String> dict) {

    boolean[] f = new boolean[s.length() + 1];
    Arrays.fill(f, false);

    f[0] = true;


    /* First DP
    for(int i = 1; i <= s.length(); i++){
        for(String str: dict){
            if(str.length() <= i){
                if(f[i - str.length()]){
                    if(s.substring(i-str.length(), i).equals(str)){
                        f[i] = true;
                        break;
                    }
                }
            }
        }
    }*/

    //Second DP
    for(int i=1; i <= s.length(); i++){
        for(int j=0; j < i; j++){
            if(f[j] && dict.contains(s.substring(j, i))){
                f[i] = true;
                break;
            }
        }
    }

    return f[s.length()];
}

	
	/*
这道题目算法上没有什么特别的，更像是一道找规律的数学题目。我们知道，n个数的permutation总共有n阶乘个，
基于这个性质我们可以得到某一位对应的数字是哪一个。思路是这样的，比如当前长度是n，
我们知道每个相同的起始元素对应(n-1)!个permutation，也就是(n-1)!个permutation后会换一个起始元素。
因此，只要当前的k进行(n-1)!取余，得到的数字就是当前剩余数组的index，如此就可以得到对应的元素。如此递推直到数组中没有元素结束。
实现中我们要维护一个数组来记录当前的元素，每次得到一个元素加入结果数组，然后从剩余数组中移除，因此空间复杂度是O(n)。
时间上总共需要n个回合，而每次删除元素如果是用数组需要O(n),所以总共是O(n^2)。这里如果不移除元素也需要对元素做标记，所以要判断第一个还是个线性的操作。
	 */
	public static String getPermutation(int n, int k) {
	    if(n<=0)
	        return "";
	    k--;
	    StringBuilder res = new StringBuilder();
	    int factorial = 1;
	    ArrayList<Integer> nums = new ArrayList<Integer>();
	    for(int i=2;i<n;i++)
	    {
	        factorial *= i;
	    }
	    System.out.println("factorial="+factorial);
	    for(int i=1;i<=n;i++)
	    {
	        nums.add(i);
	    }
	    System.out.println("start nums="+nums);
	    
	    int round = n-1;
	    
	    while(round>=0)
	    {
	    	System.out.println("round:"+round);
	    	
	        int index = k/factorial;
	        System.out.println("index = k/factorial k="+k+ " factorial=" + factorial + " index="+index+" " );
	        k %= factorial;
	        System.out.println("k %= factorial k="+k);
	        
	        res.append(nums.get(index));
	        System.out.println("res="+res);
	        
	        nums.remove(index);
	        System.out.println("nums="+nums);
	        
	        if(round>0)
	            factorial /= round;
	        System.out.println("factorial="+factorial);
	        
	        round--;
	    }
	    return res.toString();
	}

	public static ArrayList<String> generateParenthesis(int n) {
	    ArrayList<String> res = new ArrayList<String>();
	    if(n<=0)
	        return res;
	    helper(n,n,new String(),res,0);
	    return res;
	}
	private static void helper(int l, int r, String item, ArrayList<String> res,int level)
	{
		System.out.println("begin level "+String.valueOf(level) + "; l="+String.valueOf(l) + " r="+String.valueOf(r) );
	    if(r<l)
	        return;
	    if(l==0 && r==0)
	    {
	        res.add(item);
	        System.out.println(item);
	        System.out.println(res);
	    }
	    if(l>0){
	    	System.out.println("level="+String.valueOf(level)+" left start; item="+item+"(" +" l="+ String.valueOf(l-1) +" r="+ String.valueOf(r) +" enter level: "+String.valueOf(level+1) );
	        helper(l-1,r,item+"(",res,level+1);
	        System.out.println("level="+String.valueOf(level)+" left finish.");
	        //com.out("");
	    }
	    if(r>0){
	    	System.out.println("level="+String.valueOf(level)+" right start; item="+item+")" +" l="+ String.valueOf(l) +" r="+ String.valueOf(r-1) +" enter level: "+String.valueOf(level+1));
	        helper(l,r-1,item+")",res,level+1);
	        System.out.println("level="+String.valueOf(level)+" right finish");
	        //com.out("");
	    }
	    System.out.println("end level "+String.valueOf(level) + "; l="+String.valueOf(l) + " r="+String.valueOf(r));	 
	    //com.out("");
	}	
	
	
	/*
	 * Gray Code Total Accepted: 25902 Total Submissions: 79854 My Submissions Question Solution 
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
	 这道题要求求出n位的格雷码对应的二进制数，主要在于找到一种格雷码的递增方法（格雷码并不是唯一的，可以有多种）。
我们来看看有了n-1位的格雷码如何得到n位的格雷码呢？其实方法比较简单，eg n==3, 已经知道  res_2=[00,01] 
首先在n-1位的格雷码前面都添加0作为前2^(n-1)个格雷码，它们一定是合法的因为除了第一位（都是0）其余位都跟n-1的格雷码一致，
所以两两之间只相差一位，满足要求。 eg, res_2=[00,01] -> res_3=[000,001]
接下来看看如何接上剩下的2^(n-1)个.
我们把n-1位的格雷码倒序排列，newRes_2 = [01,00]
然后在每个前面添加1，然后接到上述的前2^(n-1)个就可以了。  -> [101, 100].  
首先因为是倒序过来，所以中间两个元素除去第一位其他都是一样的（因为原来最后一个，现在倒序过来就是第一个），而他们第一位分别是0和1，满足格雷码的要求。
-- 
0+00
0+01 
1+01 -- 中间这两个除第一位不一样，其他都一样。
1+00

而剩下的元素因为我们是n-1位的格雷码倒序排列，所以两两都是满足要求的，加上前面都一样的位1，仍然满足条件。最后看看这些数字是不是都不一样，
前半部分和后半部分肯定不会一样，而因为前半部分都是0开头，后半部分都是1打头，所以互相之间也不会有重复，可以看出覆盖了所有数字，而且依次下来均满足条件。
如此我们提出了格雷码的递推方法，我们只需要做一次位数的循环，每次根据上面结果构造当前位数的结果即可。算法复杂度是O(2+2^2+...+2^n-1)=O(2^n)，所以是指数量级的，因为是结果数量无法避免。空间复杂度则是结果的大小，也是O(2^n)。
	 */
	public ArrayList<Integer> grayCode(int n) {
	    ArrayList<Integer> res = new ArrayList<Integer>();
	    if(n<0)
	        return res;
	    if(n==0)
	    {
	        res.add(0);
	        return res;
	    }
	    // n=1,res=[0,1] ； 0，1
	    res.add(0);
	    res.add(1);
	    //i从2开始，
	    for(int i=2;i<=n;i++)
	    {
	        int size = res.size();
	        //将res倒过来，并且在前面加上1，相当于 res的元素+ 1<<(i-1)， 将这些添加到原来res里面。
	        for(int j=size-1;j>=0;j--)
	        {
	            res.add(res.get(j)+(1<<(i-1)));
	        }
	    }
	    return res;
	}
	

    /*Given a string, find the length of the longest substring without repeating characters. 
     * For example, the longest substring without repeating letters for "abcabcbb" is "abc", 
     * which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
     ////////
     two pointers, 
     */
    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> set= new HashSet<Character>();
        int i=0; 
        int j=0;
        int maxLen=0;
        while(i<=s.length()-1 && j<=s.length()-1){
        	//j++直到s[j]是set中的元素。也就是出现了重复元素，需要重新开始计算。
            while(j<=s.length()-1 && !set.contains(s.charAt(j))){
                set.add(s.charAt(j));
                maxLen=Math.max(maxLen,j-i+1);
                j++;
            }//end while j
            //i++，直到s[i]==s[j],并且在此过程中去掉set中的s[i]，因为要重新开始一个新的set
            while(i<=s.length()-1 && j<=s.length()-1 && s.charAt(i)!=s.charAt(j)){
                set.remove(s.charAt(i));
                i++;
            }//end whil i
            i++; j++;
        }//end while i,j
        //System.out.println(maxLen);
        return maxLen;
   }
    /*
     * Given a digit string, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below.
Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//////
 * [a,b,c] then add the letters of 2nd digit to each elem in old res. 
     * */
    public static ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<String>();
        
        //Trav each digit
        for(int i=0;i<digits.length();i++){
            ArrayList<String> newRes = new ArrayList<String>();
            
            String letters=getLetters(digits.charAt(i));
            
            //a,b,c, Trav each letter,add to all pre res
            for(int j=0;j<letters.length();j++){
                if(i==0) newRes.add( letters.substring(j,j+1)  ); 
                else{
                    for(int k=0; k<res.size();k++){
                        newRes.add( res.get(k) + letters.charAt(j)  );
                    }
                }//end trav previous res
            }//end trav letters
            res=newRes;
            
        }//end trav digits
        System.out.println(res);
        return res;
    }//end func
    
    private static String getLetters(char digit)
	{
	    switch(digit)
	    {
	        case '2':
	            return "abc";
	        case '3':
	            return "def";
	        case '4':
	            return "ghi";
	        case '5':
	            return "jkl";
	        case '6':
	            return "mno";
	        case '7':
	            return "pqrs";
	        case '8':
	            return "tuv";
	        case '9':
	            return "wxyz";
	        case '0':
	            return " ";
	        default:
	            return "";
	    }
	}

	
	/*Above is a 3 x 7 grid. How many possible unique paths are there?*/
	/*
	 * 其实上一步中递推式已经出来了，就是res[i][j]=res[i-1][j]+res[i][j-1]，这样我们就可以用一个数组来保存历史信息，
	 * 也就是在i行j列的路径数，这样每次就不需要重复计算，从而降低复杂度。用动态规划我们只需要对所有格子进行扫描一次，
	 * 到了最后一个得到的结果就是总的路径数，所以时间复杂度是O(m*n)。而对于空间可以看出我们每次只需要用到上一行当前列，
	 * 以及前一列当前行的信息，我们只需要用一个一维数组存上一行的信息即可，然后扫过来依次更替掉上一行对应列的信息即可
	 * （因为所需要用到的信息都还没被更替掉），所以空间复杂度是O(n)（其实如果要更加严谨，我们可以去行和列中小的那个，
	 * 然后把小的放在内层循环，这样空间复杂度就是O(min(m,n))，下面的代码为了避免看起来过于繁杂，就不做这一步了，
	 * 有兴趣的朋友可以实现一下，比较简单，不过代码有点啰嗦）。实现的代码如下：
	 * */
	public static int uniquePaths(int m, int n) {
	    int res=uphelper(1,1,m,n);
	    System.out.println(res);
	    return res;
	}
	private static int uphelper(int row, int col, int m, int n)
	{
	    if(row==m && col==n)
	        return 1;
	    if(row>m || col>n)
	        return 0;
	    
	    int up= uphelper(row+1,col,m,n);
	    int left=uphelper(row,col+1,m,n);
	    
	    return up+left;
	}
	
	public static int uniquePaths2(int m, int n) {
	    if(m<=0 || n<=0)
	        return 0;
	    int[] res = new int[n];
	    res[0] = 1;
	    ptrArr(res);
	    for(int i=0;i<m;i++)
	    {
	    	System.out.println("i="+i);
	        for(int j=1;j<n;j++)
	        {
	        	System.out.println("--j="+j);
	        	System.out.println("--res[j]="+ res[j]);
	        	System.out.println("--res[j-1]="+ res[j-1]);
	        	res[j] += res[j-1];
	        	System.out.println("--Aft add:res[j]="+ res[j]);
	        	ptrArr(res);
	        	System.out.println();
	        }
	    }
	    return res[n-1];
	}
	
	
	/*
	 *下面我们用一个例子来说明，比如排列是(2,3,6,5,4,1)，求下一个排列的基本步骤是这样：
1) 先从后往前找到第一个不是依次增长的数，记录下位置p。比如例子中的3，对应的位置是1;
2) 接下来分两种情况：
    (1) 如果上面的数字都是依次增长的，那么说明这是最后一个排列，下一个就是第一个，其实把所有数字反转过来即可
    (比如(6,5,4,3,2,1)下一个是(1,2,3,4,5,6));
    (2) 否则，如果p存在，从p开始往后找，找到下一个数就比p对应的数 小的数字，然后两个调换位置，比如例子中的4。
    调换位置后得到(2,4,6,5,3,1)。最后把p之后的所有数字倒序，比如例子中得到(2,4,1,3,5,6), 这个即是要求的下一个排列。
	 */
	public static void nextPermutation(int[] num) {
		
	    if(num==null || num.length==0)
	        return;
	    int i = num.length-2;
	    ptrArr(num);
	    while(i>=0 && num[i]>=num[i+1])
	    {
	        i--;
	    }
	    System.out.println("i="+i+"  num[i]="+num[i]);
	    
	    if(i>=0)
	    {
	        int j=i+1;
	        while(j<num.length && num[j]>num[i])
	        {
	            j++;
	        }
	        j--;
	        System.out.println("less than num[i],j=" + j +"  num[j]="+num[j]);
	        int temp = num[i];
	        num[i] = num[j];
	        num[j] = temp;
	    }
	    ptrArr(num);
	    System.out.println();
	    reverse(num, i+1);
	    
	    ptrArr(num);
	}
	
	private static void reverse(int[] num, int index)
	{
	    int l = index;
	    int r = num.length-1;
	    while(l<r)
	    {
	        int temp = num[l];
	        num[l] = num[r];
	        num[r] = temp;
	        l++;
	        r--;
	    }
	}
	/*
	 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
For example,
Given the following matrix:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
这道题是比较简单的数组操作，按螺旋顺序把数组读取并且放到结果数组中即可。基本思路跟Rotate Image有点类似，就是一层一层的处理，
每一层都是按照右下左上的顺序进行读取就可以。实现中要注意两个细节，一个是因为题目中没有说明矩阵是不是方阵，
因此要先判断一下行数和列数来确定螺旋的层数。
另一个是因为一层会占用两行两列，
如果是单数的，最后要将剩余的走完。所以最后还要做一次判断。因为每个元素访问一次，所以时间复杂度是O(m*n)，m，n是分别是矩阵的行数和列数，空间复杂度是O(1)
	 */
	
	public static ArrayList<Integer> spiralOrder(int[][] matrix) {
	    ArrayList<Integer> res = new ArrayList<Integer>();
	    
	    if(matrix == null || matrix.length==0 || matrix[0].length==0)
	        return res;
	    
	    int min = Math.min(matrix.length, matrix[0].length);
	    System.out.println("matrix.length:"+matrix.length+ "matrix[0].len"+ matrix[0].length);
	   
	    int levelNum = min/2;
	    System.out.println("levelNum:"+levelNum);
	    
	    for(int level=0;level<levelNum;level++)
	    {
	    	System.out.println("level:"+level);
	    	System.out.println("matrix[0].length-level-1:"+ (matrix[0].length-level-1) );
	        for(int i=level;i<matrix[0].length-level-1;i++)
	        {
	            res.add(matrix[level][i]);
	            System.out.println(res);
	        }
	        System.out.println();
	        for(int i=level;i<matrix.length-level-1;i++)
	        { //The column 
	            res.add(matrix[i][matrix[0].length-1-level]);
	            System.out.println(res);
	        }
	        System.out.println();
	        for(int i=matrix[0].length-1-level;i>level;i--)
	        {
	            res.add(matrix[matrix.length-1-level][i]);
	            System.out.println(res);
	        }
	        System.out.println();
	        for(int i=matrix.length-1-level;i>level;i--)
	        {
	            res.add(matrix[i][level]);
	            System.out.println(res);
	        }
	        System.out.println();
	    }
	    
	    if(min%2==1)
	    {
	        if(matrix.length < matrix[0].length)
	        {
	            for(int i=levelNum; i<matrix[0].length-levelNum;i++)
	            {
	                res.add(matrix[levelNum][i]);
	            }
	        }
	        else
	        {
	            for(int i=levelNum; i<matrix.length-levelNum;i++)
	            {
	                res.add(matrix[i][levelNum]);
	            }
	        }
	    }
	    
	    System.out.println(res);
	    return res;
	}
	/*
	 * 
	 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.
    red:0,white:1, blue:2
    [1,2,0,2,0,0] -> [0,0,0,1,2,2]
    []
    [2]
    [2,0]
    cnt[0]=0;
    j=1,2,3, count of 
    red: i=0,1,2, cnt[red]=3
    white:i=3, cnt[white]=1
    blue: i=4,5 ,cnt[blue]=2
    这道题也是数组操作的题目，其实就是要将数组排序，只是知道数组中只有三个元素0,1,2。
    熟悉计数排序的朋友可能很快就发现这其实就是使用计数排序，元素空间只需要三个元素即可。
	 */
    public static void sortColors(int[] A) {
        int[] cnt=new int[3];
        //count the number of each color
        for(int k=0;k<A.length; k++){
            cnt[A[k]]++;
        }
        //System.out.println(cnt[1]);
        
        int cur=0,i=0;
        
        for(int j=0;j<=2;j++){
        	System.out.println("j:"+j);
        	System.out.println("cnt[j]:"+ cnt[j]);
        	System.out.println(cur);
            for(i=cur;i<cur+cnt[j];i++){
                A[i]=j;
            }
            cur = cur+cnt[j];
            System.out.println(cur);
        }
        System.out.println();
        for(int k=0;k<A.length; k++){
        	System.out.println(A[k]);
        }
    }
    
    public static int findPeakElement(int[] num) {
        if(num.length ==1) return 0;
        if(num.length ==2) return num[0]>num[1]? 0:1;
        
        int res = interFindPeak(0,num.length-1,num);
        System.out.println(res);
        return res;
    }
    private static int interFindPeak(int l,int r,int[] num){
        if(l==r) { //one element
            return l;    
        };
        if((r-l)==1) {// two elements
            return num[l]>num[r]? l:r;
        }

        //if(l>r) //
        // more than two elements
        int m = (l+r)/2; //0+2/2 =1
        
        if(num[m]>num[m-1] && num[m]>num[m+1]) return m;
        else if(num[m]<num[m+1]) return interFindPeak(m+1,r,num); //go right
        //if(num[m]<num[m-1]) return interFindPeak(l,m-1); // go left
        else return interFindPeak(l,m-1,num); // go left
        //return m;
    }
    
    public static ArrayList<String[]> solveNQueens(int n) {
        ArrayList<String[]> res = new ArrayList<String[]>();
        solveNQueenshelper(n,0,new int[n], res);
        System.out.println("res:"+res);
        return res;
    }
    
    private static void solveNQueenshelper(int n, int row, int[] columnForRow, ArrayList<String[]> res)
    {
    	System.out.println();
    	System.out.println("row="+row);
    	ptrArr(columnForRow);
        if(row == n)
        {
            String[] item = new String[n];
            for(int i=0;i<n;i++)
            {
                StringBuilder strRow = new StringBuilder();
                for(int j=0;j<n;j++)
                {
                    if(columnForRow[i]==j)
                        strRow.append('Q');
                    else
                        strRow.append('.');
                }
                item[i] = strRow.toString();
            }
            
            res.add(item);
            return;
        }
        for(int i=0;i<n;i++)
        {
        	System.out.println("try i="+i);
            columnForRow[row] = i;
            if(check(row,columnForRow))
            {
            	System.out.println("cur row is ok,go to next row:"+(row+1));
            	ptrArr(columnForRow);
            	solveNQueenshelper(n,row+1,columnForRow,res);
            }
        }
    }
    private static boolean check(int row, int[] columnForRow)
    {
        for(int i=0;i<row;i++)
        {
            if(columnForRow[row]==columnForRow[i] || Math.abs(columnForRow[row]-columnForRow[i])==row-i)
                return false;
        }
        return true;
    }
    //////////////////
    
    public static ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num==null || num.length==0)
            return res;
        permutehelper(num, new boolean[num.length], new ArrayList<Integer>(), res, 0);
        return res;
    }
    private static void permutehelper(int[] num, boolean[] used, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res, int level)
    {
        if(item.size() == num.length)
        {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for(int i=0;i<num.length;i++)
        {
        	System.out.println("level="+level+" i="+i + " used[i]=" + used[i]);
            if(!used[i])
            {
                used[i] = true;
                item.add(num[i]);
                System.out.println("item="+item+ "go to level:"+(level+1));
                permutehelper(num, used, item, res,level+1);
                item.remove(item.size()-1);
                System.out.println("item="+item+ "recover from level:" + (level+1));
                used[i] = false;
            }
        }
    }
    
    /////////////subset//////////////
    /*
     * Given a collection of integers that might contain duplicates, S, return all possible subsets.
Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,2], a solution is:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
     */   
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        res.add(new ArrayList<Integer>());
        if(num==null || num.length==0)
            return res;
        Arrays.sort(num);
        int start = 0;
        for(int i=0;i<num.length;i++)
        {
        	System.out.println("i="+i);
            int size = res.size();
            System.out.println("size="+size);
            
            for(int j=start;j<size;j++)
            {
            	System.out.println("j="+j);
                ArrayList<Integer> newItem = new ArrayList<Integer>(res.get(j));
                newItem.add(num[i]);
                res.add(newItem);
            }
            if(i<num.length-1 && num[i]==num[i+1])
            {
            	System.out.println("num[i]==num[i+1]");
                start = size;
            }
            else
            {
                start = 0;
            }
        }
        return res;
    }
    

    /*
     * Given a set of distinct integers, S, return all possible subsets.
Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,3], a solution is:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
     */
	 //private static int level=0; 
	 public static ArrayList<ArrayList<Integer>> subsets(int[] n) {
		    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		    if(n==null)
		        return res;
		    Arrays.sort(n);
		    subsetsHelp(n,0,new ArrayList<Integer>(), res,0);
		    return res;
		}
		private static void subsetsHelp(int[] n, int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res, int level)
		{
			System.out.println();
			System.out.println("level="+level+" start="+start);
			
		    res.add(new ArrayList<Integer>(item));
		    
		    System.out.println("item="+item);
		    System.out.println("res="+res);
		    
		    for(int i=start;i<n.length;i++)
		    {
		    	System.out.println("level="+level+" i="+i);
		        item.add(n[i]);
		        System.out.println("add n[i], item="+item + "enter to next level.");
		        subsetsHelp(n,i+1,item,res,level+1);
		        item.remove(item.size()-1);
		        System.out.println("remove,item="+item+" recover from next level.");
		    }
		}
    
		//
	public static ArrayList<ArrayList<Integer>> subsetsRecur(int[] num) {
	    if(num == null)
	        return null;
	    Arrays.sort(num);
	    return subsetsRurHel(num, num.length-1);
	}
	private static ArrayList<ArrayList<Integer>> subsetsRurHel(int[] num, int index)
	{
		System.out.println("index:"+index);
	    if(index == -1)
	    {
	        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	        ArrayList<Integer> elem = new ArrayList<Integer>();
	        res.add(elem);
	        System.out.println("index:"+index+"return:"+res);
	        return res;
	    }
	    ArrayList<ArrayList<Integer>> res = subsetsRurHel(num,index-1);
	    //System.out.println(res);
	    System.out.println();
	    
	    int size = res.size();
	    
	    for(int i=0;i<size;i++)
	    {
	        ArrayList<Integer> elem = new ArrayList<Integer>(res.get(i));
	        System.out.println("elem:"+elem);
	        elem.add(num[index]);
	        System.out.println("Aft add num[idx],elem:"+elem);
	        res.add(elem);
	        System.out.println("Aft add num[idx],res:"+res);
	    }
	    System.out.println("index:"+index+"return:"+res);
	    return res;
	}
	
	public static ArrayList<ArrayList<Integer>> subsetsInte(int[] S) {
	     ArrayList<ArrayList<Integer>> res = new  ArrayList<ArrayList<Integer>>();
	     //先加入一个空list
	     res.add(new ArrayList<Integer>());
	     //System.out.println(res);
	     
	     if(S == null || S.length == 0)
	        return res;
	    //将元素排序 
	    Arrays.sort(S);
	    System.out.println(S.length);
	    //[1,2,3] ->[14,24,34]
	    for(int i=0;i<S.length;i++)
	    {
	    	System.out.println();
	    	System.out.println("i:"+i+" res:"+res);
	    	
	        int size = res.size();
	        
	        for(int j=0;j<size;j++)
	        {
	            ArrayList<Integer> item = new ArrayList<Integer>(res.get(j));
	            System.out.println("j="+j+" item:"+item);
	            //Add s[i] to every elem in old res. and then add this item to new res. 
	            item.add(S[i]);
	            System.out.println("j="+j+" aft add S[i] item:"+item);
	            res.add(item);
	            System.out.println("j="+j+" aft addS[i] res:"+res);
	        }
	    }
	    
	    return res;
	}
	
	/*
	 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
	 */
	public static ArrayList<ArrayList<Integer>> combine(int n, int k) {
	    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	    if(n<=0 || n<k)
	        return res;
	    combineHelp(n,k,1,new ArrayList<Integer>(), res);
	    System.out.println(res);
	    return res;
	}

	private static void combineHelp(int n, int k, int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res)
	{
		if(item.size()==k)
	    {
	        res.add(new ArrayList<Integer>(item));
	        System.out.println(item);
	        return;
	    }
	
	    for(int i=start;i<=n;i++)
	    {
	    	//System.out.println("start:"+start);
	        item.add(i);
	        //System.out.println("item pre:"+item);
	        combineHelp(n,k,i+1,item,res);
	        item.remove(item.size()-1);
	        //System.out.println("item aft:"+item);
	    }
	}


	/*
	 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C 
	 * where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
	 */
	public static ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
	    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	    if(candidates == null || candidates.length==0)
	        return res;
	    // sort the array first
	    Arrays.sort(candidates);
	    combinationSumHelp(candidates,0,target,new ArrayList<Integer>(),res,0);
	    return res;
	}
	/*2,3,6,7 
	 *2,2,3
	 *7
	 * */
	private static void combinationSumHelp(int[] candidates, int start, int target, ArrayList<Integer> item, 
	ArrayList<ArrayList<Integer>> res,int level)
	{
		System.out.println("level=" + level+ " start=" +start);
		// Cann't sum to target
	    if(target<0) {
	    	System.out.println("Target<0,item="+item);
	    	return;
	    }
	    // Can sum to target,add to res.NOTE:NEW a new list
	    if(target==0)
	    {
	    	System.out.println("Target==0,item="+item);
	        res.add(new ArrayList<Integer>(item));
	        return;
	    }
	    
	    for(int i=start;i<candidates.length;i++)
	    {
	    	System.out.println("level=" + level + "i=" + i);
	        if(i>0 && candidates[i]==candidates[i-1])
	            continue;
	        
	        item.add(candidates[i]); //Add cur candidate elem
	        System.out.println("After add c[i],item="+item);
	        //sum=target-cand[i],try again,can use cand[i] again.
	        combinationSumHelp(candidates,i,target-candidates[i],item,res,level+1);
	        System.out.println(item);
	        //Recover the item, prepare for next try
	        item.remove(item.size()-1);
	        System.out.println("After recover,item="+item);
	    }
	}
	public static ArrayList<ArrayList<Integer>> combinationSumII(int[] candidates, int target) {
	    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	    if(candidates == null || candidates.length==0)
	        return res;
	    // sort the array first
	    Arrays.sort(candidates);
	    combinationSumHelpII(candidates,0,target,new ArrayList<Integer>(),res,0);
	    return res;
	}
	/*2,3,6,7 
	 *2,2,3
	 *7
	 * */
	private static void combinationSumHelpII(int[] candidates, int start, int target, ArrayList<Integer> item, 
	ArrayList<ArrayList<Integer>> res,int level)
	{
		System.out.println("level=" + level+ " start=" +start);
		// Cann't sum to target
	    if(target<0) {
	    	System.out.println("Target<0,item="+item);
	    	return;
	    }
	    // Can sum to target,add to res.NOTE:NEW a new list
	    if(target==0)
	    {
	    	System.out.println("Target==0,item="+item);
	        res.add(new ArrayList<Integer>(item));
	        return;
	    }
	    
	    for(int i=start;i<candidates.length;i++)
	    {
	    	System.out.println("level=" + level + "i=" + i);
	        if(i>0 && candidates[i]==candidates[i-1])
	            continue;
	        
	        item.add(candidates[i]); //Add cur candidate elem
	        System.out.println("After add c[i],item="+item);
	        //sum=target-cand[i],try again,can use cand[i] again.
	        combinationSumHelpII(candidates,i+1,target-candidates[i],item,res,level+1);
	        System.out.println(item);
	        //Recover the item, prepare for next try
	        item.remove(item.size()-1);
	        System.out.println("After recover,item="+item);
	    }
	}
		
	
	
	public static void ptrArr(int[] num){
	    for(int k=0;k<num.length;k++){
	    	System.out.print(num[k]);
	    }
	    System.out.println();
	}
	
}
