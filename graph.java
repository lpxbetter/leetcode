import java.io.*;
import java.util.*;
//import commLib.*;

public class graph {
	public static void main(String[] args){
		StringBuilder res = new StringBuilder();
		res.append("hello".charAt(0));
		System.out.println(res);
		int[] A={1,1,1,2,2};
		
		System.out.println(rst);
		
	}
	
	public class UndirectedGraphNode{
		int val;
		ArrayList<UndirectedGraphNode> neighbors=null;
		UndirectedGraphNode(int x){
			val = x;
		}
	}
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode root){
		if(root==null) return root;
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
		LinkedList<UndirectedGraphNode> stack = new LinkedList<>();
		stack.push(root);
		map.put(root,new UndirectedGraphNode(root.val) );
		while( !stack.isEmpty()){
			UndirectedGraphNode cur=stack.pop();
			for(UndirectedGraphNode n : cur.neighbors){
				if(!map.containsKey(n)){
					map.put(n, new UndirectedGraphNode(n.val) );
					stack.push(n);
				}
				map.get(cur).neighbors.add( map.get(n) );
			}
		}
		return map.get(root);
	}
	
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode root){
		if(root==null) return root;
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
		map.put(root, new UndirectedGraphNode(root.val));
		return helper(root,map);
	}
	public UndirectedGraphNode helper(UndirectedGraphNode node, HashMap<UndirectedGraphNode,UndirectedGraphNode>map){
		for(UndirectedGraphNode n : node.neighbors){
			if(!map.containsKey(n)){
				map.put(n, new UndirectedGraphNode(n.val) );
				helper(n,map);
			}
		}
		return map.get(node);
	}
	
	public class Solution {
	    public maxSum =0;
	    public int maxPathSum(TreeNode root) {
	        if(root == null) return 0;
	        //if(root.left == null && root.right == null) return ;
	        int left = maxPathSum(root.left);
	        int right = maxPathSum(root.right);
	        maxSum =Math.max(maxSum,root.val+left+right);
	        return root.val+Math.max(left,right);
	    }
	}	

public int ladderLength(String start, String end, Set<String> dict) {
    if(start==null || end==null || start.length()==0 || end.length()==0 || start.length()!=end.length())  
        return 0; 
    int level=1;
    int lastNum=1;
    int curNum=0;
    HashSet<String> visited = new HashSet<>();
    LinkedList<String> queue=new LinkedList<>();
    
    while(!queue.isEmpty()){
        String temp=queue.poll();
        lastNum--;
        for(int i=0;i<temp.length();i++){
            char[] charArr=temp.toCharArray();
            for(char c='a';c<='z';c++){
                charArr[i]=c;
                String newStr=new String(charArr);
                if(!visited.contains(newStr) && dict.contains(newStr) ){
                    curNum++;
                    visited.add(newStr);
                    queue.offer(newStr);
                    if(newStr.equals(end)){
                        return level+1;
                    }
                }
            }
        if(lastNum==0){
            level++;
            lastNum=curNum;
            curNum=0;
        }
        }//end for i
    }//end while
    return 0;
}

}