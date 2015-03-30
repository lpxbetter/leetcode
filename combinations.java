import java.io.*;
import java.util.*;
public class combinations {
	
	public static void main(String[] args){
		combine(3,2);
		
	}
	
	
	public static ArrayList<ArrayList<Integer>> combine(int n, int k) {
	    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	    if(n<=0 || n<k)
	        return res;
	    helper(3,k,1,new ArrayList<Integer>(), res);
	    System.out.println(res);
	    return res;
	}
	
	private static void helper(int n, int k, int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res)
	{
		//res.add(item);
		System.out.println(item);
	    
		/*
		if(item.size()==k)
	    {
	        res.add(new ArrayList<Integer>(item));
	        System.out.println(item);
	        return;
	    }
	    */
	    
		
	    for(int i=start;i<=n;i++)
	    {
	    	//System.out.println("start:"+start);
	        item.add(i);
	        //System.out.println("item pre:"+item);
	        helper(n,k,i+1,item,res);
	        item.remove(item.size()-1);
	        //System.out.println("item aft:"+item);
	    }
	}
	
}