public class MyHashMap<K,V>{
	private Entry table[];
	private static final int DEFAULT_INIT_CAPACITY = 16;
	private static final float LOAD_FACTOR = 0.75f;
	public static int threshold;
	public static int size;
	public MyHashMap(){
		threshold = (int) (DEFAULT_INIT_CAPACITY * LOAD_FACTOR);
		table = new Entry[DEFAULT_INIT_CAPACITY];
		size = 0;
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
			size++;
		}else if(containsKey(key)){
			
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
			
			while(entry.after != null){
				entry = entry.after;
			}
			entry.after = new Entry<K,V>(key,value);
			size++;
		
		}
		
		if(size >= threshold){
			resize(table.length*2);
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
	private void resize(int newCapacity){
		System.out.println("Resizing to : " + newCapacity);
		Entry newTable[] = new Entry[newCapacity];
		transfer(newTable);
		table = newTable;
		threshold = (int)(newCapacity*LOAD_FACTOR);
	} 
	private void transfer(Entry newTable[]){
		for(int i = 0; i < table.length; i++){
			if(table[i] != null){
				Entry<K,V> current = table[i];
				while(current != null){
					Entry<K,V> entry = current;
					current = current.after;
					
					entry.after = null; // sanity
					int index = indexFor(entry.key.hashCode(),newTable.length-1);
					if(newTable[index] == null){
						newTable[index] = entry;
					}else{
						Entry<K,V> e = newTable[index];
						while(e.after != null){
							e = e.after;
						}
						e.after = entry;
					}
					
				}
			}
		}
	}
	public void removeKey(K key){
		if(containsKey(key)){
			
			int index = indexFor(key.hashCode(),table.length-1);
			Entry<K,V> entry = table[index];
			if(entry.key.equals(key)){
				
				table[index] = entry.after;
			}else{
				while(entry.after.key.equals(key) == false){
					entry = entry.after;
				}
				entry.after = entry.after.after;
			}
			size--;
		}
	}
	public void printTable(){
	System.out.println("HashMap size = " + size);
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
			return "[key = "+key+", Value = "+value+", hashcode="+key.hashCode()+"]";
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
		hashmap.put("axeliux","Axeliux");
		hashmap.printTable();
		hashmap.put("17","17");
		hashmap.put("18","18");
		hashmap.put("19","19");
		hashmap.put("20","20");
		hashmap.put("21","21");		
		hashmap.put("22","22");
		hashmap.put("23","23");
		hashmap.put("24","24");
		hashmap.put("25","25");
		hashmap.put("26","26");
		hashmap.put("27","27");
		hashmap.put("28","28");
		hashmap.put("29","29");
		hashmap.put("30","30");
		hashmap.put("31","31");
		hashmap.put("32","32");
		hashmap.put("axeliux","Axel David Velazquez Huerta");
		hashmap.printTable();
		hashmap.removeKey("30");
		hashmap.removeKey("dieciseis");
		hashmap.removeKey("siete");
		hashmap.removeKey("23");
		hashmap.removeKey("doce");
		hashmap.printTable();
		System.out.println(hashmap.get("30"));
		System.out.println(hashmap.get("dieciseis"));
		System.out.println(hashmap.get("siete"));
		System.out.println(hashmap.get("23"));
		System.out.println(hashmap.get("doce"));
	}
}
