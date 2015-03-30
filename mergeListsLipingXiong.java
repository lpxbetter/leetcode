

import java.io.*;
import java.util.*;
//import commLib.*;

public class mergeListsLipingXiong {
	 public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	 
	public static ListNode mergeKLists(ArrayList<ListNode> lists) {
		if(lists==null || lists.size()==0) return null;
		return merge(lists,0,lists.size()-1);
		}
	 
	private static ListNode merge(ArrayList<ListNode> lists, int left, int right){
		    if(left < right)
		    {
		        int mid = (left + right)/2;
		        return mergeTwoLists(merge(lists,left,mid), merge(lists,mid+1,right));
		    }
		    return lists.get(left);
		}
		
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	    ListNode first = new ListNode(0); //first node is added to help,is not a node in the list
	    ListNode pre = first;
	    first.next = l1;
	    while(l1!=null && l2 != null)
	    {
	        if(l1.val>l2.val)
	        {
	            ListNode next = l2.next;
	            l2.next = pre.next;
	            pre.next = l2;
	            l2 = next;
	        }
	        else // just go to next node
	        {
	            l1 = l1.next;
	        }
	        pre = pre.next;

	    }
	    if(l2!=null) //when l1 is null, there may be some nodes left in l2
	    {
	        pre.next = l2;
	    }
	    return first.next;
	}	
	
    private static ListNode createList(int[] arr){
		ListNode head = new ListNode(arr[0]);
		ListNode pre = head;
		for(int i=1;i<arr.length;i++){
			ListNode node = new ListNode(arr[i]);
			pre.next = node;
			pre = pre.next;
		}   
		return head;
    }
    private static void ptrList(ListNode head){
    	ListNode cur = head;
    	while(cur != null){
    		System.out.print(cur.val);
    		cur = cur.next;
    	}
    	System.out.println();
    }
	 
	public static void main(String[] args){
		ListNode l1 = createList(new int[] {1,3,5});
		ListNode l2 = createList(new int[] {2,4,6});
		
		ptrList(l1);
		ptrList(l2);
		ListNode resOfmerge2List = mergeTwoLists(l1,l2);
		ptrList(resOfmerge2List); 
		
		ListNode L1 = createList(new int[] {1,4,7});
		ListNode L2 = createList(new int[] {2,5,8});		
		ListNode L3 = createList(new int[] {3,6,9});
		ArrayList<ListNode> lists = new ArrayList<ListNode>();
		
		lists.add(L1);
		lists.add(L2);
		lists.add(L3);
		ListNode resOfmergeKList = mergeKLists(lists);
		ptrList(resOfmergeKList);
		
	}
}
