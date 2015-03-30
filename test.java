import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import LeastCommonAnc.TreeNode;


public class test{
	
	class UndirectedGraphNode{
		int label;
		ArrayList<UndirectedGraphNode> neighbors;
		UndirectedGraphNode(int x){
			label=x;
			neighbors=new ArrayList<>();
		}
	};
	
	public TreeNode kthLarge(TreeNode node, int[] A, int k){
		if(node==null) return null;
		TreeNode nr = kthLarge(node.right,A,k);
		if(nr!=null) return nr;
		A[0]++;
		if(A[0]==k) return node;
		TreeNode nl=kthLarge(node.left, A,k);
		if(nl!=null) return nl;
	}
	
	
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        //queue
        if(node==null) return null;
        LinkedList<UndirectedGraphNode> queue= new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode,UndirectedGraphNode> map=new HashMap<>();
        queue.add(node);
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map.put(node,copy);
        while(!queue.isEmpty()){
            UndirectedGraphNode cur=queue.poll();
            for(UndirectedGraphNode neigh:cur.neighbors){
                if(!map.containsKey(neigh)){
                    queue.add(neigh);
                    map.put(neigh,new UndirectedGraphNode(neigh.label));
                }
                map.get(cur).neighbors.add(map.get(neigh));
            }
        }//end while
        return map.get(node);
    }
	


    	class DirectedGraphNode{
    		char label;
            //boolean ;
    		ArrayList<DirectedGraphNode> neighbors;
    		DirectedGraphNode(char x){
    			label=x;
    			neighbors=new ArrayList<>();
    		}
    	};
    /*
    Start from the vertex with only outgoing edges (if there is more than one or no such vertex, fail).

   Do a BFS. If you encounter an already visited vertex, it's not a tree.

   If you're done and there are vertices left, it's not a tree - the graph is not connected.

   To check for a binary tree, additionally just check if each vertex has at most 2 outgoing edges.
    */
   public static void main(String args[] ) throws Exception {
       /* Enter your code here. Read input from STDIN. Print output to STDOUT */
       String[] inp = {"a b","a c","b d","c d"};
       DirectedGraphNode temp;
       DirectedGraphNode root;
       HashMap<DirectedGraphNode,Integer> map = new HashMap<DirectedGraphNode,Integer>();
       
       for(String s:inp){
           char[] arr = s.split(' ');
           temp1 =new DirectedGraphNode(arr[0]);
           temp2 =new DirectedGraphNode(arr[1]);
           if(!map.contains(temp1)){
               temp1.neighbors.add(temp2);
               map.put(temp1,true);
           }
           else{
               temp1.neighbors.add(temp2);
           }
           
           map.put(temp2,false);
       }
       boolean hasRoot = false;
       for (Map.Entry<Integer, Integer> entry : map.entrySet()) {  
           if(entry.getValue()){
               root=entry.getKey();
               hasRoot=true;
               break;
           }
       }
       if(!hasRoot) {System.out.println("false");return;}
       return isTree;
   }
   
   public boolean isTree(DirectedGraphNode root){
	   
   }

	/*
	public static void main(String[] args){
		

		//System.out.println(longestCommonPrefix(new String[]{})  );
		//System.out.println(isValid("[]") );
		//System.out.println("abcd".substring(1) );
		//System.out.println("abcd".charAt(0));
		
		System.out.println(pathNum(1,1));
	}
*/
	/*
	 * 
	void DFS(TreeNode root){
		if(root==null) return;
		DFS(root.left);
		DFS(root.right);
		
	}*/


	
	public static int pathNum(int m,int n){
		int[][] DP=new int[m][n];
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(i==0 || j==0) DP[i][j]=1;
				else
				DP[i][j]=DP[i-1][j]+DP[i][j-1];
			}
		}
		return DP[m-1][n-1];
	}
	
	public static int pathCost(int[][] M,int m,int n){
		int[][] DP=new int[m][n];
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(i==0 || j==0) DP[i][j]=M[i][j];
				else
				DP[i][j]=Math.min(DP[i-1][j]+DP[i][j-1])+M[i][j];
			}
		}
		return DP[m-1][n-1];
	}	
	
	public int substr(String s1,String s2){
		int m =s1.length();
		int n=s2.length();
		int maxLen=0;
		int[][] DP=new int[m+1][n+1];
		for(int i=0;i<=m;i++){
			for(int j=0;j<=n;j++){
				if(i==0||j==0) DP[i][j]=0;;
				if(s1.charAt(i-1)!=s2.charAt(j-1)) {DP[i][j]=0;}
				else{
					DP[i][j]=DP[i-1][j-1]+1;
					if(>maxLen) maxLen=DP[i][j];
				}
			}
		}
		return DP[m][n];
	}
	
	public int subseq(String s1,String s2){
		int m =s1.length();
		int n=s2.length();
		int maxLen=0;
		int[][] DP=new int[m+1][n+1];
		for(int i=0;i<=m;i++){
			for(int j=0;j<=n;j++){
				if(i==0||j==0) DP[i][j]=0;;
				if(s1.charAt(i-1)!=s2.charAt(j-1)) {DP[i][j]=Math.max(DP[i-1][j],DP[i][j-1]) ;}
				else{
					DP[i][j]= (DP[i-1][j-1]) +1;
					if(DP[i][j]>maxLen) maxLen=DP[i][j];
				}
			}
		}
		return DP[m][n];
	}
	
	public int LIS(int[] A){
		int n=A.length;
		int[] T= new int[n];
		int max=1;
		for(int i=0;i<n;i++){
			T[i]=1;
		}
		for(int i=0;i<n;i++){
			for(int j=0;j<i;j++){
				if(A[j]<A[i] && A[j]+1 > T[i]) {
					T[i]=A[j]+1;
					max=Math.max(T[i], max);
				}
			}
		}
		return max;
	}
	
	
	
	class Node{
		int val;
		Node next=null;
		Node(int x){
			val=x;
		}
	}

	
	public boolean sub(String s1, String s2){
		String newStr=s2+s2;
		return issubstring(newStr,s1);
	}
	
	public static String countSay(String s){
		int cnt=1;
		int max=1;
		if(s.length()==1) return s;
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<s.length();i++){
			if(s.charAt(i)==s.charAt(i-1)){
				cnt++;
				System.out.println(cnt);
				continue;
			}
			else{
				sb.append(s.charAt(i-1)+""+cnt);
				System.out.println(sb.toString());
				if(cnt>max) max=cnt;
				cnt=1;
			}
		}
		
		sb.append(s.charAt(s.length()-1)+""+cnt);
		if(max==1) return s;
		return sb.toString();
	}
	public static rep(String s){
		int n=s.length();
		int cnt=0;
		char[] A=s.toCharArray(s);
		for(int c:A){
			if(c==' ') cnt++;
		}
		int m = n+cnt*2;
		for(int i=n-1;i>=0;i--){
			if(A[i]==' ') {
				A[m--]='0';
				A[m--]='2';
				A[m--]='%';
			}
			else A[m--]=A[i--];
		}
		
		return s.valueOf(A);
	}

	public boolean oneEditDistance(String s, String t){
		int m=s.length();
		int n=t.length();
		if(m<n) return oneEditDistance(t,s); 
		int diff = m-n;
		if(diff>1) return false;
		
	}
    
    public static String longestCommonPrefix(String[] strs) {
    	if(strs.length==0) return "";
        int k=0;
        while(true){
            for(int i=0;i<strs.length;i++){
                if(strs[i].length()==0) return "";
                System.out.println(k);
                if(k>=strs[i].length()) return strs[0].substring(0,k);
                if(strs[i].charAt(k) != strs[0].charAt(k)) return strs[0].substring(0,k);
            }
            k++;
        }
    }
    
}