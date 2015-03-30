import java.io.*;
import java.util.*;

/*
 * The time complexity is O(N) if there is N nodes in the tree. 
 * We traverse the tree three times. Two times to check whether two nodes are both
 * in the tree. And one time to find the LCA.The reason we need to check whether two nodes 
 * are both in the tree is that findLCAInner() will still return a LCA when there is only one
 * of the two nodes is in the tree. But it should return null.
 * 
 * However, actually we don't need to traverse so many times.
 * We can check whether one node is missing in the tree in one traversal. 
 * We can even don't check before finding the LCA. We can check that during finding. 
 * We can do this by returning two values: the node and a flag indicating whether this node
 * is actually the LCA. But this will reduce the readability of the code. I didn't implemented 
 * this solution.
 *  
 * The time complexity cannot be better than O(N) because potentially we need to look at every 
 * node in the tree no matter how we do that. 
 */
public class LeastCommonAnc {
	
	public static class TreeNode{
		TreeNode left;
		TreeNode right;
		int val;
		TreeNode(int x){
			val = x;
		}
	}
	
	/*
	 *  We traverse the entire tree recursively. 
	 *  If the root equals one of these two nodes,no need to go deeper. Because weather 
	 *  the other node is in the left or right subtree. This root is the LCA according to 
	 *  the definition of LCA. If the root doesn't match any one, we check its left branch 
	 *  and right branch. There will be 3 cases: 
	 *  1) Two nodes are found from different branches. In this case, the root is the LCA.   
	 *  2) Two nodes are found from the same branch, then the LCA is the LCA of this sub-branch.
	 *  3) None of the nodes are found in the tree. There is no LCA. Need to return null.  
	 */
	public static TreeNode findLCAInner(TreeNode root, TreeNode n1, TreeNode n2){
		// Base case
		if(root == null) return null;
		
		// If root matches at least one of the nodes, no need to continue, root is the LCA
		if(root.val == n1.val || root.val == n2.val ) return root;
		
		TreeNode resL = findLCAInner(root.left, n1, n2);
		TreeNode resR = findLCAInner(root.right, n1, n2);
		
		// n1 and n2 found in different subtrees,then root is the LCA
		if(resL != null && resR != null) return root;
		
		// Either two nodes are found in the same subtree or none of them is found in the subtrees.  
		return (resL != null) ? resL : resR;
	}
	
	/*
	 * Check whether the node is in the tree. 
	 */
	public static Boolean findNode(TreeNode root, TreeNode node){
		if(root == null) return false;
		if(root.val == node.val) return true;
		return findNode(root.left, node) || findNode(root.right, node);
	}
	
	/*
	 * If one of the nodes is not in the tree,should return null. We need to check before 
	 * we call findLCAInner because findLCAInner isn't able to deal with this case.
	 */
	public static TreeNode findLCA(TreeNode root, TreeNode n1, TreeNode n2){
		if(root == null) return null;
		
		if(n1 == null || n2 == null) return null;
		
		if(!findNode(root, n1) || !findNode(root, n2) ) return null;
		
		return findLCAInner(root, n1, n2);
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