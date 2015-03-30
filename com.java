import java.io.*;
import java.util.*;

public class com {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		System.out.println(s);
		StringBuilder sb=new StringBuilder();
		//sb.deleteCharAt(index)
		
		//int i = System.in.read();
		//System.out.print(i);
		//char c='a';
        //String s="12 34";
        String[] strs=s.split(" ");
        int n0=Integer.parseInt(strs[0]);        
        System.out.println(n0);
        
	/*
		String s=new String() ;
		ArrayList<StringBuilder> res = new ArrayList<StringBuilder>();
		res.add(new StringBuilder("ok"));
		res.get(0).append("w");
		System.out.println(res);
		*/		
		
	}
	
	
	public ArrayList<String> letterCombinations(String digits) {
	    ArrayList<String> res = new ArrayList<String>();
	        res.add("");
	    if(digits==null || digits.length()==0)
	        return res;
	    for(int i=0;i<digits.length();i++)
	    {
	        String letters = getLetters(digits.charAt(i));
	        ArrayList<String> newRes = new ArrayList<String>();
	        for(int j=0;j<res.size();j++)
	        {
	            for(int k=0;k<letters.length();k++)
	            {    
	                newRes.add(res.get(j)+Character.toString(letters.charAt(k)));
	            }
	        }
	        res = newRes;
	    }
	    return res;
	}
	private String getLetters(char digit)
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
	
	public static void ptrArr(int[] num){
	    for(int k=0;k<num.length;k++){
	    	System.out.print(num[k]);
	    }
	    System.out.println();
	}
	
	public static void out(String str){
		//System.out.println(str+getLineNumber());
		System.out.println(str);
	}
    public static void outInt(int i){
    	System.out.println(String.valueOf(i));
    }
    
    public static String getLineNumber() {
        int lineNum = Thread.currentThread().getStackTrace()[2].getLineNumber();
        return "\tLine:"+Integer.toString(lineNum);
    }
    
    
	
}