import java.io.*;
import java.util.*;
//import commLib.*;

public class twoPointers {
	public static void main(String[] args){
		StringBuilder res = new StringBuilder();
		res.append("hello".charAt(0));
		System.out.println(res);
		int[] A={1,1,1,2,2};
		lengthOfLongestSubstringTwoDistinct("aabaac");
		//System.out.println(Integer.MIN_VALUE);
		
	}


    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int i = 0, j = -1, maxLen = 0;
        for (int k = 1; k < s.length(); k++) {
        	System.out.println("i="+i+" j="+j+" k="+k);
            if (s.charAt(k) == s.charAt(k - 1)) continue;
            System.out.println("Aft i="+i+" j="+j+" k="+k);
            
            if (j >= 0 && s.charAt(j) != s.charAt(k)) {
                maxLen = Math.max(k - i, maxLen);
                i = j + 1; 
                System.out.println("update i="+i+"  maxLen="+ maxLen);
            }
            j = k - 1;  
        }
        return Math.max(s.length() - i, maxLen);
    }
    
    
}