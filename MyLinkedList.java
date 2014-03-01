public class MyLinkedList<T>{
	LNode<T> head;
	LNode<T> tail;
    int size;
	public MyLinkedList(){
		this.head = null;
		this.tail = null;
        size  = 0;
	}
	public MyLinkedList(T item){
		LNode<T> node = new LNode<T>(item);
	    this.head = node;
	    this.tail = node;
        size++;
	}

	public LNode<T> add(T item){
		LNode<T> node = new LNode<T>(item);
		if(isEmpty()){
			head = node;
			tail = node;
		}else{
			
			node.after = head;
			node.before = null;

            head.before = node;
			head = node;
		}
        size++;
		return node;

	}
    public LNode<T> add(LNode<T> node){
        if(node == null) return null;;

        node.after = null;
        node.before= null;
        if(isEmpty()){
            head = node;
            tail = node;
        }else{
            node.after = head;
            node.before = null;
            head.before = node;
            head = node;
        }
        size++;
        return node;
    }

	public LNode<T> getLast(){
		return tail;
	}
	public LNode<T> getFisrt(){
		return head;
	}
    public LNode<T> removeLast(){
    	if(!isEmpty()){
            LNode<T> tmp = tail;
    		if(head == tail){
    			head = null;
    			tail = null;
    		}else{
    			
    			tail = tail.before;
    			tail.after = null;
    			//tmp = null;
    		}
            size--;
            return tmp;
    	}
        return null;
    }
    public LNode<T> removeFist(){
    	if(!isEmpty()){
            LNode<T> tmp = head;
    		if(head == tail ){

    			head = null;
    			tail = null;

    		}else{
    			
    			head = head.after;
    			head.before = null;
    			
    		}
            size--;
            return tmp;
    	}
        return null;
    }
    public LNode<T> remove(LNode<T> node){
        if(isEmpty())return null;
    	if(node == null)return null;

        if(node == head){
        	return removeFist();
        }else if(node == tail){
        	return removeLast();
        }else{
        	node.after.before = node.before;
    		node.before.after = node.after;
        }
        size--;
        return node;
    }
    public boolean isEmpty(){
    	return head == null && tail == null;
    }

    public void printHeadToTail(){

    	LNode<T> tmp = head;
    	while(tmp != null){
    		System.out.print(" {"+ tmp.item +"} ");
    		tmp = tmp.after;
    	}
        System.out.println();
    }
    public void printTailToHead(){
        LNode<T> tmp = tail;
        while(tmp!=null){
            System.out.print(" {" + tmp.item + "} ");
            tmp = tmp.before;
        }
        System.out.println();
    }
    public void print(){
        if(isEmpty()){
            System.out.println("__Empty List__");
        }else{
            System.out.println("SIZE: " + size);
            printHeadToTail();
          //  printTailToHead();
        }
    }
    public LNode<T> getObject(T item){
        if(isEmpty()){
            return null;
        }else{
            LNode<T> temp = head;
            while(temp != null){
                if(temp.item.equals(item)){
                    return temp;
                }
                temp = temp.after;
            }
            return null;
        }
    }

	public static void main(String args[]){
		MyLinkedList<String> list = new MyLinkedList<String>();
		LNode<String> l1 = list.add("HUERTA");
		LNode<String> l2 = list.add("VELAZQUEZ");
		LNode<String> l3 = list.add("DAVID");
		LNode<String> l4 = list.add("AXEL");
		list.print();
		list.add(list.remove(l2));
        list.print();
        list.add(list.removeLast());
        list.print();
        list.removeFist();
        list.print();
	}
}


