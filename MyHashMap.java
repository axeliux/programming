public class MyHashMap<K,V>{
	private Entry table[];
	private static final int DEFAULT_INIT_CAPACITY = 16;
	private static final float LOAD_FACTOR = 0.75f;
	public static int threshold;
	public MyHashMap(){
		threshold = (int) (DEFAULT_INIT_CAPACITY * LOAD_FACTOR);
		table = new Entry[DEFAULT_INIT_CAPACITY];
	}
	public int indexFor(int hashcode, int length){
		int index = hashcode&length;
		//System.out.println("hashcode=" + hashcode + " length: " + length +  " Index> " + index);
		return index;
	}
	public V get(K key){
		int index = indexFor(key.hashCode(),table.length-1);
		Entry<K,V> entry = table[index];
		while(entry != null){
			if(entry.key.equals(key)){
				return entry.value;
			}
			entry = entry.after;
		}
		return null;
	}
	public void put(K key, V value){
		int index = indexFor(key.hashCode(),table.length-1);
		Entry<K,V> entry = table[index];
		if(entry == null){
			table[index] = new Entry<K,V>(key,value);
		}else if(containsKey(key)){
			System.out.println("update");
			//update
			while(entry != null){
				if(entry.key.equals(key)){
					entry.value = value;
					break;
				}
				entry = entry.after;
			}
		}else{
			//insert
			System.out.println("inserting..." + key);
			while(entry.after != null){
				entry = entry.after;
			}
			entry.after = new Entry<K,V>(key,value);
		
		}
			
		
	}
	public boolean containsKey(K key){
		int index = indexFor(key.hashCode(),table.length-1);
		Entry<K,V> entry = table[index];
		if(entry == null){
			return false;
		}else{
			while(entry != null){
				if(entry.key.equals(key)){
					return true;
				}
				entry = entry.after;
			}
		}
		
		return false;
	}
	
	public void printTable(){
	System.out.println();
		for(int i = 0; i < table.length; i++){
			Entry<K,V> entry = table[i];
			if(entry != null){
				System.out.print("["+i+"]");
				while(entry != null){
					System.out.print(entry + " ");
					entry = entry.after;
				}
				System.out.println();		
			}
		}
	}
	
	class Entry<K,V>{
		public K key;
		public V value;
		public Entry<K,V> after;
		public Entry(K k, V v){
			key = k;
			value = v;
			after = null;
		}
		public String toString(){
			return "[key="+key+",Value="+value+", hashcode="+key.hashCode()+"]";
		}
	}
	public static void main(String args[]){
		MyHashMap<String,String> hashmap= new MyHashMap<String,String>();
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
		hashmap.printTable();
		
		//System.out.println(hashmap.get("seis"));
	}
}
