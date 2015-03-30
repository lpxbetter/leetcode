import java.io.*;
import java.util.*;
//import commLib.*;

//import mergeListsLipingXiong.ListNode;


public class others {
	 public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}

	}

	public static void main(String[] args){
		StringBuilder res = new StringBuilder();
		res.append("hello".charAt(0));
		//System.out.println(res);
		/*
		int[] A={1,1,1,2,2};
		ArrayList<Integer> item = new ArrayList<Integer>();
		item.add(0,10);
		System.out.println(item);
		item.add(1,11);
		System.out.println(item);
		item.add(2,20);
		System.out.println(item);	
		*/	
		//lengthOfLongestSubstringTwoDistinct("ecba");
		
		//findMissingRanges(new int[]{0, 1, 3, 50, 75},0,99);
		//boolean rst=isOneEditDistance("abc","xyz");
		//System.out.println(rst);
		//System.out.println(permute(new int[]{1,1,2}) );
		//majorityElement(new int[] {2, 2, 3, 5, 2, 2, 6});
		//findOccurences(new int[]{2,2,3,5,3,2});
		//repeat(new int[]{1, 3, 2, 3, 4, 1});
		//flipBitsMe(new int[]{1, 0 ,0 ,1 ,0 ,0 ,1, 0});
		//reverseNum(4);
		//sortColors(new int[]{1, 2, 0 ,0 ,1 ,0 ,0 ,1, 0,2});
		int[] B={1,0,0};
		//System.out.println( canJump(B) );
		//System.out.println(reverseWords("  The   sky   is        blue. ") );
		//System.out.println(addBinary("11","100"));
		//threeSum(new int[]{0,0,0});
		String s="25525511135"; //["255.255.11.135", "255.255.111.35"]. (Order does not matter)
		//restoreIpAddresses(s);
		//subsetsIte(new int[]{1,2,3});
//		(new int[]{1,2,2,2});
		//System.out.println(combine(3,2) );
//		System.out.println( combinationSum(new int[]{8,7,4,3},11) ); //8,7,4,3], 11 
		System.out.println(intToRoman(321) );
	}
	public static String intToRoman(int num) {
	    //I 1
	    //V 5
	    //X 10
	    //L 50
	    //C 100
	    //D 500
	    //M 1,000
	    if(num<1 || num>3999)
	        return "";
	    int digit = 1000;
	    ArrayList<Integer> digits = new ArrayList<Integer>();
	    while(digit>0)
	    {
	        digits.add(num/digit);
	        num %= digit;
	        digit /= 10;
	    }
	    System.out.println("digits="+digits);
	    
	    StringBuilder res = new StringBuilder();
	    res.append(convert(digits.get(0),'M',' ', ' '));
	    res.append(convert(digits.get(1),'C','D', 'M'));
	    res.append(convert(digits.get(2),'X','L', 'C'));
	    res.append(convert(digits.get(3),'I','V', 'X'));
	    return res.toString();
	}
	public static String convert(int digit, char one, char five, char ten)
	{
	    StringBuilder res = new StringBuilder();
	    switch(digit)
	    {
	        case 9:
	            res.append(one);
	            res.append(ten);
	            break;
	        case 8:
	        case 7:
	        case 6:
	        case 5:
	            res.append(five);
	            for(int i=5;i<digit;i++)
	                res.append(one);
	            break;
	        case 4:
	            res.append(one);
	            res.append(five);
	            break;   
	        case 3:
	        case 2:
	        case 1:
	            for(int i=0;i<digit;i++)
	                res.append(one);
	            break;   
	        default:
	            break;
	    }
	    return res.toString();
	}
	
    public static List<ArrayList<Integer>> combine(int n,int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        int[] A=new int[n];
        for(int i=0;i<n;i++){
        	A[i]=i+1;
        }
        if(A==null || A.length ==0) return res;
        Arrays.sort(A);
        domysubsets(A,0, new ArrayList<Integer>(), res,k);
        return res;
    }
    public static void domysubsets(int[] A, int start,ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res,int k){
    	if(item.size()==k){
    	    res.add(new ArrayList<Integer>(item));
    	}
    	
    	for(int i=start;i<A.length;i++){
    		if(i>start && A[i]==A[i-1]) continue;//just add one line
    		item.add(A[i]);
    		domysubsets(A,i+1,item,res,k);
    		item.remove(item.size()-1);
    	}
    }
	
    public static List<ArrayList<Integer>> mysubsets(int[] A) {
            ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
            if(A==null || A.length ==0) return res;
            Arrays.sort(A);
            domysubsets(A,0, new ArrayList<Integer>(), res);
            return res;
        }
        public static void domysubsets(int[] A, int start,ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res){
        	res.add(new ArrayList<Integer>(item));
        	for(int i=start;i<A.length;i++){
        		if(i>start && A[i]==A[i-1]) continue;//just add one line
        		item.add(A[i]);
        		domysubsets(A,i+1,item,res);
        		item.remove(item.size()-1);
        	}
        }
        
        
        
    public static ArrayList<ArrayList<Integer>> combinationSum(int[] A, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(A==null || A.length ==0) return res;
        Arrays.sort(A);
        docombine(A,0, target, new ArrayList<Integer>(), res);
        return res;
    }
    public static void docombine(int[] A, int start,int target,ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res){
        if(target<0) return;
        if(target == 0){
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for(int i=start;i<A.length;i++){
            item.add(A[i]);
            docombine(A,i,target-A[i],item,res);
            item.remove(item.size()-1);
        }
    }
    
    public static List<ArrayList<Integer>> mypermuteII(int[] S) {
        List<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(S==null || S.length ==0) return res;
        boolean[] used = new boolean[S.length];
        Arrays.sort(S);;
        doPermuteII(S,new ArrayList<Integer>(),res,used);
        return res;
    }

    public static void doPermuteII(int[] S, ArrayList<Integer> item,List<ArrayList<Integer>> res,boolean[] used){
        if(item.size() == S.length){
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for(int i=0;i<S.length;i++){
        	if(i>0 && !used[i-1]  && S[i]==S[i-1]) continue;
            if(!used[i]){
                item.add(S[i]);
                used[i]=true;
                doPermuteII(S,item,res,used);
                item.remove(item.size()-1);
                used[i]=false;
            }
        }
    }
    

    public static List<ArrayList<Integer>> mypermute(int[] S) {
        List<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        boolean[] used = new boolean[S.length];
        doPermute(S,new ArrayList<Integer>(),res,used);
        return res;
    }

    public static void doPermute(int[] S, ArrayList<Integer> item,List<ArrayList<Integer>> res,boolean[] used){
        if(item.size() == S.length){
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for(int i=0;i<S.length;i++){
            if(!used[i]){
                item.add(S[i]);
                used[i]=true;
                doPermute(S,item,res,used);
                item.remove(item.size()-1);
                used[i]=false;
            }
        }
    }
    
    
    public static List<ArrayList<Integer>> subsets(int[] S) {
        List<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        int[] lastSize= new int[1] ;
        lastSize[0]=0;
        res=helper(S,S.length-1,lastSize);
        System.out.println(res);
        return res;
    }
    public static ArrayList<ArrayList<Integer>> helper(int[] S, int index,int[] lastSize){
        if(index==-1){
        	ArrayList<ArrayList<Integer>> tmpRes = new ArrayList<ArrayList<Integer>>();
            tmpRes.add(new ArrayList<Integer>());
            return tmpRes;
        }
        
        ArrayList<ArrayList<Integer>> res = helper(S,index-1,lastSize);
        System.out.println(res);
        int size=res.size();
        int start = 0;
        if(index >=1 && index < S.length && S[index]==S[index-1]){
        	start = lastSize[0];
        	System.out.println(lastSize[0]);
        }
        for(int i=start;i<size;i++){
        	ArrayList<Integer> list= new ArrayList<Integer>(res.get(i));
        	list.add(S[index]);
        	res.add(list);
        }
        lastSize[0]=size;
        return res;
    }
	
    public static List<ArrayList<Integer>> subsetsIte(int[] S) {
        List<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        res.add(new ArrayList<Integer>());
        //System.out.println(res);
        for(int i=0;i<S.length;i++){
        	int size=res.size();
        	for(int j=0;j<size;j++){
        		ArrayList<Integer> list = new ArrayList<Integer>(res.get(j));
        		list.add(S[i]);
        		res.add(list);
        	}
        	//System.out.printf("i=%d,res=%s\n",i,res);
        }
        return res;
    }
	
    public static List<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<>();
        if(s==null) return res;
        System.out.println(s.length());
        //StringBuilder item = new StringBuilder();
        helper(s,0,1,"",res);
        System.out.println(res);
		return res;
    }
    public static void helper(String s,int start,int seg,String item,ArrayList<String> res){
    	if(start > s.length()) return;
        if(seg==4 ){
        	
            String tmpStr = s.substring(start,s.length());
            if(isValid(tmpStr)){
                res.add(item+"."+tmpStr);
            }
            //item = new StringBuilder();
            //item = item.delete( 0, item.length() );
            //System.out.printf("invalid left,seg=%s,str=%s,item=%s\n",seg,tmpStr,item);
            return;
        }
        for(int i=start;i<=start+2 && start+2<=s.length();i++){
            String str=s.substring(start,i+1);
            //System.out.printf("str=%s,i=%d,start=%d,seg=%d\n",str,i,start,seg);
            if(isValid(str)){
            	//System.out.printf("valid str=%s\n",str);
                System.out.printf("item=%s,seg=%d\n",item+".",seg);
                if(seg==1) helper(s,i+1,seg+1,str,res);
                else
                {helper(s,i+1,seg+1,item+"."+str,res);}
            }
        }
    }
    
	/*
    public static List<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<>();
        if(s==null) return res;
        System.out.println(s.length());
        StringBuilder item = new StringBuilder();
        helper(s,0,1,item,res);
        System.out.println(res);
		return res;
    }
    public static void helper(String s,int start,int seg,StringBuilder item,ArrayList<String> res){
    	if(start > s.length()) return;
        if(seg==4 ){
        	
            String tmpStr = s.substring(start,s.length());
            if(isValid(tmpStr)){
            	System.out.printf("seg=%s,str=%s\n",seg,tmpStr);
                item.append(tmpStr);
                res.add(item.toString());
            }
            //item = new StringBuilder();
            //item = item.delete( 0, item.length() );
            //System.out.printf("invalid left,seg=%s,str=%s,item=%s\n",seg,tmpStr,item);
            return;
        }
        for(int i=start;i<=start+2 && start+2<=s.length();i++){
            String str=s.substring(start,i+1);
            //System.out.printf("str=%s,i=%d,start=%d,seg=%d\n",str,i,start,seg);
            if(isValid(str)){
            	//System.out.printf("valid str=%s\n",str);
                item.append(str+".");
                System.out.printf("item=%s,seg=%d\n",item,seg);
                helper(s,i+1,seg+1,item,res);
            }
            item.delete(item.length()-str.length()+1,item.length());
            System.out.printf("recover item=%s,seg=%d\n",item,seg);
        }
    }
    */
    
    public static boolean isValid(String str){
    	if(str.length()>3 || str.length() <=0 ) return false;
    	if(Integer.parseInt(str) > 255) return false;
    	for(int i=0;i<str.length();i++){
    		if(str.charAt(i) < '0' || str.charAt(i) > '9') return false;
    	}
    	return true;
    }
    
    public static ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Arrays.sort(num);
        int n=num.length;
        for(int i=0;i<n;i++){
            ArrayList<ArrayList<Integer>> list = twoSum(num,i+1,-num[i]);
            for(ArrayList<Integer> l : list){
            	l.add(0,num[i]);
                res.add(l);
            }
        }
        System.out.println(res);
        return res;
    }
    public static ArrayList<ArrayList<Integer>> twoSum(int[] num,int beg,int sum){
    	System.out.println(beg+" "+sum);
        ArrayList<ArrayList<Integer>>  res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> item = new ArrayList<>();
        int l=beg;
        int r=num.length-1;
        while(l<r){
        	System.out.println(l+" r="+r);
            if(num[l]+num[r]==sum){
                 item.add(num[l++]);
                 item.add(num[r--]);
                 res.add(new ArrayList<Integer>(item));
            }
            else if(num[l]+num[r]>sum){
                r--;
            }
            else{
                l++;   
            }
        }
        return res;
    }
	
    public void merge(int A[], int m, int B[], int n) {
        int end=m+n-1;
        int i=m-1;
        int j=n-1;
        while(i>=0 && j>=0){
            if(A[i]>B[j]) A[end--]=A[i--];
            else A[end--]=B[j--];
        }
        while(j>=0){
            A[end--]=B[j--];
        }
    }
    
    public int removeDuplicates(int[] A) {
        int l=1;
        int n=A.length;
        for(int i=1;i<n;i++){
            while(i<n&&A[i]==A[i-1]) i++;
            if(i<n) A[++l]=A[i];
        }
        return l;
    }
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode helper = new ListNode(0);
        helper.next = head;
        ListNode l = helper;
        ListNode r = helper;
        for(int i=1;i<=n;i++){
            r=r.next;
        }
        while(r.next!=null){
            l=l.next;
            r=r.next;
        }
        l.next=l.next.next;
        return helper.next;
    }
    
    public int removeElement(int[] A, int elem) {
        if(A==null) return 0;
        int n=A.length;
        int l=0,r=n-1;
        while(l<n&& && r>=0 && l<=r){
            if(A[l]==elem){
                int temp=A[l];
                A[l++]=A[r];
                A[r--]=temp;
            }
            else{
                l++;
            }
        }
        return r+1;
    }
	
    public static int majorityElement(int[] A) {
        int cur=A[0];
        int vo=1;
        int n=A.length;
        for(int i=1;i<n;i++){
            if(A[i]==cur) vo++;
            else vo--;
            if(vo==0){
                cur=A[i];
            }
        }
        return cur;
    }
	
    public static String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int m=a.length();
        int n=b.length();
        int i=1,sum,digit,rem=0;
        while(i<=m && i<=n){
            sum = (int)(a.charAt(m-i) -'0')+ (int)(b.charAt(n-i)-'0') +rem;
            digit = sum%2;
            rem = sum/2;
            res.append(digit);
            i++;
        }
        System.out.println(res);
        System.out.println(rem);
        
        //rem=0;
        if(i<=m){
            sum = (int)(a.charAt(m-i)-'0') +rem;
            digit = sum%2;
            rem = sum/2;
            res.append(digit);
            i++;
        }
        System.out.println(res);
        //rem=0;
        if(i<=n){
            sum = (int)(b.charAt(n-i) - '0') +rem;
            digit=sum%2;
            rem = sum/2;
            res.append(digit);
            i++;
        }
        System.out.println(rem);
        
        if(rem>0) res.append(rem);
        return res.reverse().toString();
    }
    

	
    public static boolean canJump(int[] A) {
        int n =A.length;
        boolean[] T=new boolean[n];
        T[0]=true;
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(T[j] && j+A[j]>=i) {
                    T[i]=true;
                    break;
                }
            }
        }
        return T[n-1];
    }
    
	
	public static void sortColors(int[] A) {
	    if(A==null || A.length==0)
	        return;
	    int idx0 = 0;
	    int idx1 = 0;
	    for(int i=0;i<A.length;i++)
	    {
	        if(A[i]==0)
	        {
	            A[i] = 2;
	            A[idx1++] = 1;
	            A[idx0++] = 0;
	        }
	        else if(A[i]==1)
	        {
	            A[i] = 2;
	            A[idx1++] = 1;
	        }
	    }
	}
	
	public static void reverseNum(int a){
		int[] digits  = new int[32];
		for(int i=0;i<32;i++){
			digits[i]=(a>>i) & 1;
		}
		for(int i=0;i<32;i++){
		System.out.print(digits[i]);
		}
	}
	
	/*
	input: a list of number
    output: two strings
    Exp: [1, 3, 2, 3, 4, 1] output: a: 000101   b:110000
    */
	public static void repeat(int[] a){
		StringBuilder res = new StringBuilder();
		HashSet<Integer> set = new HashSet<>();
		for(int i=0;i<a.length;i++){
			if(set.contains(a[i])){
				res.append(1);
			}
			else{
				res.append(0);
				set.add(a[i]);
			}
		}
		System.out.println(res);
	}
	
	
	
	public static void flipBitsMe(int[] a) {
		int[][] res= new int[a.length][a.length];
		int max = 0,L=0,R=0;
		res[0][0]=0;
		for(int i=a.length-1;i>=0;i--){
			for(int j=0;j<a.length-1;j++){
				if(i+1>=a.length) res[i][j] = a[i]==0 ? 1:-1;
				else{
					res[i][j] = res[i+1][j] + (a[i] == 0? 1:-1);
				}
				if(res[i][j] > max){
					max=res[i][j];
					L = i; R = j;
				}
			}
		}//for
		int reminder = 0;
		for(int k=0;k<a.length;k++){
			if(k>L && k<R) continue;
			else reminder += a[k]==1? 1:0;
		}
		System.out.println(max+reminder);
	}
	
	public static void flipBits(int[] a) {
	
		        int maxDiff = 0;
		        int flipStartIndex = 0;
		        int flipEndIndex = 0;
		        int onesToFlip = 0;
		        int totalNumberOfOnes = 0;

		        int currentDiff = 0;
		        int currentStart = 0;
		        int currentOnesToFlip = 0;

		        for (int i = 0; i < a.length; i++) {
		            if (a[i] == 0) {
		                        currentDiff -= 1;
		            } else {
		                        currentDiff += 1;
		                        currentOnesToFlip++;
		                        totalNumberOfOnes++;
		            }
		            
		            if (currentDiff < maxDiff) {
		                        maxDiff = currentDiff;
		                        flipStartIndex = currentStart;
		                        flipEndIndex = i;
		                        onesToFlip = currentOnesToFlip;
		            } else if (currentDiff > 0) {
		                        currentDiff = 0;
		                        currentStart = i + 1;
		                        currentOnesToFlip = 0;
		            }
		        }
		        System.out.println(flipEndIndex - flipStartIndex + 1 - onesToFlip +   totalNumberOfOnes - onesToFlip);
		}
		
		
	
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode helper = new ListNode(0);
        helper.next = l1;
        ListNode pre = helper;
        while(l1!=null && l2 !=null){
            if(l1.val <= l2.val){
                pre = l1;
                l1 = l1.next;
            }
            else{
                pre.next = l2;
                ListNode next = l2.next;
                l2.next = l1;
                l2 = next;
                pre = pre.next;
            }
        }
        while(l2 != null){
            pre.next = l2;
        }
        return helper.next;
    }
	
	public static void findOccurences(int[] array){        
		if(array == null) return;        
		HashMap<Integer, Integer> hs = new HashMap<>();        
		       
		for(int i = 0; i<array.length; i++){           
		 if(!hs.containsKey(array[i])){                
			 hs.put(array[i],1);                
			 //list.add(array[i]);            
		}
		else{                
			hs.put(array[i],hs.get(array[i])+1);            
			}        
		}  
		
		ArrayList<Integer> list = new ArrayList<>(hs.keySet()); 
		Collections.sort(list);
		for(Integer e :list){            
			int numOfOccurences = hs.get(e);            
			System.out.println(e+":"+numOfOccurences);        
		}
/*
		for(Map.Entry<Integer, Integer> entry: hs.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		*/
			
	}
    
	
	public static ArrayList<ArrayList<Integer>> permute(int[] num) {
	    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	    if(num==null || num.length==0)
	        return res;
	    permutehelper(num, new boolean[num.length], new ArrayList<Integer>(), res);
	    return res;
	}
	private static void permutehelper(int[] num, boolean[] used, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res)
	{
	    if(item.size() == num.length)
	    {
	        res.add(new ArrayList<Integer>(item));
	        System.out.println(item);
	        return;
	    }
	    for(int i=0;i<num.length;i++)
	    {
	    	if(i>0 num[] && num[i]==num[i-1]) continue;
	        if(!used[i])
	        {
	            used[i] = true;
	            item.add(num[i]);
	            permutehelper(num, used, item, res);
	            item.remove(item.size()-1);
	            used[i] = false;
	        }
	    }
	}
	
	


   /**
    * @param buf Destination buffer
    * @param n   Maximum number of characters to read
    * @return    The number of characters read
    */
   public int read(char[] buf, int n) {
      char[] buffer = new char[4];
      int readBytes = 0;
      boolean eof = false;
      while (!eof && readBytes < n) {
          int sz = read4(buffer);
          if (sz < 4) eof = true;//文件读完了，剩下不到4个
          int bytes = Math.min(n - readBytes, sz); //
          //每次从buffer 拷贝到buf，最多4个，也可能少于4个。
          System.arraycopy(buffer /* src */, 0 /* srcPos */, buf /* dest */, readBytes /* destPos */, bytes /* length */);
          readBytes += bytes;
      }//end while
      return readBytes;
   }
	
	/*
	Bottom up approach:
	Although the code for the top-down approach seems concise, it is actually subtle and there are a lot of hidden traps if you are not careful. The other approach is thinking recursively in a bottom-up fashion. If we reassign the bottom-level nodes before the upper ones, we won’t have to make copies and worry about overwriting something. We know the new root will be the left-most leaf node, so we begin the reassignment here.
	*/

  public static boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        if (m>n) {
        	return isOneEditDistance(t, s);
        }
        if (n-m>1) {
        	return false;
        }
        int i =0, shift = n-m;
        while (i<m && s.charAt(i)==t.charAt(i)) {
        	++i;
        }
        if (i==m) {
        	return shift > 0; // if two string are the same (shift==0), return false
        }
        if (shift==0) {
        	i++; // if n==m skip current char in s (modify operation in s)
        }
        while (i<m && s.charAt(i)==t.charAt(i+shift)) {
        	i++; // use shift to skip one char in t
        }
        return i == m;
    }
  

  public static List<String> findMissingRanges(int[] vals, int start, int end) {
       List<String> ranges = new ArrayList<String>();
       int prev = start - 1;
       for (int i=0; i<=vals.length; ++i) {
           int curr = (i==vals.length) ? end + 1 : vals[i];
           if ( curr-prev>=2 ) {
               ranges.add(getRange(prev+1, curr-1));
           }
           prev = curr;
       }
       return ranges;
   }

   private static String getRange(int from, int to) {
       return (from==to) ? String.valueOf(from) : from + "->" + to;
   }

  
  }