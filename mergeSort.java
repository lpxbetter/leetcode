
public class mergeSort {
	private static int[] A; // the array need to be sorted
	private static int[] tmp; //temp array as same size as A's size
	
	public static void main(String[] args){
		int[] input = {45,23,11,89,77,98,4,28,65,43};
		//int[] input = {2,1,3};
		ptrArr(input);
		mergeSort ms = new mergeSort();
		ms.mergeSort(input);
		ms.ptrArr(input);
	}
	
	public static void mergeSort(int[] input){
		A = input;
		tmp = new int[A.length];
		doMergeSort(0,A.length-1);
	}
	public static void doMergeSort(int l, int r){
		if(l>=r) return;
		int m=(l+r)/2;
		doMergeSort(l,m);
		doMergeSort(m+1,r);
		merge(l,m,r);
	}
	public static void merge(int l, int m, int r){
		//System.out.printf("l=%s,m=%s,r=%s\n",l,m,r);
		
		for(int i=l;i<=r;i++){
			tmp[i]=A[i];
		}
		int i=l;
		int j=m+1;
		int k=l;
		while(i<=m && j<=r){
			if(tmp[i]<tmp[j]) A[k++]=tmp[i++];
			else A[k++] = tmp[j++];
		}
		while(i<=m){
			A[k++]=tmp[i++];
		}
	}
	public static void ptrArr(int[] A){
		for(int x : A){
			System.out.print(String.valueOf(x) + ",");
		}
		System.out.println("\n");
	}
}