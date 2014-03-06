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
	 /**
     * Gets the entry corresponding to the specified key; if no such entry
     * exists, returns the entry for the greatest key less than the specified
     * key; if no such entry exists, returns {@code null}.
     */
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
			return new Entry<K>(key,parent);
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
			return node;
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
	
	
	static class Entry<K>{
		public K key;
		
		public Entry<K> left;
		public Entry<K> right;
		public Entry<K> parent;
		public Entry(K k,Entry<K> p){
			this.key =k;
			this.parent = p;
		}
		public String toString(){
			return " " + key.toString() + " ";
		}
		
	}
	
	public static void main(String [] args){
		GenericBST<Integer> bst = new GenericBST<Integer>();
		bst.add(100);
		
		bst.add(50);
		bst.add(150);
		
		bst.add(25);
		bst.add(60);
		bst.add(10);
		bst.add(30);
		
		bst.add(130);
		bst.add(120);
		bst.add(110);
		
		
		bst.inorder();
		System.out.println();
		System.out.println(bst.floor(100));
		System.out.println(bst.floor(10));
		System.out.println(bst.floor(150));
		System.out.println(bst.floor(110));
		System.out.println(bst.floor(1));
		System.out.println(bst.floor(160));
		System.out.println(bst.floor(27));
		System.out.println(bst.floor(66));
		System.out.println(bst.floor(55));
		
	}
}
