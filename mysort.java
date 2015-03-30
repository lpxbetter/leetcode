import java.util.Arrays;
import java.util.Comparator;



public class mysort {
	public class ListNode{
		int val;
		ListNode next;
		ListNode(int x){
			val = x;
			next = null;
		}
	}
	public static void main(String[] args){
		String[] res= new String[2];
		res[0]="abc";
		if(res[0].equals("abc")) System.out.println("true"); 
		else System.out.println("false");
		
	}
	
    public String largestNumber(int[] num) {
        int n = num.length;
        String[] tmp = new String[num.length];
        StringBuilder res=new StringBuilder();
        
        for(int i=0;i<n;i++){
            tmp[i]=String.valueOf(num[i]);
        }
        Comparator comp = new Comparator<String>(){
            @Override
            public int compare(String s1,String s2){
                return (s1+s2).compareTo(s2+s1);
            }
        };
        Arrays.sort(tmp,comp);
        for(int i=tmp.length;i>=0;i--){
            res.append(tmp[i]);
        }
        return tmp.toString();
    }
	
	
	
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }
    public static ListNode mergeSort(ListNode head){
        if(head.next == null) return head;
        ListNode w=null;
        ListNode r=null;
        while(r!=null && r.next!=null){
            r=r.next.next;
            w=w.next;
        }
        ListNode l1=mergeSort(head);
        ListNode l2=mergeSort(w);
        return merge(l1,l2);
    }
   
       public static ListNode merge(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode h = new ListNode(1);
        h.next = l1;
        ListNode pre = h;
        while(l1 != null && l2 != null){
        if(l2.val < l1.val){
            ListNode nextNode = l2.next;
            pre.next = l2;
            l2.next = l1;
            pre = l2;
            l2 = nextNode;
        }
        else{
            pre = l1;
            l1=l1.next;
        }
        
        }
        if(l2!=null){
            pre.next = l2;
        }
        return h.next;
    }
       
}