import java.util.*;
public class Tree {
	public static void main(String [] args)	{
		TNode root = null;
		root = insert(root,100);
		insert(root,50);
		insert(root,60);
		insert(root,40);
		insert(root,200);
		insert(root,150);
		insert(root,300);
		insert(root,250);
		insert(root,350);

		inorder(root);
		System.out.println();
		List<List<TNode>> result = tree2Lists(root);
		for(List<TNode> l : result){
			for(TNode n : l){
				System.out.print(n.value + " ->");
			}
			System.out.println();
		}
	System.out.println();

		System.out.println("MAx depth for this tree is: " + max_depth(root));
	}

	public static TNode insert(TNode node, int value){
		if(node == null){
			 node = new TNode(value);
		}else{
			if( value < node.value){
				node.left = insert(node.left,value);			
			}else{
				node.right = insert(node.right,value);
			}
		}
		return node;
	}
  	
	public static List<List<TNode>> tree2Lists(TNode node){
		List<List<TNode>> result = new LinkedList<List<TNode>>();
		getNodesByDepth(node,0,result);
		return result;	
	}

	public static int max_depth(TNode node){
		if(node == null)
		{
			return 0;
		}else
		{
			return 1 + Math.max(max_depth(node.right),max_depth(node.left));
		}	
	}
	public static void getNodesByDepth(TNode node, int depth, List<List<TNode>> list){
		if( node == null){
			return;		
		}else{
			if( list.size() == depth){
				list.add(new LinkedList<TNode>());			
			}
			getNodesByDepth(node.left, depth + 1, list);
			list.get(depth).add(node);
			getNodesByDepth(node.right, depth + 1, list);

		}	
	}
	public static void inorder(TNode node){
		if( node == null){
			return;		
		}else{
			inorder(node.left);
			System.out.print(node.value + " -> ");
			inorder(node.right);		
		}	
	}


static class TNode{
 int value;
 TNode right;
 TNode left;
 

 public TNode(int val){
 	this.value = val; 
  }
 }
}
