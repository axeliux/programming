public class LRUCache<K,V>{
	private MyLinkedHashMap<K,V> cache;
	private final int cache_size;
	public LRUCache(int size){
		cache_size = size;
		this.cache = new MyLinkedHashMap<K,V>(){
			protected boolean removeEldestEntry(MyLinkedHashMap.Entry<K,V> eldest){
				//System.out.println("remove Eldest entry: " + (cache_size >= size()));
				return cache_size < size();
			}
		};
	}
	
	public V get(K key){
		return cache.get(key);
	}
	public void put(K key, V value){
		cache.put(key,value);
	}
	public boolean  containsKey(K key){
		return cache.containsKey(key);
	}
	public void print(){
		cache.print();
	}
	public void printTable(){
		this.cache.printTable();
	}
	
	public static void main(String arg[]){
		LRUCache<String,String> cache = new LRUCache<String,String>(5);
		cache.print();
		cache.put("uno","one");
		cache.put("dos","two");
		cache.print();
		cache.put("tres","three");
		cache.put("cuatro","four");
		cache.put("cinco","five");		
		cache.put("seis","six");
		cache.print();
		System.out.println(cache.get("cuatro"));
		cache.print();
		System.out.println(cache.get("uno"));
		cache.print();
		cache.put("uno","one");
		/*cache.put("siete","seven");
		cache.put("ocho","eight");
		cache.put("nueve","nine");
		cache.put("diez","ten");
		cache.put("once","eleven");
		cache.put("doce","twelve");
		cache.put("trece","thirteen");
		cache.put("catroce","fortheen");
		cache.put("quince","fivetheen");
		cache.put("dieciseis","sixteen");
		cache.put("axeliux","Axeliux");*/
		
		cache.print();
		//System.out.println("Parent");
		//cache.printTable();
	}	
}
