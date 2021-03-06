public class MyStack{
	private Item V[];
	private int top;
	private int capacity;
	
	public MyStack(int c){
		this.capacity = c;
		this.V = new Item[c];
		this.top=0;
	}
	
	public boolean isEmpty(){
		return this.top == 0;
	}
	public boolean isFull(){
		return top==capacity;
	}
	public int peek(){
		if(isEmpty()){
			return -999;
		}else{
			return V[top-1].value;
		}
	}
	public int pop(){
		if(!isEmpty()){
			return V[--top].value;
		}else{
			return -999;
		}
	}
	public void push(int i){
		if(!isFull()){
			if(!isEmpty()){
				int min  = i < V[top-1].min ? i: V[top-1].min;
				V[top++] = new Item(i, min);
			}else{
				V[top++] = new Item(i,i);
			}
		}
	}
	public int min(){
		if(!isEmpty()){
			return V[top-1].min;
		}else{
			return -999;
		}
	}
	
	public void print(){
		for(int i = 0; i < top; i++){
			System.out.print(V[i].value + " > ");
		}		
	System.out.println();
	}
	static class Item{
		public int value;
		public int min;
		public Item(int i, int b){
			this.value = i;
			this.min = b;
		}
	}
	
	public static void main(String [] args){
		MyStack stack = new MyStack(10);
		stack.push(10);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(-1);
		stack.print();
		System.out.println("MIN: " + stack.min());
		stack.pop();
		stack.pop();
		stack.print();
		System.out.println("MIN: " + stack.min());
		
		
	}
}
