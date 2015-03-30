import java.io.*;
import java.util.*;

public class strings {
	
	public static void main(String[] args){
		strings obj=new strings();
		//String res = intToRoman(348);//CCXLVIII
		//reverseWords(" the sky is     blue  ");
		//generateParenthesis(2);
		//String res=countAndSay(5);
		//String res = longestPalindrome("aba");
		//com.out(res);
		//String s=new String("9 man, a plan, a canal: Panam9");
		//boolean ret=isPalindrome(".");
		//System.out.println(ret);
		//ArrayList<String> resArr=restoreIpAddresses("25525511135");
		//System.out.println(resArr);
		//simplifyPath("/a/./b/../../c/");
		//String res=convertToTitle(52);
		//System.out.println(res);
		//System.out.println( (char)('A' -1 +2 ) + "" );
		//String res2=convertToTitle2(52);
		//System.out.println(res2);
		//System.out.println(multiply("236","45") );
		//System.out.println(isOneEditDistance("hit","hoty"));
		//System.out.println(lengthOfLongestSubstringTwoDistinct("abaac") );
		//System.out.println(reverseWords_m1("  the  sky is   blue "));
		//generateParenthesis(1);
		String[] strs=new String[2];
		strs[0]="acb";
		strs[1]="ac";
		//System.out.println(longestCommonPrefix(strs));
		//isInterleave("aabcc","dbbca","aadbbcbcac");
		String S="ADOBECODEBANC";
		String T="ABC";
		//minWindow(S,T);
		String s="barfoothefoobarman";
		String[] L= {"foo", "bar"};
		//findSubstring2(s,L);
		//System.out.println(reverseWords("The   sky is blue."));
		//System.out.println(isPalindrome("a b;A "));
		//System.out.println(lengthOfLastWord("b a "));
		//System.out.println(Integer.MAX_VALUE);
		//System.out.println(Integer.MIN_VALUE);
		//System.out.println(obj.reverseInt(-1534236469) );
		//System.out.println(obj.reverseInt(-123) );
//		System.out.println(obj.reverseWordsTwoPot("the sky  is blue") );
		System.out.println(obj.reverseWords("the sky is blue".toCharArray()) );
	}
	
	public static String reverseWordsTwoPot(String s) {
		s=s.trim();
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int i=n-1,j=n-1;
        //for(int i=n-1;i>=0;i--){
        while(i>=0 && j>=0){
        	while(j>=0 && s.charAt(j) !=' ') {
        		j--;
        	}
        	j++;
        	sb.append(s.substring(j,i+1));
        	System.out.println(sb.toString());
        	sb.append(" ");
        	j--;
        	while(j>=0 && s.charAt(j) == ' '){
        		j--;
        	}
        	i=j;
        }
		return sb.toString();
	}
	
	public String reverseWords(char[] s) {
		reversePart(s,0,s.length);
		System.out.println(new String(s));
		for(int i=0,j=0;j<=s.length;j++){
			if( j==s.length || s[j]==' '){
				reversePart(s,i,j);
				i=j+1;
			}
		}
		return new String(s);
	}
	
	
	
	
	private void reversePart(char[] s, int beg, int end ){
		int i=beg,j=end-1;
		char temp;
		while(i<j){
			temp=s[i];
			s[i]=s[j];
			s[j]=temp;	
			i++;j--;			
		}
	}
	
    public int reverse(int x) {
        int result=0;
        while(x!=0){
        	//result=result*10+x%10;
        	//System.out.println(":"+isNeg*(result*10+x%10));
            int newResult=result*10+x%10;
            if((newResult-x%10)/10 != result) return 0;
            result = newResult;;
        	x=x/10;
        }
       // System.out.println(result);

        return result;
    }
	
	public static boolean isUniq(String s){
		if(s.length() > 256 )  return false;
		boolean[] isIN=new boolean[256];
		
		for(int i=0;i<s.length();i++){
			if( isIN[s.charAt(i)]) return false;
			isIN[s.charAt(i)] = true;
		}
		return true;
	}
	
    public static int lengthOfLastWord(String s) {
        if(s==null) return 0;
        s=s.trim();
        if(s.length() == 0) return 0;
        //1) no space, just one word
        //2) multiple words
        //3)"I do  "  ; space in the end
        int len =0;
        for(int i=s.length()-1;i>=0;i--){
            while(i>=0 && s.charAt(i)!=' ') {
            	System.out.println(s.charAt(i));
                i--;
                len++;
            }
            break;
        }
        return len;
    }
    
    
    public static int lengthOfLastWordPre(String s) {
        s=s.trim();
        if(s.length()==0) return 0;
        // until s[i-1]==' ',break,the cnt is the len of last word
        // if just one word,until i==0,
        int cnt=0;
        for(int i=s.length()-1;i>=0;i--){
            cnt++;
            
            if(i>=1 && s.charAt(i-1)==' ') break;
        }
        return cnt;
    }
    
    public static boolean isPalindrome(String s) {
        int i=0;
        int j=s.length()-1;
        while(i<j){
            while(i<s.length() && !isValid(s.charAt(i)) ) i++;
            while(j>=0 && !isValid(s.charAt(j))) j--;
            
            //if(isValid(s.get(i)) && isValid(s.get(j)) ){
            System.out.printf("i=%d,j=%d\n",i,j);
            if(i>j) return true;
            if(!isEqual(s.charAt(i),s.charAt(j)) ){
                return false;
            }
            //}
            i++;
            j--;
        }
        System.out.printf("i=%d,j=%d\n",i,j);
        return true;
    }
    public static boolean isValid(char c){
        // a- z; A-Z; 0-9; 
        if(c>='a'&&c<='z' || (c>='A'&&c<='Z') || (c>='0'&&c<='9') ) {
            return true;
        }
        return false;
    }
    public static boolean isEqual(char c1, char c2){
        if(c1>='A'&&c1<='Z'){
            c1=(char)(c1-'A'+'a');
        }
        if(c2>='A' && c2<='Z'){
            c2=(char)(c2-'A'+'a');
        }
        return c1==c2;
    }
    
	public static String reverseWords(String s) {
        StringBuilder temp = new StringBuilder();
        int n = s.length();
        for(int i=0;i<n;i++){
            while(i<n&&s.charAt(i)!=' '){
            	//temp.in
            	temp.insert(0,s.charAt(i));
            	i++;
            }
            
            if(i<n && s.charAt(i)==' ') {
            	temp.insert(0, ' ');
            	i++;
            }
            while(i<n && s.charAt(i)==' ') i++;
            i--;
        }
        System.out.println("temp="+temp);
        
        int l=0,r=0,next;
        char tmp;
        int m = temp.length();
        for(int i=0;i<temp.length();i++){
        	System.out.println(i);
            while(i<m && temp.charAt(i)!=' ') {
            	r++;
            	i++;
            }
            next = r+1;
            r--;
            while(l<r){
                tmp=temp.charAt(l);
                temp.setCharAt(l++,temp.charAt(r));
                temp.setCharAt(r--,tmp);
            }
            l=r=next;
        }
        System.out.println("temp2="+temp);
		return temp.toString();
    }
	
	public static ArrayList<Integer> findSubstring2(String S, String[] L) {
	    // Note: The Solution object is instantiated only once and is reused by each test case.
	    ArrayList<Integer> res = new ArrayList<Integer>();
	    if(S==null || S.length()==0 || L==null || L.length==0)
	        return res;
	    HashMap<String,Integer> map = new HashMap<String,Integer>();
	    for(int i=0;i<L.length;i++)
	    {
	        if(map.containsKey(L[i]))
	        {
	            map.put(L[i],map.get(L[i])+1);
	        }
	        else
	        {
	            map.put(L[i],1);
	        }
	    }
	    System.out.println(map);
	    
	    for(int i=0;i<L[0].length();i++)
	    {
	        HashMap<String,Integer> curMap = new HashMap<String,Integer>();
	        int count = 0;
	        int left = i;
	        for(int j=i;j<=S.length()-L[0].length();j+=L[0].length())
	        {
	            String str = S.substring(j,j+L[0].length());
	            
	            if(map.containsKey(str))
	            {
	                if(curMap.containsKey(str))
	                    curMap.put(str,curMap.get(str)+1);
	                else
	                    curMap.put(str,1);
	                if(curMap.get(str)<=map.get(str))
	                    count++;
	                else
	                {
	                    while(curMap.get(str)>map.get(str))
	                    {
	                        String temp = S.substring(left,left+L[0].length());
	                        if(curMap.containsKey(temp))
	                        {
	                            curMap.put(temp,curMap.get(temp)-1);
	                            if(curMap.get(temp)<map.get(temp))
	                                count--;
	                        }
	                        left += L[0].length();
	                    }
	                }
	                if(count == L.length)
	                {
	                    res.add(left);
	                    //if(left<)
	                    String temp = S.substring(left,left+L[0].length());
	                    if(curMap.containsKey(temp))
	                        curMap.put(temp,curMap.get(temp)-1);
	                    count--;
	                    left += L[0].length();
	                }
	            }
	            else
	            {
	                curMap.clear();
	                count = 0;
	                left = j+L[0].length();
	            }
	        }
	    }
	    return res;
	}
	
	/*
	 * 这道题是字符串处理的题目，和Substring with Concatenation of All Words思路非常类似，同样是建立一个字典，然后维护一个窗口。区别是在这道题目中，因为可以跳过没在字典里面的字符（也就是这个串不需要包含且仅仅包含字典里面的字符，有一些不在字典的仍然可以满足要求），所以遇到没在字典里面的字符可以继续移动窗口右端，而移动窗口左端的条件是当找到满足条件的串之后，一直移动窗口左端直到有字典里的字符不再在窗口里。在实现中就是维护一个HashMap，一开始key包含字典中所有字符，value就是该字符的数量，然后遇到字典中字符时就将对应字符的数量减一。算法的时间复杂度是O(n),其中n是字符串的长度，因为每个字符再维护窗口的过程中不会被访问多于两次。空间复杂度则是O(字典的大小)，也就是代码中T的长度。代码如下： 
	 */
	public static String minWindow(String S, String T) {
	    if(S==null || S.length()==0)
	        return "";
	    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	    
	    for(int i=0; i<T.length();i++)
	    {
	        if(map.containsKey(T.charAt(i)))
	        {
	            map.put(T.charAt(i),map.get(T.charAt(i))+1);
	        }
	        else
	        {
	            map.put(T.charAt(i),1);
	        }
	    }
	    System.out.println(map);
	    
	    int left = 0;
	    int count = 0;
	    int minLen = S.length()+1;
	    int minStart = 0;
	    
	    for(int right=0; right<S.length();right++)
	    {
	    	System.out.println(S.charAt(right));
	        if(map.containsKey(S.charAt(right)))
	        {
	        	//System.out.println(S.charAt(right));
	            map.put(S.charAt(right),map.get(S.charAt(right))-1);
	            
	            System.out.println(map);
	            
	            if(map.get(S.charAt(right))>=0)
	            {
	                count++;
	                System.out.println("count="+count);
	            }
	            
	            while(count == T.length())
	            {
	                if(right-left+1<minLen)//update minLen and minStart
	                {
	                    minLen = right-left+1;
	                    minStart = left;                 
	                }
	                System.out.println("right="+right+" left="+left+" minStart="+minStart+" minLen="+minLen);
	                System.out.println("left ch="+S.charAt(left));
	                if(map.containsKey(S.charAt(left)))
	                {
	                    map.put(S.charAt(left), map.get(S.charAt(left))+1);
	                    System.out.println("map="+map);
	                    
	                    if(map.get(S.charAt(left))>0)
	                    {
	                        count--;
	                        System.out.println("count="+count);
	                    }
	                }
	                left++;
	            }
	        }//fi
	    }
	    if(minLen>S.length())
	    {
	        return "";
	    }
	    return S.substring(minStart,minStart+minLen);
	}
	
	
    public static String minWindowMe(String S, String T) {
        int minLen = Integer.MAX_VALUE ;
        HashSet<Character> set = new HashSet<>();
        for(int i=0;i<T.length();i++){
            set.add(T.charAt(i));
        }
        System.out.println(set);
        HashSet<Character> set2 = new HashSet<>();
        int j=0;
        boolean flag = true;
        for(int i=0;i<S.length();i++){
            while(i<S.length() && !set.contains(S.charAt(i))){
                i++;
                //continue;
            }
            System.out.println(i+" "+S.charAt(i));
            //if(i==S.length()) break; 
            if(flag) {
                j=i;
                System.out.println("j="+j);
                flag = false;
            }
            char ch = S.charAt(i);
            System.out.println("ch:"+ch + "i="+i);
            if(set2.contains(ch)){
                while(S.charAt(j)!=ch){
                    if(set2.contains(S.charAt(j))) set2.remove(S.charAt(j));
                    j++;
                }
                j=j+1;
                while(j<S.length() && !set.contains(S.charAt(j))) j++;
                System.out.println("j="+j);
                System.out.println("set2:"+set2);
                //flag = true;
            }
            else{
            	if(set.contains(ch)){
            		set2.add(ch);
            	}
            }
            if(set2.size()==set.size()){
            	System.out.println(S.substring(j,i+1) );
                if(minLen>i-j+1){
                    minLen=i-j+1;
                    System.out.println("minLen="+minLen);
                    //System.out.println(S.substring(j,i+1) );
                }
            }
            //}//else
            
        }
        System.out.println(minLen);
        return ""+minLen;
    }
    
	public ArrayList<Integer> findSubstring(String S, String[] L) {
	    // Note: The Solution object is instantiated only once and is reused by each test case.
	    ArrayList<Integer> res = new ArrayList<Integer>();
	    if(S==null || S.length()==0 || L==null || L.length==0)
	        return res;
	    HashMap<String,Integer> map = new HashMap<String,Integer>();
	    
	    for(int i=0;i<L.length;i++)
	    {
	        if(map.containsKey(L[i]))
	        {
	            map.put(L[i],map.get(L[i])+1);
	        }
	        else
	        {
	            map.put(L[i],1);
	        }
	    }
	    for(int i=0;i<L[0].length();i++)
	    {
	        HashMap<String,Integer> curMap = new HashMap<String,Integer>();
	        int count = 0;
	        int left = i;
	        for(int j=i;j<=S.length()-L[0].length();j+=L[0].length())
	        {
	            String str = S.substring(j,j+L[0].length());
	            
	            if(map.containsKey(str))
	            {
	                if(curMap.containsKey(str))
	                    curMap.put(str,curMap.get(str)+1);
	                else
	                    curMap.put(str,1);
	                if(curMap.get(str)<=map.get(str))
	                    count++;
	                else
	                {
	                    while(curMap.get(str)>map.get(str))
	                    {
	                        String temp = S.substring(left,left+L[0].length());
	                        if(curMap.containsKey(temp))
	                        {
	                            curMap.put(temp,curMap.get(temp)-1);
	                            if(curMap.get(temp)<map.get(temp))
	                                count--;
	                        }
	                        left += L[0].length();
	                    }
	                }
	                if(count == L.length)
	                {
	                    res.add(left);
	                    //if(left<)
	                    String temp = S.substring(left,left+L[0].length());
	                    if(curMap.containsKey(temp))
	                        curMap.put(temp,curMap.get(temp)-1);
	                    count--;
	                    left += L[0].length();
	                }
	            }
	            else
	            {
	                curMap.clear();
	                count = 0;
	                left = j+L[0].length();
	            }
	        }
	    }
	    return res;
	}
	

	public static boolean isInterleave(String s1, String s2, String s3) {
	    if(s1.length()+s2.length()!=s3.length())
	        return false;
	    String minWord = s1.length()>s2.length()?s2:s1;
	    String maxWord = s1.length()>s2.length()?s1:s2;
	    boolean[] res = new boolean[minWord.length()+1];
	    res[0] = true;
	    for(int i=0;i<minWord.length();i++)
	    {
	        res[i+1] = res[i] && minWord.charAt(i)==s3.charAt(i);
	    }
	    for(int i=0;i<maxWord.length();i++)
	    {
	        res[0] = res[0] && maxWord.charAt(i)==s3.charAt(i);
	        for(int j=0;j<minWord.length();j++)
	        {
	            res[j+1] = res[j+1]&&maxWord.charAt(i)==s3.charAt(i+j+1) || res[j]&&minWord.charAt(j)==s3.charAt(i+j+1);
	        }
	    }
	    return res[minWord.length()];
	}
	
    public static String longestCommonPrefix(String[] strs) {
        
        StringBuilder res=new StringBuilder();
        if(strs == null || strs.length==0)
            return res.toString();
        if(strs.length==1) return strs[0];
        int i=0,j=0;
        while(true){
            for(i=0;i<strs.length;i++){
                if( j>=strs[i].length() || strs[i].charAt(j)!=strs[0].charAt(j)) return res.toString();
            }
            res.append(strs[0].charAt(j));
            j++;
        }
        //return res.toString();
    }
	
	public static String reverseWords_m1(String s) {
	    s = s.trim();
	    return helper(s,0).toString();
	}
	private static StringBuilder helper(String s, int index)
	{
	    if(index>=s.length())
	        return new StringBuilder(); 
	    StringBuilder cur = new StringBuilder();
	    int lastIndex = index;
	    while(index < s.length() && s.charAt(index)!=' ')
	    {
	        cur.append(s.charAt(index++));
	    }
	    while(index < s.length() && s.charAt(index)==' ')
	        index++;
	    if(lastIndex == 0)
	        return helper(s,index).append(cur);
	    return helper(s,index).append(cur).append(' ');
	}
	
	
	public static int lengthOfLongestSubstringTwoDistinct(String s) {
		   int i = 0, j = -1, maxLen = 0;
		   
		   for (int k = 1; k < s.length(); k++) {
			  System.out.println("i="+i+" j="+j+" k="+k);
		      if (s.charAt(k) == s.charAt(k - 1)) continue;
		      System.out.println("i="+i+" j="+j+" k="+k);
		      
		      if (j >= 0 && s.charAt(j) != s.charAt(k)) {
		    	  System.out.println("s.charAt(j)="+s.charAt(j) + " s.charAt(k)="+s.charAt(k));
		    	  System.out.println("i="+i+" j="+j+" k="+k+" k-i="+ (k-i));
		    	  maxLen = Math.max(k - i, maxLen);
		    	  i = j + 1;
		    	  System.out.println("Aft i=j+1;i="+i+" j="+j+" k="+k + " maxLen="+maxLen);
		      }
		      j = k - 1;
		      System.out.println("Aft j=k-1;i="+i+" j="+j+" k="+k);
		   }
		   return Math.max(s.length() - i, maxLen);
		}
	
	public static boolean isOneEditDistance(String s, String t) {
		   int m = s.length(), n = t.length();
		   if (m > n) return isOneEditDistance(t, s);
		   if (n - m > 1) return false;
		   int i = 0, shift = n - m;
		   System.out.println("shift="+shift);
		   
		   while (i < m && s.charAt(i) == t.charAt(i)) i++;
		   System.out.println("i="+i);
		   
		   if (i == m) return shift > 0;
		   
		   if (shift == 0) i++;
		   while (i < m && s.charAt(i) == t.charAt(i + shift)) i++;
		   return i == m;
		}
	/*start from 0 index,until s[i]!=t[i],then two cases:  
	1) i==m,  
	2)i<m:  1,s,t are same len 2,NOT same len, compare A[i],A[i+shift]
		*/
	public static boolean isOneEditDistance2(String s, String t) {
		   int m = s.length(), n = t.length();
		   if (m > n) return isOneEditDistance(t, s); // make sure m<=n
		   if (n - m > 1) return false;
		   int i = 0, shift = n - m;
		   System.out.println("shift="+shift);
		   
		   while (i < m && s.charAt(i) == t.charAt(i)) i++; //until diff char
		   System.out.println("i="+i);
		   
		   if (i == m) return shift > 0; //first m char are same.then if shift>0, means there is oneeditDis
		   
		   if (shift == 0) i++; // s,t same len,m==n
		   while (i < m && s.charAt(i) == t.charAt(i + shift)) i++;
		   return i == m;
		}
	
	public static String multiply(String num1, String num2) {
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
	    	System.out.println("i="+i + " i-1="+ (i-1)+ " num1.len="+num1.length());
	    	System.out.println("num="+num);
	    	
	        for(int j=Math.min(i-1,num1.length());j>0;j--)
	        {
	        	System.out.println("j="+j+" i-j="+ (i-j) + " num2.len=" + num2.length());
	            if(i-j<=num2.length())
	            {
	            	System.out.println("(num1[j-1]="+ (num1.charAt(j-1)-'0') + "  num2[i-1-j]=" + (num2.charAt(i-1-j)-'0') );
	                num += (int)(num1.charAt(j-1)-'0')*(int)(num2.charAt(i-1-j)-'0');
	            }
	        }
	        if(i!=1 || num>0)
	        	System.out.println("num="+num + " num%10=" + num%10 + " num/10=" + num/10);
	            res.append(num%10);
	            System.out.println("res="+res);
	        num = num/10;
	    }
	    return res.reverse().toString();
	}
	
	/*
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
	 * determine if the input string is valid.
The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
这道题思路比较简单，主要考察对栈数据结构的操作。遇到左括号就入栈，遇到右括号看栈顶左括号是否与当前右括号匹配，匹配继续，否则返回false。算法的时间复杂度是O(n)，空间复杂度也是O(n)。代码如下：

	 */
	public boolean isValid(String s) {
	    if(s==null || s.length()==0)
	        return true;
	    LinkedList<Character> stack = new LinkedList<Character>();
	    for(int i=0;i<s.length();i++)
	    {
	        switch(s.charAt(i))
	        {
	            case '(':
	            case '{':
	            case '[':
	                stack.push(s.charAt(i));
	                break;
	            case ')':
	                if(stack.isEmpty() || stack.pop()!='(')
	                    return false;
	                break;
	            case '}':
	                if(stack.isEmpty() || stack.pop()!='{')
	                    return false;
	                break;
	            case ']':
	                if(stack.isEmpty() || stack.pop()!='[')
	                    return false;
	                break; 
	            default:
	                break;
	        }
	    }
	    if(stack.isEmpty())
	        return true;
	    return false;
	}
	/*
	 * Given two binary strings, return their sum (also a binary string).
For example,
a = "11"
b = "1"
Return "100".
    这道题跟Add Two Numbers很类似，代码结构很接近。从低位开始，一直相加并且维护进位。和Add Two Numbers的区别是这个题目低位在后面，所以要从string的尾部往前加。时间复杂度是O(max(m,n))，m和n分别是两个字符串的长度，空间复杂度是结果的长度O(max(m,n))。代码如下：
	 */
	public String addBinary(String a, String b) {
	    if(a==null || a.length()==0)
	        return b;
	    if(b==null || b.length()==0)
	        return a;
	    int i=a.length()-1;
	    int j=b.length()-1;
	    int carry = 0;
	    StringBuilder res = new StringBuilder();
	    while(i>=0&&j>=0)
	    {
	        int digit = (int)(a.charAt(i)-'0'+b.charAt(j)-'0')+carry;
	        carry = digit/2;
	        digit %= 2;
	        res.append(digit);
	        i--;
	        j--;
	    }
	    while(i>=0)
	    {
	        int digit = (int)(a.charAt(i)-'0')+carry;
	        carry = digit/2;
	        digit %= 2;
	        res.append(digit);
	        i--;
	    }
	    while(j>=0)
	    {
	        int digit = (int)(b.charAt(j)-'0')+carry;
	        carry = digit/2;
	        digit %= 2;
	        res.append(digit);
	        j--;
	    }      
	    if(carry>0)
	    {
	        res.append(carry);
	    }
	    return res.reverse().toString();
	}
	
	public  String largestNumber(int[] num) {
	    if(num==null || num.length==0)
	        return "";
	    String[] Snum = new String[num.length];
	    for(int i=0;i<num.length;i++)
	        Snum[i] = num[i]+"";

	    Comparator<String> comp = new Comparator<String>(){
	        @Override
	        public int compare(String str1, String str2){
	            String s1 = str1+str2;
	            String s2 = str2+str1;
	            return s1.compareTo(s2);
	        }
	    };

	    Arrays.sort(Snum,comp);

	    StringBuilder sb = new StringBuilder();
	    for(String s: Snum)
	        sb.insert(0, s);

	    if(sb.charAt(0)=='0')
	        return "0";

	    return sb.toString();

	}
	
	
	/*
	 *  1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
	 */
	public static String convertToTitle(int n) {

	    int x = n/26;
	    int y = n%26;

	    if (n<=0) {
	        return "";
	    }

	    if (x == 0) {
	        //return ((char)(y+64))+"";
	    	return ((char)(y+'A'-1))+"";
	    }

	    if (y == 0) {
	        return convertToTitle(x-1) + "Z";
	    }

	    return convertToTitle(x)+convertToTitle(y);

	}
	
	public static String convertToTitle2(int n) {
		if(n==0) return "";
		String str1= convertToTitle(--n/26);
		
		char c2 = (char)('A' + (n % 26) );
		return str1 + c2;
		//return n == 0 ? "" : convertToTitle(--n / 26) + (char)('A' + (n % 26));
	}
	
	public static String simplifyPath(String path) {
	    if(path == null || path.length()==0)
	    {
	        return "";
	    }
	    LinkedList<String> stack = new LinkedList<String>();
	    StringBuilder res = new StringBuilder();
	    int i=0;
	    
	    while(i<path.length())
	    {
	    	System.out.println("stack="+stack);
	    	System.out.println("i="+i);
	        int index = i;
	        StringBuilder temp = new StringBuilder();
	        while(i<path.length() && path.charAt(i)!='/')
	        {
	            temp.append(path.charAt(i));
	            i++;
	        }
	        System.out.println("temp="+temp);
	        System.out.println("index="+index);
	        
	        if(index!=i)
	        {
	            String str = temp.toString();
	            System.out.println("str="+str);
	            if(str.equals(".."))
	            {
	                if(!stack.isEmpty())
	                    stack.pop();
	            }
	            else if(!str.equals("."))
	            {
	                stack.push(str);
	            }
	        }
	        i++;
	    }
	    if(!stack.isEmpty())
	    {
	        String[] strs = stack.toArray(new String[stack.size()]);
	        System.out.println("strs="+strs);
	        for(int j=strs.length-1;j>=0;j--)
	        {
	          res.append("/"+strs[j]);
	        }
	    }
	    if(res.length()==0)
	        return "/";
	    return res.toString();
	}
	
	
	
    public static ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<String>();
        helper(s,0,1,"",res);
        return res;
    }//end func
    
    private static void helper(String s,int i,int seg, String item, ArrayList<String> res){
        //end recursion
        if(i>=s.length()) return;
        if(seg==4) {
            String str=s.substring(i,s.length());
            if(isstrValid(str)){
                //item = item+"."+str;
                res.add(item+"."+str);               
            }
            return;
        }
        for(int j=0;j<=2 && (i+j+1)<s.length();j++){
            String str=s.substring(i,i+j+1);
            if(isstrValid(str)){
                if(seg==1) helper(s,i+j+1,seg+1,str,res);
                
                else helper(s,i+j+1,seg+1,item+"."+str,res);
          	
            }
        }
        return;
    }//end fucn
    
    private static boolean isstrValid(String str){
        //2,25,255
        if(str==null || str.length()==0) return false;
        if(str.length()>3) return false;
        int num=Integer.parseInt(str);
        if(num>0 && num<=255 ) return true;
        return false;
    }//end func
    /*
     * Compare two version numbers version1 and version1.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
     */
    
    
    
    /*
    Count and Say Total Accepted: 29290 Total Submissions: 113482 My Submissions Question Solution 
    The count-and-say sequence is the sequence of integers beginning as follows:
    1, 11, 21, 1211, 111221, ...

    1 is read off as "one 1" or 11.
    11 is read off as "two 1s" or 21.
    21 is read off as "one 2, then one 1" or 1211.
    Given an integer n, generate the nth sequence.
    Note: The sequence of integers will be represented as a string.
    这道题属于字符串操作的类型，算法上提高空间不大，只能是对于某一个数的串，读过去然后得到计数并生成下一个串。时间复杂度是O(n*串的长度)，空间复杂度是O(串的长度)。代码如下：
    小陷阱就是跑完循环之后记得把最后一个字符也加上，因为之前只是计数而已。这道题属于字符串或者说数组操作的类型，考察对于明确问题和算法的实现能力，一般会在电面，或者最容易的第一道题中出现，力求一遍搞定，不留bug哈。
    
    */
    //by code ganker
    public String countAndSay(int n) {
        if(n<=0)
            return "";
        String curRes = "1";
        for(int i=2;i<=n;i++)
        {
            StringBuilder res = new StringBuilder();
            int count = 1;
            for(int j=1;j<curRes.length();j++)
            {
                if(curRes.charAt(j)==curRes.charAt(j-1))
                    count++;
                else
                {
                    res.append(count);
                    res.append(curRes.charAt(j-1));
                    count = 1;
                }
            }
            res.append(count);
            res.append(curRes.charAt(curRes.length()-1));
            curRes = res.toString();
        }
        return curRes;
    }
    
    
    /*
    这道题属于字符串处理的题目，思路比较简单，就是brute force的想法，以第一个字符串为标准，对于每个字符串从第一个字符开始，看看是不是和标准一致，如果不同，则跳出循环返回当前结果，否则继续下一个字符。时间复杂度应该是O(m*n)，m表示字符串的最大长度，n表示字符串的个数，空间复杂度应该是O(m),即字符串的长度，代码如下： 
以上代码设置了一个标记，来标记是否结束。细心的读者可能发现中间那个循环index是从0开始，按理说不用对第一个字符串进行判断了，因为他是标准，这么做的目的其实是因为leetcode有一个测试集是空串，如果不对0进行判断，那么就没有设置flag为false，跑到第二层就会越界。当然也可以手动判断一下，这个其实是小问题。 
如果有朋友有更优的解法，如果大家这个题有更好的解法，可以留言或者发邮件到linhuanmars@gmail.com给我交流谈论哈。    
    */
    //by code ganker
public String longestCommonPrefixGanker(String[] strs) {
    StringBuilder res = new StringBuilder();
    if(strs == null || strs.length==0)
        return res.toString();
    boolean sameFlag = true;
    int idx = 0; // 记录当前在检查哪个letter 
    while(sameFlag)
    {
        // 检查每给字符串，如果某个字符串太短，或者不符合strs[0].charAt(idx)
        for(int i=0;i<strs.length;i++)
        {
            if(strs[i].length()<=idx || strs[i].charAt(idx)!=strs[0].charAt(idx))
            {
                sameFlag = false;
                break;
            }
        }
        if(sameFlag)
            res.append(strs[0].charAt(idx));
        idx++;
    }
    return res.toString();
}

    /*
    这道题和Integer to Roman一样，也是整数和罗马数字的转换。思路也比较简单，就是维护一个整数，然后如果
    1下一个字符是对应位的5或者10则减对应位的1，否则加之。遇到5或者10就直接加上对应位的5或者10。时间复杂度是O(字符串的长度)，空间复杂度是O(1)。
    题目思路比较清晰，就是简单的字符串操作。
    */
    
    //by code ganker
    public int romanToInt(String s) {

        if(s==null || s.length()==0)
            return 0;
        int res = 0;
        /*
      	1-3：用1表示； I,II,III
4:1：5左边加一个1； IV
5： 直接用5表示； V
6-8: 5右边加相应的1； VI,VII, VIII 
9： 10左边加一个1。IX
以下的代码用一个函数来对某一个位用相应的1,5,10进行转换，然后求出每一位依次转换得到结果，因为知道不会超过4000，所以直接求4位出来，算法时间复杂度是O(整数的位数），空间复杂度是O(1)。
    //I 1  
    //V 5  
    //X 10  
    //L 50  
    //C 100  
    //D 500  
    //M 1,000
    248 : CCXLVIII
         */
        for(int i=0;i<s.length();i++)
        {
            switch(s.charAt(i))
            {
                case 'I':
                    if(i<s.length()-1 && (s.charAt(i+1)=='V'||s.charAt(i+1)=='X'))
                    {
                        res -= 1;
                    }
                    else
                    {
                        res += 1;
                    }
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'X':
                    if(i<s.length()-1 && (s.charAt(i+1)=='L'||s.charAt(i+1)=='C'))
                    {
                        res -= 10;
                    }
                    else
                    {
                        res += 10;
                    }
                    break;
                    //I 1  
                    //V 5  
                    //X 10  
                    //L 50  
                    //C 100  
                    //D 500  
                    //M 1,000
                case 'L':
                    res += 50;
                    break;
                case 'C':
                    if(i<s.length()-1 && (s.charAt(i+1)=='D'||s.charAt(i+1)=='M'))
                    {
                        res -= 100;
                    }
                    else
                    {
                        res += 100;
                    }
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'M':
                    res += 1000;
                    break;
                default:
                    return 0;
            }
        }
        return res;
    }
    
    /*
         这道题比较简单，只要搞清楚每个数字在每个位置应该如何表示就可以，罗马数字对于每个位有三个单位：1,5,10，对于1到9，表示方法如下：
1-3：用1表示； I,II,III
4:1：5左边加一个1； IV
5： 直接用5表示； V
6-8: 5右边加相应的1； VI,VII, VIII 
9： 10左边加一个1。IX
以下的代码用一个函数来对某一个位用相应的1,5,10进行转换，然后求出每一位依次转换得到结果，因为知道不会超过4000，所以直接求4位出来，算法时间复杂度是O(整数的位数），空间复杂度是O(1)。
    //I 1  
    //V 5  
    //X 10  
    //L 50  
    //C 100  
    //D 500  
    //M 1,000
    248 : CCXLVIII
     */
    public static String intToRoman(int num) {
        StringBuilder res=new StringBuilder();
        ArrayList<Integer> digits= new ArrayList<Integer>();
        int range=1000;

        while(range>=1){
            digits.add(num/range);
            //go to next digit
            num = num%range;
            range=range/10;
        }//end while
        
        res.append( helper(digits.get(0),'M',' ',' ') );
        res.append( helper(digits.get(1),'C','D','M') );
        res.append( helper(digits.get(2),'X','L','C') );
        res.append( helper(digits.get(3),'I','V','X') );
        return res.toString();
    }
    
    private static String helper(int digit,char one,char five,char ten){
        StringBuilder str=new StringBuilder();
        switch(digit){
            case 3:
                str.append(one);
            case 2:
            	str.append(one);
            case 1:
            	str.append(one);
                break;
            case 4:
            	str.append(one);
            	//str.append(five);
            case 5:
                str.append(five);
                break;
            case 6:
                str.append(five);
                str.append(one);
                break;
            case 7:  
                str.append(five);
                str.append(one);            	
                str.append(one);
                break;
            case 8:
                str.append(five);
                str.append(one);            	
                str.append(one);
                str.append(one);
                break;
            case 9:
                str.append(one);
                str.append(ten);
                break;
            default:
                break;
                
        }//end switch
        return str.toString();
    }   
    
	   public static String longestPalindrome(String s) {
	        String res=new String();
	        String str=new String();
	        int l=0,r=0;
	        int max=0;
	        for(int i=0;i<=s.length()-1;i++){
	            l=r=i;
	            
	            str=palHelp(l,r,s);
	            if(str.length()>max){
	                max=str.length();
	                res=str;
	            }
	            l=i;r=i+1;
	            str=palHelp(l,r,s);
	            if(str.length()>max){
	                max=str.length();
	                res=str;
	            }            
	            
	        }//end for
	        //com.out(res);
	        return res;
	    }
	    
	    private static String palHelp(int l, int r, String s){
	        while(l>=0 && r<=(s.length()-1) && s.charAt(l)==s.charAt(r)){
	            l--;r++;
	            //str.add();
	        }
	        com.out(String.valueOf(l) +" "+ String.valueOf(r));
	        return s.substring(l+1,r); 
	    } 

	
 
	
	
	/*
	 * https://oj.leetcode.com/discuss/18162/my-accepted-java-solution
	For 2, it should place one "()" and add another one insert it but none tail it,

	'(' f(1) ')' f(0)

	or add none insert it but tail it by another one,

	'(' f(0) ')' f(1)

	Thus for n, we can insert f(i) and tail f(j) and i+j=n-1,
	'(' f(i) ')' f(j)
	*/
/*
	public List<String> generateParenthesis2(int n) {
	    List<String> result = new ArrayList<String>();
	    if (n == 0) {
	        result.add("");
	    } else {
	        for (int i = n - 1; i >= 0; i--) {
	            List<String> insertSub = generateParenthesis(i);
	            List<String> tailSub = generateParenthesis(n - 1 - i);
	            for (String insert : insertSub) {
	                for (String tail : tailSub) {
	                    result.add("(" + insert + ")" + tail);
	                }
	            }

	        }
	    }
	    return result;
	}
*/	


    
}