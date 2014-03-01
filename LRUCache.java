public class LRUCache<K,V>{
  private MyLinkedList<Entry<K,V>> cache_list;
  private Items<LNode<Entry<K,V>>> table[];
  private int cache_size ;
  static final int DEFAULT_INITIAL_CAPACITY = 16;
  static final float DEFAULT_LOAD_FACTOR = 0.75f;

  

  public LRUCache(int size){
  	this.cache_size = size;
  	this.table =new Items[DEFAULT_INITIAL_CAPACITY];
  	this.cache_list = new MyLinkedList<Entry<K,V>>();

  }
  public void remove(K key){

  }
  public boolean containsKey(K key){

  }
  public V get(K key){

  }
  public void put(K key, V value){
     int hashcode = key.hashCode();
     int index = indexFor(hashcode,table.length-1);
     if(table[index] == null){
     	Entry<K,V> entry = new Entry(key,value);
        LNode<Entry<K,V>> node = cache_list.add(entry);
        table[index] = new Items<LNode<Entry<K,V>>>();
        table[index].add(node);
     }else{
     	Items<LNode<Entry<K,K>>> list = table[index];
     	LNode<Entry<K,V>> tmp = list.getFisrt();
     	while(tmp!= null){
     		 Entry<K,V> entry = tmp.item;
     		 if(key.equals(entry.key)){
     		 	entry.value = value;
     		 	cache_list.add(cache_list.remove(tmp));
     		 }
     		 tmp = tmp.after;
     	}
     }

  }
  static int indexFor(int hash, int length){
  	return hash%length;
  }
  class Entry<K,V>{
  	public K key;
  	public V value;
  	public Entry(K key, V value){
  		this.key =key;
  		this.value = value;
  	}
  }
   class Items<T>{
	public  MyLinkedList<T> list;
	public Items(){
		this.list = new MyLinkedList<T>();
	}
	public void add(T entry){
		list.add(entry);
	}
	public LNode<T> getFirst(){
		return list.getFirst();
	}
   }

  public static void main(String args[]){

  }
}

