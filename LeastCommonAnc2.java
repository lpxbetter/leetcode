import java.io.*;
import java.util.*;

/*
This is advanced version of LeastCommonAnc class. Only need one traversal of the tree.  
 */
public class LeastCommonAnc2 {
	
	public static class TreeNode{
		TreeNode left;
		TreeNode right;
		int val;
		TreeNode(int x){
			this.val = x;
		}
	}
	public static class Result {
		public TreeNode node;
		public boolean isLCA;
		public Result(TreeNode n, boolean isLCA) {
			this.node = n;
			this.isLCA = isLCA;
		}
	}
	
	/*
	 *  We traverse the entire tree recursively. 
	 *  We check root's left branch and right branch. There will be 6 cases: 
	 *  1) The LCA was found in the left sub tree.
	 *  2) The LCA was found in the right sub tree.
	 *  3) Two nodes are found from different branches of root. Then, the root is the LCA.
	 *  4) The root matches one of the nodes, and there is another node in its left or right subtree.
	 *  5) The root matches one of the nodes, and there is NOT another node in its left and right subtree.
	 *  6) None of the above cases, then the LCA cannot be found
	 *  
	 */
	public static Result findLCAInner(TreeNode root, TreeNode n1, TreeNode n2){
		// Base case
		if(root == null) return new Result(null,false);
		
		Result resL = findLCAInner(root.left, n1, n2);
		if(resL.isLCA) return resL; // LCA was found
		
		Result resR = findLCAInner(root.right, n1, n2);
		if(resR.isLCA) return resR; // LCA was found
		
		// n1 and n2 found in different subtrees,then root is the LCA
		if(resL.node != null && resR.node != null) return new Result(root, true);

		// If root matches one node,but there isn't another node in its left and right subtree,
		// Then we should return false.
		if(root.val == n1.val || root.val == n2.val ) {
			boolean isLCA  = (resL.node != null || resR.node != null) ? true : false;
			return new Result(root, isLCA);
		}
		
		// root is not the LCA and also LCA was not found in either subtree.Then LCA cannot be 
		// found in the tree.
		return new Result( resL.node != null ? resL.node : resR.node, false);
	}
	
	/*
	 * If one of the nodes is not in the tree,should return null. 
	 */
	public static TreeNode findLCA(TreeNode root, TreeNode n1, TreeNode n2){
		if(root == null) return null;
		if(n1 == null || n2 == null) return null;
		
		Result res = findLCAInner(root, n1, n2);
		if(res.isLCA) return res.node;
		else return null;
	}
	
	/*
	 * For test
	 */
	public static void printResult(String str, TreeNode resNode){
		System.out.println(str);
		if(resNode == null) System.out.println("null");
		else System.out.println(resNode.val);
	}
	
	public static void main(String[] args){
		/*
		 1
		/ \
	   2   3
	   / \
	  4   5
	  */
		TreeNode root = new TreeNode(1);
		root.left= new TreeNode(2);
		root.right= new TreeNode(3);
		root.left.left= new TreeNode(4);
		root.left.right= new TreeNode(5);
		
		TreeNode resNode = findLCA(root,new TreeNode(4), new TreeNode(5));
		printResult("LCA of 4,5:", resNode);
		
		resNode = findLCA(root,new TreeNode(4), new TreeNode(3));
		printResult("LCA of 4,3:", resNode);
		
		// When one node is the parent of another 
		resNode = findLCA(root,new TreeNode(1), new TreeNode(3));
		printResult("LCA of 1,3:" , resNode);
		
		// When one node is not in the tree
		resNode = findLCA(root,new TreeNode(1), new TreeNode(6));
		printResult("LCA of 1,6:" , resNode);
		
		// When both nodes are not in the tree
		resNode = findLCA(root,new TreeNode(6), new TreeNode(7));
		printResult("LCA of 6,7:" , resNode);	
		
		// Corner cases
		resNode = findLCA(root, null, new TreeNode(2));
		printResult("LCA of null,2:" , resNode);
		
		resNode = findLCA(null, new TreeNode(1), new TreeNode(2));
		printResult("When root is null:" , resNode);
		
		resNode = findLCA(root, new TreeNode(1), new TreeNode(1));
		printResult("LCA of 1,1:" , resNode);		
		
	}
	
}