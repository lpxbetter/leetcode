import java.util.Arrays;

public class squezze {
	public class Solution {
	    public int threeSumClosest(int[] A, int target) {
	        if(A==null || A.length<=2) return 0; 
	    	Arrays.sort(A);
			int diff=A[0]+A[1]+A[2]-target;
	        for(int i=0;i<A.length-2;i++){
	            int curDiff=twoSum(A,target-A[i],i+1);
	            if( Math.abs(curDiff) < Math.abs(diff) ) diff = curDiff;
	        }
	        return diff+target;
	    }
	    public int twoSum(int[] A, int target,int start){
	        int i=start; 
	        int j=A.length - 1;
	        int cloest=A[i]+A[j]-target;
	        while(i<j){
	            int diff=A[i]+A[j]-target;
	            if(diff == 0) return 0;
	            if(Math.abs(diff) < Math.abs(cloest)) cloest=diff;
	            if(A[i]+A[j] > target){
	                j--;    
	            }
	            else{
	                i++;
	            }
	        }
	        return cloest;        
	    }
	}
}