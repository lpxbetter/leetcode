import java.util.LinkedList;


public class myqueue{
	LinkedList<Integer> instack = new LinkedList<>();
	LinkedList<Integer> outstack = new LinkedList<>();
	
	public myqueue(){
		
	}
	
	void enqueue(int x){
		instack.push(x);
	}
	int dequeue(){
		if(outstack.isEmpty()){
			while(!instack.isEmpty()) outstack.push(instack.pop());
		}
		return outstack.pop();
	}
	
	Stack<> sortstack(Stack<> s1){
		s2;
		int x=s1.pop();
		if(s2.isEmpty()) s2.push(x);
		while(true) {
			s1.push(s2.pop());
			if(s1.peek()<x) break;
		}
	}
}