import java.util.*;
public class LinkedList
{

	public static void main(String [] args){
		System.out.println("Linked list");
		LNode head = null;
		head = append(head,1);
		append(head,2);
		append(head,3);
		append(head,4);
		append(head,4);
		append(head,3);
		append(head,2);
		append(head,1);
		//append(head,1);
	        print(head);
        	System.out.println("Is Palindrome: " +  isPalindrome(head));
	        print(head);
		
		
	}
	public static boolean  isPalindrome(LNode node){
		int size = size(node);
		int middle = size/2;
		
		if((size % 2) != 0) middle++;
		
		LNode current = node;
		LNode runner = node;
		int counter = 0;
		while(counter < middle-1){
			runner = runner.next;
			counter++;
		}
		runner.next = reverse(runner.next);
		runner = runner.next;
		while(runner != null){
			if(runner.value != current.value){
				return false;
			}
			runner = runner.next;
			current = current.next;	
		}
		
		return true;
		
	}

	public static LNode reverse(LNode node){
		LNode current = node;
		LNode next = null;
		LNode prev = null;
		
		while(current != null){
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		return prev;
	}
	public static LNode add1(LNode l1, LNode l2){
		LNode result = null;
		int size1 = size(l1);
		int size2 = size(l2);
		int currentDigit = Math.max(size1,size2);
		boolean carrier = false;
		while(l1 != null && l2 != null)
		{	
			int sum = 0;
			if(currentDigit <= size1){
				sum += l1.value;
				l1 = l1.next;
			}
			if(currentDigit <= size2){
				sum += l2.value;
				l2 = l2.next;
			}
			
			currentDigit--;
			
			if(carrier){
				sum += 1;
			}
			if(sum >= 10){
				carrier =true;
			}else{
				carrier = false;
			}
			result = append(result,sum%10);
		}
		if(carrier)
		{
			result = append(result,1);
		}
		return result;
			
	}
	public static void removeMe2(LNode node)
	{
		node.value = node.next.value;
		node.next = node.next.next;
			
	}
	public static int size(LNode head){
		if( head == null) return 0;
		
		LNode tmp = head;
		int counter = 0;
		while(tmp != null){
			counter++;
			tmp = tmp.next;
		}
		return counter;
	}
	//Implement an algorithm to find the nth to last element of a singly linked list.
	// complexity  O (2n) since size(head) requires O ( n )
	public static LNode kth(LNode head, int k){
		if(size(head) < k)
		{
			return null;
		}else{
			LNode runner = head;
			LNode target = head;
			while(k > 0 ){
				runner = runner.next;
				k--;
			}
			while(runner != null){
				runner = runner.next;
				target = target.next;
			}
			return target;
			
		}
	}
	// Implement an algorithm to delete a node in the middle of a single linked list, given
        //only access to that node.
        public static void deleteMe(LNode head,int me){
		if(head.value ==  me){
			head = head.next;
		}else{
			LNode tmp= head;
		
			while(tmp.value != me){
			        
				tmp = tmp.next;
			}
			// at this point we have a reference to the node that we want  to delete
			
			while(tmp.next.next != null)
			{
				tmp.value = tmp.next.value;
				tmp = tmp.next;
			}
			tmp.next = null;
        	}
        }
        public static void deleteMe2(LNode me){
        	if(me.next == null) return; // If it is the last element of the list, this cannot be deleted
        	if(me.next.next == null){
        		me.value = me.next.value;
        		me.next = null;
        	}else{
			 while(me.next.next != null){
			 	me.value = me.next.value;
			 	me = me.next;
			 }
			 me.next = null;
		}
        }
	// remove duplicates
	// complexity O ( n )
	public static void removeDuplicates(LNode head){
		Hashtable<Integer,LNode> table = new Hashtable<Integer,LNode>();
		if(head == null) return;
		if(head.next == null) return;
		LNode current = head;
		LNode next = current.next;
		table.put(current.value, current); 
		while(next != null){
			if(table.containsKey(next.value)){
				next = next.next;
				current.next = next;
							
			}else{
				table.put(next.value,next);
				current = next;
				next = next.next;
			}
			
		}
	}
// remove duplicates with out an extra buffer.  
// complexity O (n^2) 
	public static void removeDuplicates2(LNode head){
		if(head == null) return;
		if(head.next == null) return;
		
		LNode current = head;
		
		
		while(current != null){
			int currentValue = current.value;
			LNode previus = current;
			LNode next = current.next;
			while(next != null){
				if(next.value == currentValue){
					
					next = next.next;
					previus.next = next;
				}else{
					previus = next;
					next = next.next;
				}
				    
			}
			current = current.next;
		}
	}
	public static void print(LNode head){
		LNode tmp = head;
		while(tmp != null){
			System.out.print(tmp.value + "->");		
			tmp = tmp.next;
		}	
		System.out.println();
	}
	// append to the end of the list
	public static LNode append(LNode head,int value){
		if(head == null){
			head = new LNode(value);	
			
		}else{
			LNode tmp = head;	
			while(tmp.next != null)
				tmp = tmp.next;
			tmp.next = new LNode(value);
			
		}		

		return head;
	}
	// append to the head of the list
	public static LNode addToHead(LNode head, int value){
		if(head == null)
		{
			head =new LNode(value);
			
		}else{
			LNode tmp = head;
			head = new LNode(value);
			head.next = tmp;		
		}
		return head;
	}


	
	static class LNode{
		public LNode next;
		public int value;
		public LNode(int value){
			this.value = value;		
		}	
		
		public boolean equals(Object obj){
			return (obj instanceof LNode  && ((LNode)obj).value == this.value);
		}
	}
}
