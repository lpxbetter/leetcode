import java.util.ArrayList;

public class generateParenthesis {
	
	public static void main(String[] args){
		generate(3);
	}
	
	public static ArrayList<String> generate(int n) { 
		ArrayList<String> res= new ArrayList<String>();
		helper("",0,0,n,res);
		return res;
	}
	public static void helper(String item, int open, int close,int n,ArrayList<String> res){
		if(item.length() == 2*n){
			res.add(item);
			System.out.println(item);
			return;
		}
		if(open < n){
			helper(item+"(", open+1, close, n, res);
		}
		if(close<open){
			helper(item+")",open, close+1,n,res);
		}
	}
}