import java.util.*;
public class BST{
	
	public static void main(String [] args){
		TNode root = null;
		int array[] = {0,1,2,3,4,5,6,7,8,9,10};
		root = createTree(root, array,0,array.length-1);
		inorden(root);
		System.out.println();
		List<List<TNode>> lista = null;
		lista = treeToList(root);
		for(List<TNode> l : lista){
			for(TNode node: l){
				System.out.print(node.value + "->");
			}
			System.out.println();
		}
		System.out.println();
		TNode t2 = null;
		t2 = add(t2,8);
		add(t2,6);
		add(t2,7);
		add(t2,9);
		add(t2,10);
		System.out.println("Is Balanced: " + isBalanced(root));
		System.out.println("Is Subtree: " + isSubtree(root,t2));
		System.out.println("FCA > " + FCA(root,6,3));
		System.out.println("FCA2 > " + LCA2(root,6,3));
		System.out.println("Second largest: " + secondLargest(root));
		System.out.println("HashSum(8) : " + pathSum(root,8));
		System.out.println("HashSum(14) : " + pathSum(root,14));
		System.out.println("HashSum(26) : " + pathSum(root,26));
		System.out.println("HashSum(32) : " + pathSum(root,32));
	}
	public static List<List<TNode>> treeToList(TNode root){
		List<List<TNode>> result = new ArrayList<List<TNode>>();
		getList(root,0,result);
		return result;
	}
	public static void getList(TNode node, int depth, List<List<TNode>> list)
	{
		if(node == null)
		{
			return;
		}else{
			if(list.size() == depth){
				List<TNode> l = new ArrayList<TNode>();
				list.add(l);
			}
			list.get(depth).add(node);
			getList(node.left,depth+1,list);
			getList(node.right,depth+1,list);
			
		}
	}
	public static boolean isSubtree2(TNode t1, TNode t2){
		Queue<TNode> queue = new java.util.LinkedList<TNode>();
		queue.add(t2);
		while(!queue.isEmpty())
		{
			TNode current = queue.poll();
			if(equalTree(current, t2)){
				return true;
			}else{
				if(t2.value < t1.value){
					queue.add(t1.left);
				}else{
					queue.add(t1.right);
				}
			}
		}
		return false;
	}
	public static int secondLargest(TNode root){
		if(root.right != null){
			TNode target =root;
			TNode runner = root.right;
			while(runner.right != null){
				target = runner;
				runner = runner.right;
			}
			return target.value;
		}else{
			return largest(root.left);
		}
	}
	public static int largest(TNode node){
		if(node == null) return Integer.MIN_VALUE;
		TNode tmp = node;
		while(tmp.right != null){
			tmp = tmp.right;
		}
		return tmp.value;
		
	}
	public static TNode LCA2(TNode t, int a, int b){
		if(t==null) return null;
		if(t.value==a || t.value==b) return t;
		TNode l=LCA2(t.left,a,b);
		TNode r=LCA2(t.right,a,b);
		if(l!=null && r!=null) return t;
		return l!=null ? l:r; 
	}
	
	public static TNode FCA(TNode root, int a, int b){
		if(root == null) return null;
		if(root.value == a || root.value == b) return root;
		if(!exist(root,a) || !exist(root,b)) return null;
		
		return FCA_helper(root,a,b); 
	}
	public static TNode FCA_helper(TNode root,int a , int b){
		if(root == null) return null;
	
		boolean is_a_on_left = exist(root.left,a);
		boolean is_b_on_left = exist(root.left,b);
		if(is_a_on_left != is_b_on_left){
			return root;
		}else{
			if(is_a_on_left) // or is_b_on_left
			{
				return FCA(root.left,a,b);
			}else{
				return FCA(root.right,a,b);
			}
		}
	}
	public static boolean exist(TNode root, int value){
		if(root == null){
			return false;
		}else{
			if(root.value == value){
				return true;
			}else{
				return exist(root.left,value) || exist(root.right,value);
			}
		}
	}
	public static boolean isSubtree(TNode t1, TNode t2){
		if(t2 == null) return true;
		if(t1 == null) return false;
		if(equalTree(t1,t2)){
			return true;
		}else{
			return isSubtree(t1.left,t2) || isSubtree(t1.right,t2);	
		}
	}                                     
	
	public static boolean equalTree(TNode t1, TNode t2){
		if(t2== null) return true;
		if(t1== null) return false;
		if(t1.value == t2.value){
			return equalTree(t1.left, t2.left) && equalTree(t1.right,t2.right);
		}else{
			return false;
		}
	}
	public static TNode createTree(TNode root, int array[],int start, int end){
		if(start > end){
			return root;
		}else{
			
			int middle =( start + end )/2;
			
			root = add(root,array[middle]);
			createTree(root,array,start,middle-1);
			createTree(root,array,middle+1,end);
		}
		return root;
		
	}
	public static boolean isBalanced(TNode node){
		return MAX_DEPTH(node) - MIN_DEPTH(node) <= 1;
	}
	public static int MAX_DEPTH(TNode node){
		if(node == null){
			return 0;
		}else{
			return 1 + Math.max(MAX_DEPTH(node.left),MAX_DEPTH(node.right));
		}
	}
	public static int MIN_DEPTH(TNode node){
		if(node == null){
			return 0;
		}else{
			return 1 + Math.min(MIN_DEPTH(node.left), MIN_DEPTH(node.right));
		}
	}
	public static void preorden(TNode node){
		if(node == null){
		}else{
			System.out.print(node.value + "->");
			preorden(node.left);
			preorden(node.right);
		}
	}
	public static void inorden(TNode node){
		if(node == null)
		{
			return;
		}else{
			inorden(node.left);
			System.out.print(node.value + "->");
			inorden(node.right);
		}
	}
	public static TNode add(TNode node,int value){
		if(node == null){
			node = new TNode(value);
		}else{
			if(value < node.value){
				node.left = add(node.left,value);
			}else{
				node.right = add(node.right,value);
			}
		}
		return node;
	}
	public static boolean pathSum(TNode node, int sum){
		if(node == null && sum == 0){
			return true;
		}else if (node == null){
			return false;
		}else{
			sum -= node.value;
			return pathSum(node.left,sum) || pathSum(node.right,sum);
		}
	}
	
		static class TNode{
			TNode left;
			TNode right;
			int value;
			public TNode(int value){
				this.value = value;
			}
			public String toString(){
				return String.valueOf(value);
			}
		}
}
