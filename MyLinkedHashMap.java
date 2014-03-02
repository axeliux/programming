/**
 * LinkedHashMap
 * This implementation spares its clients from the unspecified, 
 * generally chaotic ordering provided by HashMap (and Hashtable),
 * without incurring the increased cost associated with TreeMap. 
 **/
public class MyLinkedHashMap<K,V> extends MyHashMap<K,V>{
	public Entry<K,V> head;
	
	public MyLinkedHashMap(){
		super();
		head = new Entry<>(null,null,null);
		head.before = head;
		head.after = head;
	}
	
	public V get(K key){
		Entry<K,V> entry  = (Entry<K,V>)getEntry(key);
		if(entry == null) return null;
		
		entry.registerAccess(this);
		return entry.value;
	}
	protected void createEntry(K key, V value, int index){
		MyHashMap.Entry<K,V> old = table[index];
		Entry<K,V> entry = new Entry<K,V>(key,value,old);
		table[index] =entry;
		entry.insertBefore(head);
		size++;
		
		if(removeEldestEntry(head.after)){
			removeKey(head.after.key);
		}
	}
	protected void transfer(MyHashMap.Entry newTable[]){
		Entry<K,V> entry = head.after;
		while(entry != head){
			Entry<K,V> current = entry;
			int index = indexFor(current.key.hashCode(),newTable.length);
			current.next = newTable[index];
			newTable[index] = current;
			
			entry = entry.after;
		}
	}
	protected boolean removeEldestEntry(Entry<K,V> eldest){
		//System.out.println("removeEldestEntry from MyLinkedHashMap");
		return false;
	}
	public void print(){
		System.out.println("SIZE> " + size());
		Entry<K,V> entry = head.after;
		while(entry != head ){
			System.out.print(entry.value + " -> " );
			entry = entry.after;
		}
		System.out.println();
	}
	
	public void printTable(){
		System.out.println("Printing HashMap");
		super.printTable();
		System.out.println("Printing Linked Hash Map");
		print();
	}
    static class Entry<K,V> extends MyHashMap.Entry<K,V>{
    	Entry<K,V> before;
    	Entry<K,V> after;
    	public Entry(K key, V value, MyHashMap.Entry<K,V> next){
    		super(key,value,next);
    	}
    	
    	public void remove(){
    		before.after = after;
    		after.before = before;
    	}
    	public void insertBefore(Entry<K,V> existingEntry){
    		after  = existingEntry;
    		before = existingEntry.before;
    		
    		after.before = this;
    		before.after = this;
    	}
    	protected void registerAccess(MyHashMap<K,V> hashmap){
    		MyLinkedHashMap map = (MyLinkedHashMap)hashmap;
    		remove();
    		insertBefore(map.head);
    	}
    	protected void removeme(MyHashMap<K,V> hashmap){
    		remove();
    	}
    }
    
    public static void main(String arg[]){
    	MyLinkedHashMap<String,String> hashmap = new MyLinkedHashMap<String,String>();
    		hashmap.put("uno","one");
		hashmap.put("dos","two");
		hashmap.put("tres","three");
		hashmap.put("cuatro","four");
		hashmap.put("cinco","five");		
		hashmap.put("seis","six");
		hashmap.put("siete","seven");
		hashmap.put("ocho","eight");
		hashmap.put("nueve","nine");
		hashmap.put("diez","ten");
		hashmap.put("once","eleven");
		hashmap.put("doce","twelve");
		hashmap.put("trece","thirteen");
		hashmap.put("catroce","fortheen");
		hashmap.put("quince","fivetheen");
		hashmap.put("dieciseis","sixteen");
		hashmap.put("axeliux","Axeliux");
	hashmap.printTable();
	System.out.println(hashmap.get("cuatro"));
	System.out.println("Getiing...." + hashmap.get("doce"));
	hashmap.print();
	hashmap.removeKey("one");
	hashmap.removeKey("cinco");
	hashmap.removeKey("diez");
	hashmap.removeKey("hola");
	hashmap.removeKey("axeliux");
	hashmap.printTable();
	System.out.println(hashmap.get("one"));
	System.out.println(hashmap.get("cinco"));
	System.out.println(hashmap.get("diez"));
	System.out.println(hashmap.get("hola"));
	System.out.println(hashmap.get("axeliux"));
    }
}
