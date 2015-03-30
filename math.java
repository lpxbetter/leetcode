import java.io.*;
import java.util.*;

public class math {
	
	public static void main(String[] args){
		//addBinary("1","1");
		String line="13.7";
		String[] items = line.split("\\.");

		//out(items[1]);
		fractionToDecimal(1,8);
		//int res =reverse(-2147483647);
		//System.out.println(res);
		//boolean res = isPalindrome(1000021);
		//System.out.println(res);
		//int rst = divide(100,3);
		//System.out.println(rst);
	}
	
	
	/*
	Pow(x, n) Total Accepted: 37352 Total Submissions: 140771 My Submissions Question Solution 
	Implement pow(x, n).
	这道题是一道数值计算的题目，因为指数是可以使结果变大的运算，所以要注意越界的问题。如同我在Sqrt(x)这道题中提到的，
	一般来说数值计算的题目可以用两种方法来解，一种是以2为基进行位处理的方法，另一种是用二分法。这道题这两种方法都可以解，下面我们分别介绍。
第一种方法在Divide Two Integers使用过，就是把n看成是以2为基的位构成的，因此每一位是对应x的一个幂数，然后迭代直到n到最高位。
比如说第一位对应x，第二位对应x*x,第三位对应x^4,...,第k位对应x^(2^(k-1)),可以看出后面一位对应的数等于前面一位对应数的平方，
所以可以进行迭代。因为迭代次数等于n的位数，所以算法的时间复杂度是O(logn)。代码如下：
*/
	// http://blog.csdn.net/linhuanmars/article/details/20092829
	

/*
	接下来我们介绍二分法的解法，如同我们在Sqrt(x)的方法。不过这道题用递归来解比较容易理解，把x的n次方划分成两个x的n/2次方相乘，
	然后递归求解子问题，结束条件是n为0返回1。因为是对n进行二分，算法复杂度和上面方法一样，也是O(logn)。代码如下：
	*/
	double pow(double x, int n) {
	    if (n == 0) return 1.0;
	    double half = pow(x, n/2);
	    if (n%2 == 0)
	    {
	        return half*half;
	    }
	    else if (n>0)
	    {
	        return half*half*x;
	    }
	    else //n<0, 
	    {
	        return half/x*half;
	    }
	}
	/*
	 * 以上代码比较简洁，不过这里有个问题是没有做越界的判断，因为这里没有统一符号，所以越界判断分的情况比较多，
	 * 不过具体也就是在做乘除法之前判断这些值会不会越界，有兴趣的朋友可以自己填充上哈，
	 * 这里就不写太啰嗦的代码了。不过实际应用中健壮性还是比较重要的，而且递归毕竟会占用递归栈的空间，所以我可能更推荐第一种解法。
	 */
	
	public static int divide(int dividend, int divisor) {
	    if(divisor == 0)
	    {
	        return Integer.MAX_VALUE;
	    }
	    boolean isNeg = (dividend^divisor)>>>31 == 1;
	    int res = 0;
	    if(dividend == Integer.MIN_VALUE)
	    {
	        dividend += Math.abs(divisor);
	        if(divisor == -1)
	        {
	            return Integer.MAX_VALUE;
	        }
	        res++;
	    }
	    if(divisor == Integer.MIN_VALUE)
	    {
	        return res;
	    }
	    dividend = Math.abs(dividend);
	    divisor = Math.abs(divisor);
	    int digit = 0;
	    while(divisor <= (dividend>>1))
	    {
	        divisor <<= 1;
	        digit++;
	    }
	    while(digit>=0)
	    {
	        if(dividend>=divisor)
	        {
	            res += 1<<digit;
	            dividend -= divisor;
	        }
	        divisor >>= 1;
	        digit--;
	    }
	    return isNeg?-res:res;
	}
	
	/*
	 * Divide Two Integers Total Accepted: 27888 Total Submissions: 176415 My Submissions Question Solution 
Divide two integers without using multiplication, division and mod operator.
If it is overflow, return MAX_INT.
这道题属于数值处理的题目，对于整数处理的问题，在Reverse Integer中我有提到过，比较重要的注意点在于符号和处理越界的问题。
对于这道题目，因为不能用乘除法和取余运算，我们只能使用位运算和加减法。比较直接的方法是用被除数一直减去除数，直到为0。
这种方法的迭代次数是结果的大小，即比如结果为n，算法复杂度是O(n)。
那么有没有办法优化呢？ 这个我们就得使用位运算。我们知道任何一个整数可以表示成以2的幂为底的一组基的线性组合，
即num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n。基于以上这个公式以及左移一位相当于乘以2，我们先让除数左移直到大于被除数之前得到一个最大的基。
然后接下来我们每次尝试减去这个基，如果可以则结果增加加2^k,然后基继续右移迭代，直到基为0为止。因为这个方法的迭代次数是按2的幂直到超过结果，
所以时间复杂度为O(logn)。代码如下：

这种数值处理的题目在面试中还是比较常见的，类似的题目有Sqrt(x)，Pow(x, n)等。上述方法在其他整数处理的题目中也可以用到，大家尽量熟悉实现这些问题。
	 */
	public int divideTwoIntegers(int dividend, int divisor) {
	    if(divisor == 0)
	    {
	        return Integer.MAX_VALUE;
	    }
	    boolean isNeg = (dividend^divisor)>>>31 == 1;
	    int res = 0;
	    if(dividend == Integer.MIN_VALUE)
	    {
	        dividend += Math.abs(divisor);
	        if(divisor == -1)
	        {
	            return Integer.MAX_VALUE;
	        }
	        res++;
	    }
	    if(divisor == Integer.MIN_VALUE)
	    {
	        return res;
	    }
	    dividend = Math.abs(dividend);
	    divisor = Math.abs(divisor);
	    int digit = 0;
	    while(divisor <= (dividend>>1))
	    {
	        divisor <<= 1;
	        digit++;
	    }
	    while(digit>=0)
	    {
	        if(dividend>=divisor)
	        {
	            res += 1<<digit;
	            dividend -= divisor;
	        }
	        divisor >>= 1;
	        digit--;
	    }
	    return isNeg?-res:res;
	}
	
	
	
	public static String multiply2(String num1, String num2) {
	    if(num1 == null || num2 == null || num1.length()==0 || num2.length()==0)
	        return "";
	    if(num1.charAt(0)=='0')
	        return "0";
	    if(num2.charAt(0)=='0')
	        return "0";
	    StringBuilder res = new StringBuilder();
	    int num = 0;
	    for(int i=num1.length()+num2.length();i>0;i--)
	    {
	    	System.out.println();
	    	System.out.println("i="+i);
	    	
	        for(int j=Math.min(i-1,num1.length());j>0;j--)
	        {
	        	System.out.println("j="+j);
	        	
	            if(i-j<=num2.length())
	            {
	            	System.out.println("j-1="+(j-1) + " i-1-j=" + (i-1-j));
	                num += (int)(num1.charAt(j-1)-'0') * (int)(num2.charAt(i-1-j)-'0');
	            }
	        }
	        if(i!=1 || num>0)
	            res.append(num%10);
	        num = num/10;
	    }
	    return res.reverse().toString();
	}
	
    public static String multiply(String num1, String num2) {
        int i=0;
        int j=0;
        int tmp=0;
        int carry=0;
         
        StringBuilder res = new StringBuilder(); 
        
        for(j=num2.length()-1;j>=0;j--){
        	carry =0;
        	StringBuilder strBld = new StringBuilder();
            for(int k=0; k< num2.length()-1-j; k++ ) strBld.append('0'); 
            
            for(i=num1.length()-1;i>=0;i--){
                tmp = (num1.charAt(i) -'0') * (num2.charAt(j) - '0') + carry;
                carry = tmp/10;
                strBld.append(tmp%10);
            }//end i interation
            
            if(carry>0) strBld.append(carry);
            System.out.println("strBld="+strBld);
            //strBld.reverse().toString();
            
            if(j==num2.length()-1) {
            	
            	res=strBld;
            }
            else {
            	res=add(res,strBld);
            }
            
        }// end j interation
        
        return res.reverse().toString();
    }//end func
    

    private static StringBuilder add(StringBuilder res, StringBuilder str){
    	System.out.println("res="+res);
    	System.out.println("str="+str);
        StringBuilder newRes = new StringBuilder();
        int i=0; 
        int carry=0;
        
        while(i<=res.length()-1 && i<=str.length()-1){
            int tmp= (res.charAt(i) - '0') + (str.charAt(i) -'0') + carry;
            carry = tmp/10;
            newRes.append( tmp %10 );
            i++;
        }
        
        while(i<=res.length()-1){
            carry = (res.charAt(i)-'0' + carry)/10;
            newRes.append( (res.charAt(i) -'0'+ carry)%10);
            i++;
        }
        
        while(i<=str.length()-1){
            carry = (str.charAt(i)-'0' + carry)/10;
            newRes.append( (str.charAt(i) -'0'+ carry)%10);
            i++;
        }
        
        if(carry>0) newRes.append(carry);
        return newRes;
        
    }//end func
	
    public static boolean isPalindrome(int x) {
        int range=1;
        //int tmp=x;
        while( range < x/10){
            range *= 10;
            //tmp = tmp/10;
        }
        System.out.println("range="+range);
        
        while(x>0){
            if(x/range != x%10 ) return false;
            x=(x%range) /10 ;
            range = range/100;
            System.out.println("x="+x);
        } 
        
        return true;
    }
    
    public static int reverse(int x) {
        long result=0;
        if(x==Integer.MIN_VALUE) return 0;  
        int num=Math.abs(x);
        int flag= x>0 ? 1:-1;
        while(num!=0){
            result=result*10+ num%10;
            System.out.println("result="+result);
            if(result>Integer.MAX_VALUE || result<Integer.MIN_VALUE)
            	{
            		System.out.println("overflow");
            		return 0; 
            	}
            num=num/10;
        }//end of while
        System.out.println(flag);
        return (int) (flag*result);
    }//end func
	
    public static String addBinary(String a, String b) {
        //if()
        int carry = 0;
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        
        while(i>=0 && j>=0){
            
            int digit = (int)(a.charAt(i)-'0') + (int)(b.charAt(j) -'0')  + carry;
            carry = digit / 2;
            //0,1,2,3
            res.append(digit%2);

            i--;
            j--;
        }
        while(i>=0) {
            int digit = (int)(a.charAt(i)-'0') + carry;
            carry = digit / 2;
            res.append(digit%2);
            i--;
        }
        while(j>=0) {
            int digit = (int)(b.charAt(j) -'0') + carry;
            carry = digit / 2;
            res.append(digit%2);
            j--;
        }  
        if(carry == 1) res.append(1);
        out(res.reverse().toString());
        return res.reverse().toString();
    }
	
	
	/*
	 * https://oj.leetcode.com/discuss/18780/my-java-solution-with-explanation-second-version
	 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
If the fractional part is repeating, enclose the repeating part in parentheses.
For example,
Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".

my trick: 1. use long to avoid overflow
2. use StringBuffer to build String
3.use class pair to keep track of numerator and denominator,
	if I find the same pair in the map,it means the fractional part is repeating.
4. using res=numeratorl/denominatorl;numeratorl=(numeratorl%denominatorl)*10; 
	to simulate division,res is the value to append to buffer.
	 */
        public static String fractionToDecimal(int numerator, int denominator) {
            //to avoid overflow
            long numeratorl=numerator, denominatorl=denominator;
            StringBuffer buffer=new StringBuffer();
            
            //handle negetives 
            if(numeratorl<0&&denominatorl>0)buffer.append('-');
            else if(numeratorl>0&&denominatorl<0)buffer.append('-');
            
            numeratorl=Math.abs(numeratorl);denominatorl=Math.abs(denominatorl);
            
            //map,key:  numerator ,because denominator never changes;value:position of numerator/denominator 
            HashMap<Long, Integer>map=new HashMap<>();
            
            //handle integer part
            long res=numeratorl/denominatorl;
            
            numeratorl=(numeratorl%denominatorl)*10;
            
            buffer.append(res);
            System.out.println(buffer.toString());
            
            if(numeratorl!=0)buffer.append('.');
            System.out.println(buffer.toString());
            
            //handle flaot part
            while(numeratorl != 0){
                res=numeratorl/denominatorl;
                if(map.get(numeratorl)!=null)
                {
                    //handle repaeting part
                    buffer.insert(map.get(numeratorl).intValue(), '(');
                    buffer.append(')');
                    System.out.println(buffer.toString());
                    break;
                }
                map.put(numeratorl, buffer.length());
                numeratorl=(numeratorl%denominatorl)*10;
                buffer.append(res);
                //System.out.println(buffer.toString());
                out(buffer.toString());
            }//end while
            
            System.out.println(buffer.toString()+ getLineNumber());
            return buffer.toString();
        }//end func
        
        
        
        
    	public static void out(String str){
    		System.out.println(str);
    	}
    	
        public static String getLineNumber() {
            int lineNum = Thread.currentThread().getStackTrace()[2].getLineNumber();
            return "\tLine:"+Integer.toString(lineNum);
        }
        

        
    }
    
  