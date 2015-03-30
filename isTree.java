import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;


class isTree {

    public static class DirectedGraphNode{
    		String label;
    		ArrayList<DirectedGraphNode> neighbors;
    		DirectedGraphNode(String x){
    			label=x;
    			neighbors=new ArrayList<>();
    		}
    	}

   public static void main(String args[] ) throws Exception {
       /* Enter your code here. Read input from STDIN. Print output to STDOUT */
	   
       String[] inp = {"a b","a c","b d","c d"};
       //InputStreamReader in= new InputStreamReader(System.in);
       //BufferedReader input = new BufferedReader(in); 
       
       
       DirectedGraphNode temp;
       DirectedGraphNode root = null;
//       HashMap<DirectedGraphNode,boolean> map = new HashMap<DirectedGraphNode,boolean>();
       HashMap<DirectedGraphNode,Boolean> map = new HashMap<DirectedGraphNode, Boolean>();
       String str;
       for(String s:inp){
      // while ((str = input.readLine()) != null) {
           String[] arr = s.split(" ");
           DirectedGraphNode temp1 =new DirectedGraphNode(arr[0]);
           DirectedGraphNode temp2 =new DirectedGraphNode(arr[1]);
           if(!map.containsKey(temp1)){
               temp1.neighbors.add(temp2);
               map.put(temp1,true);
           }
           else{
               temp1.neighbors.add(temp2);
           }
           
           map.put(temp2,false);
       }
       
       boolean hasRoot = false;
       for (Entry<DirectedGraphNode, Boolean> entry : map.entrySet()) {  
           if(entry.getValue()){
               root=entry.getKey();
               hasRoot=true;
               break;
           }
       }
       if(!hasRoot) {System.out.println("false");return;}
       boolean res = isTree(root,map.size());
       System.out.println(res);
   }

   public static boolean isTree(DirectedGraphNode node, int graphSize){
	   if(node == null) return true;
	   LinkedList<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
	   //HashMap<DirectedGraphNode,Boolean> visited = new HashMap<DirectedGraphNode, Boolean>();
	   HashSet<DirectedGraphNode> visited = new HashSet<DirectedGraphNode>();
	   queue.add(node);
	   visited.add(node);
	   while(!queue.isEmpty()){
		   DirectedGraphNode cur = queue.poll();
		   for(DirectedGraphNode n:cur.neighbors){
			   if(visited.contains(n)) return false;
			   else{
				   queue.add(n);
				   visited.add(n);
			   }
		   }
	   }
	   return visited.size() == graphSize;
   }
   
   /*
    *    Do a BFS. If you encounter an already visited vertex, it's not a tree.

   If you're done and there are vertices left, it's not a tree - the graph is not connected.

   To check for a binary tree, additionally just check if each vertex has at most 2 outgoing edges.
    */
}
   
   
   

