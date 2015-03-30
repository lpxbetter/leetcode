
public class kthLargeInBST{
	public static int count =0;
	public static class Node{
		int val;
		Node leftChild=null;
		Node rightChild=null;
		Node(int x){
			val=x;
		}
	}
	/*
	public static Node getKthNode(Node root,int k){
		if(root==null) return null;
		Node nr= getKthNode(root.rightChild,k);
		if(nr!=null) return nr;
		count++;
		if(count==k) return root;
		Node nl=getKthNode(root.leftChild, k);
		if(nl!=null) return nl;
		return null;
		
	}
	*/
	public static Node getKthNode(Node root,int[] A){
		if(root==null) return null;
		Node nr= getKthNode(root.leftChild,A);
		if(nr!=null) return nr;
		A[0]--;
		if(A[0]==0) return root;
		Node nl=getKthNode(root.rightChild, A);
		if(nl!=null) return nl;
		return null;
	}
	
	public static void main(String[] args){
		kthLargeInBST cur=new kthLargeInBST();
		 Node a = new Node(8);
	     Node b = new Node(3);
	     Node c = new Node(10);
	     Node d = new Node(1);
	     Node e = new Node(6);
	     Node f = new Node(14);
	     Node g = new Node(4);
	     Node h = new Node(7);
	     Node i = new Node(13);
	     
	     a.leftChild = b;
	     a.rightChild = c;
	     b.leftChild = d;
	     b.rightChild = e;
	     c.rightChild = f;
	     e.leftChild = g;
	     e.rightChild = h;
	     f.leftChild = i;
	     InorderTraverse(a);
	     //the third largest value in BST
	     System.out.println(cur.getKthNode(a, new int[]{1}).val );
	     System.out.println(cur.getKthNode(a, new int[]{2}).val );
	     System.out.println(cur.getKthNode(a, new int[]{3}).val );
	     System.out.println(cur.getKthNode(a, new int[]{4}).val );
	     System.out.println(cur.getKthNode(a, new int[]{5}).val );
	     System.out.println(cur.getKthNode(a, new int[]{6}).val );
		
	}

    
	public static void modifyBSTUntil(Node root,int[] sum){
		if(root==null) {
			return;
		}
		modifyBSTUntil(root.rightChild,sum);
		sum[0]+=root.val;
		root.val+=sum[0];
		modifyBSTUntil(root.leftChild,sum);
	}
	
	
	public static void InorderTraverse(Node root){
		if(root==null) return;
		InorderTraverse(root.leftChild);
		System.out.print(root.val+" ");
		InorderTraverse(root.rightChild);
	}
}	
