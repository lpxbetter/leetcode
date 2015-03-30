import java.io.*;
import java.util.*;
//import commLib.*;

import kthLargeInBST.Node;

public class trees {
	public static void main(String[] args){
		StringBuilder res = new StringBuilder();
		res.append("hello".charAt(0));
		System.out.println(res);
		String s="x123";
		System.out.println(s.substring(1));
		//String str = simplifyPath("/a/./b/../../c/");
		String str = simplifyPath("/");
		System.out.println(str);
		
	}
	
	
	public void sum(TreeNode root, int sum){
		ArrayList<Integer> item = new ArrayList<>();
		dosum(root,sum,item);
	}
	public void dosum(root,sum,item){
		if(root==null) return;
		if(sum==root.val){ 
			item.add(root.val);
			print;
		}
		dosum(root.left, sum-root.val,item);
		dosum(root.right, sum-root.val,item);
	}
	
	
    public TreeNode sortedArrayToBST(int[] num) {
        return doConvert(num,0,num.length);
    }
    public TreeNode doConvert(int[] num,int l, int r){
        if(l>r) return null;
        int m=(l+r)/2;
        TreeNode root=new TreeNode(num[m]);
        root.left=doConvert(num,l,m-1);
        root.right=doConvert(num,m+1,r);
        return root;
    }
    
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        return check(root.left,root.right);
    }
    public boolean check(TreeNode r1, TreeNode r2){
        if(r1==null && r2==null) return true;
        if(r1==null || r2==null) return false;
        if(r1.val != r2.val) return false; 
        return check(r1.left,r2.right) && check(r1.right,r2.left);
    }
    
	
    public static ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(root==null) return res;
        helper(root,sum,new ArrayList<Integer>(), res);
        return res;
    }
    public static void helper(TreeNode root,int sum,ArrayList<Integer> item,ArrayList<ArrayList<Integer>> res){
        if(root ==null)  return;
        item.add(root.val);
        if(root.left ==null && root.right==null && root.val ==sum) {
            res.add(new ArrayList<Integer>(item));
            return ;
        }
        if(root.left != null) {
            helper(root.left,sum-root.val,item,res);
            item.remove(item.size()-1);
        }
        if(root.right != null) {
            helper(root.right,sum-root.val,item,res);
            item.remove(item.size()-1); 
        }
    }
    
	
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null) return false;
        if(root.left==null && root.right ==null && root.val==sum) return true;
        return hasPathSum(root.left,sum-root.val) || hasPathSum(root.right, sum-root.val) ;
    }
	
	public static boolean isSubTree(TreeNode t1, TreeNode t2){
		if(t1==null && t2==null) return true;
		if(t2==null) return true;
		if(t1.val==t2.val) return isSame(t1,t2);
		return isSubTree(t1.left,t2) || isSubTree(t1.right,t2);
		
	}
	public static boolean isSame(TreeNode t1, TreeNode t2){
		if(t1==null && t2==null) return true;
		if(t1==null || t2==null) return false;
		if(t1.val!=t2.val) return false;
		return isSame(t1.left,t2.left) && isSame(t1.right,t2.right) ;
	}
	
    private static ArrayList<Integer> res = new ArrayList<>();
    private int sum = 0;
    public int maxPathSum(TreeNode root) {
        return helper(root);        
    }
    private int helper(TreeNode root){
        if(root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        int cur = root.val + Math.max(left,0) + Math.max(right, 0) ;
        if(cur > sum) sum = cur;
        return root.val + Math.max(Math.max(left,0), Math.max(right, 0));
    }
    
    

    
	
	
	 // Definition for binary tree
	  public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	     TreeNode(int x) { val = x; }
	 }
	  
	 //by me
	    public static String simplifyPath(String path) {
	        
	          if(path == null || path.length()==0)  
	    {  
	        return "";  
	    }  
	        LinkedList<String> stack = new LinkedList<String>();
	        StringBuilder res = new StringBuilder();
	        
	        for(int i=0;i<path.length();i++){
	            StringBuilder temp = new StringBuilder();
	            while(i<path.length() && path.charAt(i) == '/') i++;
	            while(i<path.length() && path.charAt(i) != '/') {
	            	temp.append(path.charAt(i)); 
	            	i++;
	            }
	            if(temp.toString().equals(".")) ;
	            else if(temp.toString().equals("..")) {
	                if(!stack.isEmpty()) stack.pop();
	            }
	            else stack.push(temp.toString());
	        }
	        //res.append('/');
	        while(!stack.isEmpty()){
	            //res.append(stack.pop());
	           String[] strs = stack.toArray(new String[stack.size()]);  
      for(int j=strs.length-1;j>=0;j--)  
      {  
        res.append("/"+strs[j]);  
      } 
      if(res.length() == 0) res.append('/');
	        }        
	        return res.toString();
	    }
	  
	/*Populating Next Right Pointers in Each Node
	 *   Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. 
If there is no next right node, the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.
Note:
You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
	 */
/*
	  void connect(TreeLinkNode *root) {
		    TreeLinkNode *head = root; // the left first node in every level
		    TreeLinkNode *cur = NULL;  // the current node in the upper level
		    TreeLinkNode *pre = NULL;  // the prev node in the downer level

		    while (head) {
		        cur = head;
		        head = pre = NULL;
		        // travel one level in a loop
		        while (cur) {
		            // left child exist
		            if (cur->left) {
		                if (pre) pre = pre->next = cur->left;
		                else head = pre = cur->left;

		            }
		            // right child exist
		            if (cur->right) {
		                if (pre) pre = pre->next = cur->right;
		                else head = pre = cur->right;
		            }
		            // next node in the same level
		            cur = cur->next;
		        }
		    }
		}
*/	  
	  
	  
	  /*Flatten Binary Tree to Linked List 
	   * Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
	   * record the previous node.
	   * 记录pre，刚开始pre==null，第一轮就是把pre=root。
	   * 然后递归处理root.left,等处理完这个。pre指向的就是现有res的最右边节点。
	   * 接着递归处理之前记录的root.right，新root就会继续接到pre的右边。 
	   * 过程中要记录好root.right. 因为中途会有pre=root, 然后pre.right会set成别的。
	   */
public void flatten(TreeNode root) {
    ArrayList<TreeNode> pre = new ArrayList<TreeNode>();
    pre.add(null);
    helper(root, pre);
}
private void helper(TreeNode root, ArrayList<TreeNode> pre)
{
    if(root == null)
        return;
    TreeNode right = root.right;
    if(pre.get(0)!=null)
    {
        pre.get(0).left = null;
        pre.get(0).right = root;
    }
    pre.set(0,root);
    helper(root.left, pre);
    helper(right, pre);
}

/*
Unique Binary Search Trees Total Accepted: 38124 Total Submissions: 103120 My Submissions Question Solution 
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/


/*Binary Tree Zigzag Level Order Traversal Total Accepted: 25472 Total Submissions: 96172 My Submissions Question Solution 
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 * 
这道题其实还是树的层序遍历Binary Tree Level Order Traversal，不过这里稍微做了一点变体，就是在遍历的时候偶数层自左向右，而奇数层自右向左。
在Binary Tree Level Order Traversal中我们是维护了一个队列来完成遍历，而在这里为了使每次都倒序出来，我们很容易想到用栈的结构来完成这个操作。有一个区别是这里我们需要一层一层的来处理（原来可以按队列插入就可以，因为后进来的元素不会先处理），所以会同时维护新旧两个栈，一个来读取，一个存储下一层结点。总体来说还是一次遍历完成，所以时间复杂度是O(n)，空间复杂度最坏是两层的结点，所以数量级还是O(n)（满二叉树最后一层的结点是n/2个）。代码如下：
[java] view plaincopy在CODE上查看代码片派生到我的代码片
*/
public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {  
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
    if(root==null)  
        return res;  
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();  
    int level=1;  
    ArrayList<Integer> item = new ArrayList<Integer>();  
    item.add(root.val);  
    res.add(item);  
    stack.push(root);  
    while(!stack.isEmpty())  
    {  
        LinkedList<TreeNode> newStack = new LinkedList<TreeNode>();  
        item = new ArrayList<Integer>();  
        while(!stack.isEmpty())  
        {  
            TreeNode node = stack.pop();  
            if(level%2==0)  
            {  
                if(node.left!=null)  
                {  
                    newStack.push(node.left);  
                    item.add(node.left.val);  
                }  
                if(node.right!=null)  
                {  
                    newStack.push(node.right);  
                    item.add(node.right.val);  
                }  
            }  
            else  
            {  
                if(node.right!=null)  
                {  
                    newStack.push(node.right);  
                    item.add(node.right.val);  
                }  
                if(node.left!=null)  
                {  
                    newStack.push(node.left);  
                    item.add(node.left.val);  
                }                     
            }  
        }  
        level++;  
        if(item.size()>0)  
            res.add(item);  
        stack = newStack;  
    }  
    return res;  
} 
/*
上面的算法其实还是一次广度优先搜索，只是在读取每一层结点交替的交换顺序。毕竟面试中像Binary Tree Level Order Traversal有时候考得太多了，面试官会觉得你肯定练过，所以会稍作变体，来考察对于编程和算法的基本理解。
*/



/*
 * 这道题要求实现树的层序遍历，其实本质就是把树看成一个有向图，然后进行一次广度优先搜索，这个图遍历算法是非常常见的，
 * 这里同样是维护一个队列，只是对于每个结点我们知道它的邻接点只有可能是左孩子和右孩子，具体就不仔细介绍了。
 * 算法的复杂度是就结点的数量，O(n)，空间复杂度是一层的结点数，也是O(n)。代码如下： 
[java] view plaincopy在CODE上查看代码片派生到我的代码片
*/
//by code ganker
public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {  
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
    if(root == null)  
        return res;  
    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();  
    queue.add(root);  
    int curNum = 0;  
    int lastNum = 1;  
    ArrayList<Integer> list = new ArrayList<Integer>();  
    while(!queue.isEmpty())  
    {  
        TreeNode cur = queue.poll();  
        lastNum--;  
        list.add(cur.val);  
        if(cur.left!=null)  
        {  
            queue.add(cur.left);  
            curNum ++;  
        }  
        if(cur.right!=null)  
        {  
            queue.add(cur.right);  
            curNum++;  
        }  
        if(lastNum==0)  
        {  
            lastNum = curNum;  
            curNum = 0;  
            res.add(list);  
            list = new ArrayList<Integer>();  
        }  
    }  
    return res;  
}  
/*
 * 层序遍历也是树的一种遍历方式，在某些题目中会比较实用，不过这样其实更接近于图的问题了，在有些树的题目中层序遍历提供了更好的方法，所以还是得熟悉哈。层序遍历也是树的一种遍历方式，在某些题目中会比较实用，不过这样其实更接近于图的问题了，在有些树的题目中层序遍历提供了更好的方法，所以还是得熟悉哈。这道题还有一个变体Binary Tree Zigzag Level Order Traversal，其实也是进行广度优先搜索，不过因为要求不同，要换一种数据结构来记录层节点，有兴趣可以看看。
 */

/*
 * 原题链接: http://oj.leetcode.com/problems/binary-tree-inorder-traversal/ 
通常，实现二叉树的遍历有两个常用的方法：一是用递归，二是使用栈实现的迭代方法。下面分别介绍。
递归应该最常用的算法，相信大家都了解，算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)。代码如下： 
*/
public ArrayList<Integer> inorderTraversal(TreeNode root) {  
    ArrayList<Integer> res = new ArrayList<Integer>();  
    helper(root, res);  
    return res;  
}  
private void helper(TreeNode root, ArrayList<Integer> res)  
{  
    if(root == null)  
        return;  
    helper(root.left,res);  
    res.add(root.val);  
    helper(root.right,res);  
}

/*接下来是迭代的做法，其实就是用一个栈来模拟递归的过程。所以算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)。
 * 过程中维护一个node表示当前走到的结点（不是中序遍历的那个结点），实现的代码如下：
*/
public ArrayList<Integer> inorderTraversal(TreeNode root) {  
    ArrayList<Integer> res = new ArrayList<Integer>();  
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();  
    while(root!=null || !stack.isEmpty())  
    {  
        if(root!=null)  
        {  
            stack.push(root);  
            root = root.left;  
        }  
        else  
        {  
            root = stack.pop();  
            res.add(root.val);  
            root = root.right;  
        }  
    }  
    return res;  
}  
/*
最后我们介绍一种比较复杂的方法，这个问题我有个朋友在去google onsite的时候被问到了，就是如果用常量空间来中序遍历一颗二叉树。这种方法叫 Morris Traversal。想用O(1)空间进行遍历，因为不能用栈作为辅助空间来保存付节点的信息，重点在于当访问到子节点的时候如何重新回到父节点（当然这里是指没有父节点指针，如果有其实就比较好办，一直找遍历的后驱结点即可）。Morris遍历方法用了线索二叉树，这个方法不需要为每个节点额外分配指针指向其前驱和后继结点，而是利用叶子节点中的右空指针指向中序遍历下的后继节点就可以了。
算法具体分情况如下：
1. 如果当前结点的左孩子为空，则输出当前结点并将其当前节点赋值为右孩子。
2. 如果当前节点的左孩子不为空，则寻找当前节点在中序遍历下的前驱节点（也就是当前结点左子树的最右孩子）。接下来分两种情况：
 a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点（做线索使得稍后可以重新返回父结点）。然后将当前节点更新为当前节点的左孩子。
 b) 如果前驱节点的右孩子为当前节点，表明左子树已经访问完，可以访问当前节点。将它的右孩子重新设为空（恢复树的结构）。输出当前节点。当前节点更新为当前节点的右孩子。 
代码如下：
*/
public ArrayList<Integer> inorderTraversal(TreeNode root) {  
    ArrayList<Integer> res = new ArrayList<Integer>();  
    TreeNode cur = root;  
    TreeNode pre = null;  
    while(cur != null)  
    {  
        if(cur.left == null)  
        {  
            res.add(cur.val);  
            cur = cur.right;  
        }  
        else  
        {  
            pre = cur.left;  
            while(pre.right!=null && pre.right!=cur)  
                pre = pre.right;  
            if(pre.right==null)  
            {  
                pre.right = cur;  
                cur = cur.left;  
            }  
            else  
            {  
                pre.right = null;  
                res.add(cur.val);  
                cur = cur.right;  
            }  
        }  
    }  
    return res;  
} 
/* 
接下来我们来分析一下时间复杂度。咋一看可能会觉得时间复杂度是O(nlogn)，因为每次找前驱是需要logn，总共n个结点。但是如果仔细分析会发现整个过程中每条边最多只走2次，一次是为了定位到某个节点，另一次是为了寻找上面某个节点的前驱节点，而n个结点的二叉树中有n-1条边，所以时间复杂度是O(2*n)=O(n)，仍然是一个线性算法。空间复杂度的话我们分析过了，只是两个辅助指针，所以是O(1)。

总结一下，上面介绍了三种方法递归，迭代和Morris来实现树的中序遍历，这道题看上去很简单，但是大家还是不能满足于递归的方法，有些面试还是会在简单问题上要求很高的。对于树的 Binary Tree Preorder Traversal 和 Binary Tree Postorder Traversal，也有相应的三种方法，大家可以练习一下。
 */
/*
原题链接: http://oj.leetcode.com/problems/binary-tree-preorder-traversal/ 
跟Binary Tree Inorder Traversal一样，二叉树的先序遍历我们仍然介绍三种方法，第一种是递归，第二种是迭代方法，第三种是用线索二叉树。
递归是最简单的方法，算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)。代码如下：
*/
public ArrayList<Integer> preorderTraversal(TreeNode root) {  
    ArrayList<Integer> res = new ArrayList<Integer>();  
    helper(root, res);  
    return res;  
}  
private void helper(TreeNode root, ArrayList<Integer> res)  
{  
    if(root == null)  
        return;  
    res.add(root.val);  
    helper(root.left,res);  
    helper(root.right,res);  
}  
/*
接下来是迭代的做法，其实就是用一个栈来模拟递归的过程。所以算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)。实现的代码如下： 
*/
public ArrayList<Integer> preorderTraversalIteratively(TreeNode root) {  
    ArrayList<Integer> res = new ArrayList<Integer>();  
    if(root == null)  
        return res;  
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();  
    while(root!=null || !stack.isEmpty())  
    {  
        if(root!=null)  
        {  
            stack.push(root);  
            res.add(root.val);  
            root = root.left;  
        }  
        else  
        {  
            root = stack.pop();  
            root = root.right;  
        }  
    }  
    return res;  
}
/*
最后使用Morris遍历方法，这个方法不需要为每个节点额外分配指针指向其前驱和后继结点，而是利用叶子节点中的右空指针指向中序遍历下的后继节点就可以了。具体的分析可以参见Binary Tree Inorder Traversal，中序和先序方法上是完全一样的，只是访问结点的时机不同而已。这种方法的缺陷在于会暂时性的改变结点的内容，从编程健壮性和封装性来说不是特别好（比如说传进来的树结点很可能是const的变量，不希望对它做任何改变）。时间复杂度和空间复杂度如同Binary Tree Inorder Traversal中分析的，分别是O(n)和O(1)。代码如下：
*/
public ArrayList<Integer> preorderTraversal(TreeNode root) {  
    ArrayList<Integer> res = new ArrayList<Integer>();  
    TreeNode cur = root;  
    TreeNode pre = null;  
    while(cur != null)  
    {  
        if(cur.left == null)  
        {  
            res.add(cur.val);  
            cur = cur.right;  
        }  
        else  
        {  
            pre = cur.left;  
            while(pre.right!=null && pre.right!=cur)  
                pre = pre.right;  
            if(pre.right==null)  
            {  
                res.add(cur.val);  
                pre.right = cur;  
                cur = cur.left;  
            }  
            else  
            {  
                pre.right = null;  
                cur = cur.right;  
            }  
        }  
    }  
    return res;  
}  
//上面介绍了三种方法来实现树的先序遍历，这种题目看上去很简单，但是大家还是不能满足于递归的方法，有些onsite面试还是会在简单问题上要求很高的。

/*
原题链接: http://oj.leetcode.com/problems/binary-tree-postorder-traversal/ 
跟Binary Tree Inorder Traversal以及Binary Tree Preorder Traversal一样，二叉树的后序遍历我们还是介绍三种方法，第一种是递归，第二种是迭代方法，第三种是用线索二叉树。 递归还是那么简单，算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)。代码如下：
*/
public ArrayList<Integer> postorderTraversal(TreeNode root) {  
    ArrayList<Integer> res = new ArrayList<Integer>();  
    helper(root, res);  
    return res;  
}  
private void helper(TreeNode root, ArrayList<Integer> res)  
{  
    if(root == null)  
        return;  
    helper(root.left,res);  
    helper(root.right,res);  
    res.add(root.val);  
}  

/*
上面迭代实现的思路虽然清晰，但是实现起来还是分情况太多，不容易记忆。我后来再看wiki的时候发现有跟Binary Tree Inorder Traversal和Binary Tree Preorder Traversal非常类似的解法，容易统一进行记忆，思路可以参考其他两种，区别是最下面在弹栈的时候需要分情况一下：
1）如果当前栈顶元素的右结点存在并且还没访问过（也就是右结点不等于上一个访问结点），那么就把当前结点移到右结点继续循环；
2）如果栈顶元素右结点是空或者已经访问过，那么说明栈顶元素的左右子树都访问完毕，应该访问自己继续回溯了。
下面列举一下代码：
*/

public List<Integer> postorderTraversalIteratively(TreeNode root) {  
    List<Integer> res = new ArrayList<Integer>();  
    if(root == null)  
    {  
        return res;  
    }  
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();  
    TreeNode pre = null;  
    while(root != null || !stack.isEmpty())  
    {  
        if(root!=null)  
        {  
            stack.push(root);  
            root = root.left;  
        }  
        else  
        {  
            TreeNode peekNode = stack.peek();  
            if(peekNode.right!=null && pre != peekNode.right)  
            {  
                root = peekNode.right;  
            }  
            else  
            {  
                stack.pop();  
                res.add(peekNode.val);  
                pre = peekNode;  
            }  
        }  
    }  
    return res;  
}

}
