import java.io.*;
import java.util.*;

public class com {
	
	public static void main(String[] args){
		String s=new String() ;
		ArrayList<StringBuilder> res = new ArrayList<StringBuilder>();
	}


//这是一道非常经典的题目，brute force时间复杂度为O(n^2), 对每一对pair两两比较。 优化的方法一般有两种，
	//第一种是用哈希表，时间复杂度为O(n),同时空间复杂度也是O(n),代码如下：
public int[] twoSum(int[] numbers, int target) {  
    int[] res = new int[2];  
    if(numbers==null || numbers.length<2)  
        return null;  
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();  
    for(int i=0;i<numbers.length;i++)  
    {  
        if(map.containsKey(target-numbers[i]))  
        {  
            res[0]=map.get(target-numbers[i])+1;  
            res[1]=i+1;  
            return res;  
        }  
        map.put(numbers[i],i);  
    }  
    return null;  
}  
//第二种解法是先对数组进行排序，然后使用夹逼的方法找出满足条件的pair，原理是因为数组是有序的，那么假设当前结果比target大，那么左端序号右移只会使两个数的和更大，反之亦然。所以每次只会有一个选择，从而实现线性就可以求出结果。该算法的时间复杂度是O(nlogn+n)=O(nlogn)，空间复杂度取决于排序算法。代码如下：
public int[] twoSum2(int[] numbers, int target) {  
    int[] res = new int[2];  
    if(numbers==null || numbers.length<2)  
        return null;  
    Arrays.sort(numbers);  
    int l = 0;  
    int r = numbers.length-1;  
    while(l<r)  
    {  
        if(numbers[l]+numbers[r]==target)  
        {  
            res[0] = number[l];  
            res[1] = number[r];  
            return res;  
        }  
        else if(numbers[l]+numbers[r]>target)  
        {  
            r--;  
        }  
        else  
        {  
            l++;  
        }  
    }  
    return null;  
}  


/*
 * 4Sum Total Accepted: 25270 Total Submissions: 118210 My Submissions Question Solution 
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
 */
原题链接: http://oj.leetcode.com/problems/4sum/ 
这道题要求跟3Sum差不多，只是需求扩展到四个的数字的和了。我们还是可以按照3Sum中的解法，只是在外面套一层循环，相当于求n次3Sum。我们知道3Sum的时间复杂度是O(n^2)，所以如果这样解的总时间复杂度是O(n^3)。代码如下：
*/
public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {  
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
    if(num==null||num.length==0)  
        return res;  
    Arrays.sort(num);  
    for(int i=num.length-1;i>2;i--)  
    {  
        if(i==num.length-1 || num[i]!=num[i+1])  
        {  
            ArrayList<ArrayList<Integer>> curRes = threeSum(num,i-1,target-num[i]);  
            for(int j=0;j<curRes.size();j++)  
            {  
                curRes.get(j).add(num[i]);  
            }  
            res.addAll(curRes);  
        }  
    }  
    return res;          
}  
private ArrayList<ArrayList<Integer>> threeSum(int[] num, int end, int target)  
{  
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
    for(int i=end;i>1;i--)  
    {  
        if(i==end || num[i]!=num[i+1])  
        {  
            ArrayList<ArrayList<Integer>> curRes = twoSum(num,i-1,target-num[i]);  
            for(int j=0;j<curRes.size();j++)  
            {  
                curRes.get(j).add(num[i]);  
            }  
            res.addAll(curRes);  
        }  
    }  
    return res;  
}  
private ArrayList<ArrayList<Integer>> twoSum(int[] num, int end, int target)  
{  
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
    int l=0;  
    int r=end;  
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
        else if(num[l]+num[r]>target)  
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
/*上述这种方法比较直接，根据3Sum的结果很容易进行推广。那么时间复杂度能不能更好呢？其实我们可以考虑用二分法的思路，如果把所有的两两pair都求出来，
 * 然后再进行一次Two Sum的匹配，我们知道Two Sum是一个排序加上一个线性的操作，并且把所有pair的数量是O((n-1)+(n-2)+...+1)=O(n(n-1)/2)=O(n^2)。
 * 所以对O(n^2)的排序如果不用特殊线性排序算法是O(n^2*log(n^2))=O(n^2*2logn)=O(n^2*logn)，算法复杂度比上一个方法的O(n^3)是有提高的。
思路虽然明确，不过细节上会多很多情况要处理。首先，我们要对每一个pair建一个数据结构来存储元素的值和对应的index，
这样做是为了后面当找到合适的两对pair相加能得到target值时看看他们是否有重叠的index，如果有说明它们不是合法的一个结果，
因为不是四个不同的元素。接下来我们还得对这些pair进行排序，所以要给pair定义comparable的函数。
最后，当进行Two Sum的匹配时因为pair不再是一个值，所以不能像Two Sum中那样直接跳过相同的，每一组都得进行查看，这样就会出现重复的情况，
所以我们还得给每一个四个元素组成的tuple定义hashcode和相等函数，以便可以把当前求得的结果放在一个HashSet里面，这样得到新结果如果是重复的就不加入结果集了。
代码如下：
*/
private ArrayList<ArrayList<Integer>> twoSum(ArrayList<Pair> pairs, int target){  
    HashSet<Tuple> hashSet = new HashSet<Tuple>();  
    int l = 0;  
    int r = pairs.size()-1;  
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
    while(l<r){  
        if(pairs.get(l).getSum()+pairs.get(r).getSum()==target)  
        {  
            int lEnd = l;  
            int rEnd = r;  
            while(lEnd<rEnd && pairs.get(lEnd).getSum()==pairs.get(lEnd+1).getSum())  
            {  
                lEnd++;  
            }  
            while(lEnd<rEnd && pairs.get(rEnd).getSum()==pairs.get(rEnd-1).getSum())  
            {  
                rEnd--;  
            }  
            for(int i=l;i<=lEnd;i++)  
            {  
                for(int j=r;j>=rEnd;j--)  
                {  
                    if(check(pairs.get(i),pairs.get(j)))  
                    {  
                        ArrayList<Integer> item = new ArrayList<Integer>();  
                        item.add(pairs.get(i).nodes[0].value);  
                        item.add(pairs.get(i).nodes[1].value);  
                        item.add(pairs.get(j).nodes[0].value);  
                        item.add(pairs.get(j).nodes[1].value);  
                        //Collections.sort(item);  
                        Tuple tuple = new Tuple(item);  
                        if(!hashSet.contains(tuple))  
                        {  
                            hashSet.add(tuple);  
                            res.add(tuple.num);  
                        }  
                    }                          
                }  
            }  
            l = lEnd+1;  
            r = rEnd-1;  
        }  
        else if(pairs.get(l).getSum()+pairs.get(r).getSum()>target)  
        {  
            r--;  
        }  
        else{  
            l++;  
        }  
    }  
    return res;  
}  
private boolean check(Pair p1, Pair p2)  
{  
    if(p1.nodes[0].index == p2.nodes[0].index || p1.nodes[0].index == p2.nodes[1].index)  
        return false;  
    if(p1.nodes[1].index == p2.nodes[0].index || p1.nodes[1].index == p2.nodes[1].index)  
        return false;  
    return true;  
}  
//第二种方法比第一种方法时间上还是有提高的，其实这道题可以推广到k-Sum的问题，基本思想就是和第二种方法一样进行二分，
//然后两两结合，不过细节就非常复杂了（这点从上面的第二种解法就能看出来），所以不是很适合在面试中出现，有兴趣的朋友可以进一步思考或者搜索网上材料哈。




/*
 * Single Number Total Accepted: 53712 Total Submissions: 117483 My Submissions Question Solution 
Given an array of integers, every element appears twice except for one. Find that single one.
Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
/*这道题目跟Single Number II比较类似，区别只是这道题目每个元素出现两次，而不是三次。我们仍然可以按照Single Number II中的两种方法来，
都只是把取余3改成取余2即可。我们就列举一下第二种方法的代码如下：
*/
public int singleNumber(int[] A) {  
    int[] digits = new int[32];  
    for(int i=0;i<32;i++)  
    {  
        for(int j=0;j<A.length;j++)  
        {  
            digits[i] += (A[j]>>i)&1;  
        }  
    }  
    int res = 0;  
    for(int i=0;i<32;i++)  
    {  
        res += (digits[i]%2)<<i;  
    }  
    return res;  
}  

//上面方法的时间复杂度仍然是O(n)，空间是一个32个元素的数组，是O(1)的复杂度。
//按照上面的方法，虽然空间复杂度是O(1)的，不过还是需要一个32个元素的数组。还有另一种方法是利用每个元素出现两次，以及位操作异或的性质来解决这个问题。因为两个相同的元素异或结果是0，利用这个特点我们可以对所有数组元素进行异或，如果出现两次的元素就会自行抵消，而最终剩下的元素则是出现一次的元素。这个方法只需要一次扫描，即O(n)的时间复杂度，而空间上也不需要任何额外变量，比起上面的方法更优。代码如下：
public int singleNumber2(int[] A) {  
    if(A==null || A.length==0)  
        return 0;  
    int res = A[0];  
    for(int i=1;i<A.length;i++)  
    {  
        res ^= A[i];  
    }  
    return res;  
}  
//上面的方法实现非常简练，不过也相当取巧，可能没有准备过比较难在面试中想到。相对而言第一种方法是比较通用的，无论出现多少次都是适用的。第二种方法属于对于出现两次这种特殊情况才能使用，不过方法巧妙，有点体现位运算的精髓，所以个人还是挺喜欢的哈。

/*
 * 
 这道题是Sudoku Solver的一个子问题，在解数独的时候我们需要验证当前数盘是否合法。其实思路比较简单，也就是用brute force。对于每一行，每一列，每个九宫格进行验证，总共需要27次验证，每次看九个元素。所以时间复杂度就是O(3*n^2), n=9。代码如下： 
*/

public boolean isValidSudoku(char[][] board) {  
    if(board==null || board.length!=9 || board[0].length!=9)  
        return false;  
    for(int i=0;i<9;i++)  
    {  
        boolean[] map = new boolean[9];  
        for(int j=0;j<9;j++)  
        {  
            if(board[i][j] != '.')  
            {  
                if(map[(int)(board[i][j]-'1')])  
                {  
                    return false;  
                }  
                map[(int)(board[i][j]-'1')] = true;  
            }  
        }  
    }  
    for(int j=0;j<9;j++)  
    {  
        boolean[] map = new boolean[9];  
        for(int i=0;i<9;i++)  
        {  
            if(board[i][j] != '.')  
            {  
                if(map[(int)(board[i][j]-'1')])  
                {  
                    return false;  
                }  
                map[(int)(board[i][j]-'1')] = true;  
            }  
        }  
    }          
    for(int block=0;block<9;block++)  
    {  
        boolean[] map = new boolean[9];  
        for(int i=block/3*3;i<block/3*3+3;i++)  
        {  
            for(int j=block%3*3;j<block%3*3+3;j++)  
            {  
                if(board[i][j] != '.')  
                {  
                   if(map[(int)(board[i][j]-'1')])  
                   {  
                      return false;  
                   }  
                   map[(int)(board[i][j]-'1')] = true;      
                }  
            }  
        }  
    }  
    return true;  
}  
//这道题其实没有什么好的算法，基本上就是遍历去检查，实现上就是数组的操作，属于Sudoku Solver的subroutine，但是在Sudoku Solver中实现上又可以进行优化，没必要每次检查整个board，只需要看当前加进去的数就可以，有兴趣的朋友可以看看哈。


}