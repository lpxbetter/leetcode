import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class myheap{
	public class Item{
		String name;
		int val;
		Item(String str,int x){
			name = str;
			val = x;
		}
	}
	public static void main(String[] args){
		int N=10;
		int[] A = new int[N];
		for(int i=0;i<N;i++){
			A[i]=i;
		}
		List<Integer> res = topK(A);
		System.out.println(res);
		
	}
	public static Comparator<Integer> comp = new Comparator<Integer>(){
		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return o1-o2;
		}
	};
	
	public static List<Integer> topK(int[] A){
		ArrayList<Integer> res = new ArrayList<>();
		
		int k=3;
		//PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k,comp);
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i=0;i<A.length;i++){
			pq.add(A[i]);
			if(pq.size() >k ) pq.poll();
		}
		while(!pq.isEmpty()){
			int e =pq.poll();
			System.out.println(e);
			res.add(e);
		}
		return res;
	}
	
	
}