public class quicksort{
	private static int[] A;
	public static void main(String[] args){
		int[] input={7,2,1,6,8,5,3,4};
		A=input;
		ptrArr(A);
		quicks(0,A.length-1);
		ptrArr(A);
	}
	
	public static int Partition( int start, int end){
		int pv=A[end];
		int idx = start;
		for(int i=start;i<end;i++){
			if(A[i]<=pv){
				int tmp=A[i];
				A[i]=A[idx];
				A[idx++]=tmp;
			}
		}
		int tmp = A[idx];
		A[idx]=A[end];
		A[end]=tmp;
		return idx;
	}
	public static void quicks( int start,int end){
		if(start>=end) return; 
		int idx=Partition(start,end);
		System.out.printf("idx=%d\n",idx);
		quicks(start,idx-1);
		quicks(idx+1,end);
	}
	
	public static void ptrArr(int[] num){
	    for(int k=0;k<num.length;k++){
	    	System.out.print(num[k]);
	    }
	    System.out.println();
	}
	
	
}