public class LNode<T>{
	public T item;
	public LNode<T> after;
	public LNode<T> before;
	public LNode(T item){
		this.item = item;
		this.after = null;
		this.before = null;
	}
	public LNode(T item, LNode<T> next, LNode<T> previus){
		this.item = item;
		this.after = next;
		this.before = previus;
	}
	public String toString(){
		return "" + (before == null? "_NULL_": before.item) 	+ " <- " + item + "  -> " + 	(after == null ? "_NULL_" : after.item);
	}

}

