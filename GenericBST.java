public class GenericBST<K>{
    public Entry<K> root;
	
	public void add(K key){
		if(root == null){
			root = put(key,root,null);
		}else{
			put(key,root,root);
		}
	}
	 /**
     * Returns the least key greater than or equal to the given key,
     * or {@code null} if there is no such key.
     *
     * @param key the key
     * @return the least key greater than or equal to {@code key},
     *         or {@code null} if there is no such key
     * @throws ClassCastException if the specified key cannot be compared
     *         with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *         and this map does not permit null keys
     */
	private Entry<K> ceiling(K key){
	   Entry<K> p = this.root;
	   while(p !=  null){
			@SuppressWarnings("unchecked")
			Comparable<? super K> k = (Comparable<? super K>)key;
			int cmp = k.compareTo(p.key);
			if(cmp < 0) { // On the left
				if(p.left!= null){
					p = p.left;
				}else{
					return p;
				}
			}else if (cmp > 0){// on the right
				if(p.right != null){
					p = p.right;
				}else{
					Entry<K> parent = p.parent;
					Entry<K> ch = p;
					while(parent != null && parent.right == ch){
						ch = parent;
						parent = parent.parent;
					}
					return parent;
				}
			}else{ // Equal key
				return p;
			}
	   }
	   return p;
	}
	public Entry<K> ceiling_recursive(K key){
		return ceiling_2(root,key);
	}
	private Entry<K> ceiling_2(Entry<K> entry,K key){
		if(entry == null) return null;
		
		@SuppressWarnings("unchecked")
		Comparable<? super K> k = (Comparable<? super K>)key;
		int cmp = k.compareTo(entry.key);
		if(cmp == 0){
			return entry;
		}else if(cmp > 0){
			return ceiling_2(entry.right,key);
		}else{
			Entry<K> tmp = ceiling_2(entry.left,key);
			if(tmp == null){
				return entry;
			}else{
				return tmp;
			}
		}
		
	} 
	
	 /**
     * Gets the entry corresponding to the specified key; if no such entry
     * exists, returns the entry for the greatest key less than the specified
     * key; if no such entry exists, returns {@code null}.
     */
	public Entry<K> floor_recursive(K key){
		return floor_2(root,key);
	}
	private Entry<K> floor_2(Entry<K> node, K key){
		if(node == null) return null;
		@SuppressWarnings("unchecked")
		Comparable<? super K> k = (Comparable<? super K>)key;
		
		int cmp = k.compareTo(node.key);
		if(cmp == 0){
			return node;
		}else if( cmp < 0){
			return floor_2(node.left,key);
		}else{
			Entry<K> tmp = floor_2(node.right,key);
			if(tmp != null){
				return tmp;
			}else{
				return node;
			}
		}
	}
	
	private Entry<K> floor(K key){
		Entry<K> p = this.root;
		
		while(p != null){
			@SuppressWarnings("unchecked")
			Comparable<? super K> k = (Comparable<? super K>)key;
			int cmp = k.compareTo(p.key);
			if(cmp < 0){
				if(p.left != null){
					p = p.left;
				}else{
					Entry<K> parent = p.parent;
					Entry<K> ch = p;
					while(parent != null && parent.left == ch){
						ch = parent;
						parent = parent.parent;
					}
					return parent;
				}
			}else if(cmp > 0){
				if(p.right != null){
					p = p.right;
				}else{
					return p;
				}
			}else{
				return p;
			}
			
		}
		return null;
	}
	private Entry<K> put(K key, Entry<K> node, Entry<K> parent){
		if(node == null){
			return new Entry<K>(key,parent,1);
		}else{
			@SuppressWarnings("unchecked")
		    Comparable<? super K> k = (Comparable<? super K>)key;
			
			int cmp = k.compareTo(node.key);
			if(cmp < 0){
			   
				node.left = put(key,node.left,node);
			}else{
				
				node.right = put(key,node.right,node);
			}
		}
			node.size = 1 + size(node.left) + size(node.right);
			return node;
	}
	public int size(Entry<K> node){
		if(node == null){
			return 0;
		}else{
			return node.size;
		}
	}
	public K getMinimiun(){
		if(this.root == null) return null;
		if(this.root.left == null) return this.root.key;
		else{
			Entry<K> tmp = this.root;
			while(tmp.left != null){
				tmp = tmp.left;
			}
			return tmp.key;
		}
	}
	public K getMaximiun(){
		if(root == null) return null;
		if(root.right == null) return root.key;
		else{
			Entry<K> tmp = root;
			while(tmp.right != null){
				tmp = tmp.right;
			}
			return tmp.key;
			
		}
	}
	public void inorder(){
		inorder_aux(this.root);
	}
	private void inorder_aux(Entry<K> node){
		if(node == null) return;
		
		inorder_aux(node.left);
		System.out.print(node);
		inorder_aux(node.right);
		
	}
	public int rank(K key){
		return rank_aux(root,key);
	}
	private int rank_aux(Entry<K> node, K key){
		if(node == null) return 0;
		@SuppressWarnings("unchecked")
		Comparable<? super K> k = (Comparable<? super K>)key;
		int cmp = k.compareTo(node.key);
		if(cmp == 0){
			return size(node.left);
		}else if(cmp < 0){
			return rank_aux(node.left,key);
		}else{
			return 1 + size(node.left) + rank_aux(node.right,key);
		}
	}
	
	
	
	static class Entry<K>{
		public K key;
		public int size; // number of elements less than the current
		public Entry<K> left;
		public Entry<K> right;
		public Entry<K> parent;
		public Entry(K k,Entry<K> p, int size){
			this.key =k;
			this.parent = p;
			this.size = size;
		}
		public String toString(){
			return " " + key.toString() + " ("+size+") ";
		}
		
	}
	
	public static void main(String [] args){
		GenericBST<Integer> bst = new GenericBST<Integer>();
		bst.add(500);
		
		bst.add(250);
		bst.add(750);
		
		bst.add(100);
		bst.add(350);
		bst.add(600);
		bst.add(800);
		
		bst.add(50);
		bst.add(150);
		bst.add(755);
		bst.add(900);
		
		
	
		
		
		bst.inorder();
		System.out.println();
		System.out.println("MIN: " + bst.getMinimiun());
		System.out.println("MAX: " + bst.getMaximiun());
		System.out.println("CEILING of 1000: " + bst.ceiling(1000));
		System.out.println("celling of 1000, recursive: " + bst.ceiling_recursive(1000));
		System.out.println("FLOOR of 1000: " + bst.floor(1000));
		System.out.println("FLOOR of 1000: " + bst.floor_recursive(1000));
		System.out.println("FLOOR of 1: " + bst.floor(1));
		System.out.println("FLOOR of 1: " + bst.floor(1));
		System.out.println("FLOOR of 65: " + bst.floor(65));
		System.out.println("FLOOR of 65: " + bst.floor(65));
		
		System.out.println("RANK 1: " + bst.rank(1));
		System.out.println("RANK 10: " + bst.rank(10));
		System.out.println("RANK 50: " + bst.rank(50));
		System.out.println("RANK 100: " + bst.rank(100));
		System.out.println("RANK 200: " + bst.rank(200));
		System.out.println("RANK 300: " + bst.rank(300));
		System.out.println("RANK 756: " + bst.rank(756));
		System.out.println("RANK 600: " + bst.rank(600));
		
		
	}
}
