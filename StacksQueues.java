import java.util.*; 

public class StacksQueues{

	class Node{
		int val;
		Node next;
		Node(int x){
			val = x;
			next = null;
		}
	}
	class Stack{
		Node top;
		Stack(){
			top = null;
		}
		int pop(){
			Node tempnode = top.next;
			int val = top.val;
			top = tempnode;
			return val;
		}
		
		void push(int x){
			Node newnode = new Node(x);
			newnode.next = top;
			top = newnode;
		}
		boolean isEmpty(){
			return top == null;
		}
		
	}
	class Queue{
		Node head;
		Node tail;
		Queue(){
			
		}
		int pop(){
			int item=head.val;
			head = head.next ;
			return item;
		}
		void push(int item){
			if(head ==  null){
				head = new Node(item);
				tail = head;
			}
			else{
			Node newnode = new Node(item);
			tail.next = newnode;
			tail = tail.next;
			}
		}
	}
	
	//min stack
	class minStack extends Stack{
		Stack ms ;
		minStack(){
			ms = new Stack();
		}
		int min(){
			if(ms.isEmpty()) return Integer.MAX_VALUE; 
			else return ms.peek();
		}
		int pop(){
			int item = super.pop();
			if(item == min()) ms.pop();
			return item;
		}
		void push(int x){
			if(x<min()) ms.push(x);;
			super.push(x);
		}
	}
	
	
	
	
	
	public static void main(String args[]){
		
	}
	
}