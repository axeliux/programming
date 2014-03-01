import java.util.*;
public class Graphs{
	public static void main(String [] args){
		System.out.println("Graphs");
		GNode root = null;
		root = createGraph(5);
		printBFS(root);
		Random	rm = new Random();
		int findMe = rm.nextInt(100);
		System.out.println("Find : " + findMe);
		findPath(root,findMe);
		System.out.println();
	}
	
	public static GNode createGraph(int n){
		Random random = new Random();
		GNode root = new GNode(random.nextInt(100),null);
		for(int i = 0; i < n ; i++){
			GNode l1 = new GNode(random.nextInt(100),root);
			root.addChild(l1);			
			for(int j =0; j < n; j++ ){
				GNode l2 = new GNode(random.nextInt(100),l1);
				l1.addChild(l2);
					
				for(int x=0; x < n; x++ ){
					GNode l3 = new GNode(random.nextInt(100),l2);
					l2.addChild(l3);
					
				}
			}
		}	
		return root;

	}
	public static void findPath(GNode root, int findMe){
		Stack<GNode> stack = new Stack<GNode>();
		stack.push(root);
		while(!stack.isEmpty()){
			GNode current = stack.peek(); 
			if(current.value == findMe){
				break;
			}else{
      				GNode next = current.getNextNotVisited();
      				if(next == null){
      					current.visited = true;
      					stack.pop();
      				}else{
      					stack.push(next);
      				}
			}
		}
		
		if(stack.isEmpty()){
			System.out.println("Item not found on the Graph: " + findMe);
		}else{
			while(!stack.isEmpty()){
				System.out.print(stack.pop().value + " <- ");
			}
		}
	}	
	public static void printBFS(GNode node){
		Queue<GNode> queue = new java.util.LinkedList<GNode>();
		queue.add(node);
		while(!queue.isEmpty()){
			GNode current = queue.poll();
			GNode parent = current.parent;
			if(parent != null)
				System.out.println("["+parent.value+", "+current.value+"]");
			else
				System.out.println("ROOT: " + current.value );
			
			List<GNode> list = current.getChildrens();
			for(GNode n: list){
				queue.add(n);
			}
		}
	}
	
	static class GNode{
		int value;
		GNode parent;
		List<GNode> childrens;
		boolean visited;
		
		public GNode(int value, GNode parent){
			this.parent = parent;
			this.value = value;
		}
		
		public void addChild(int value){
			getChildrens().add(new GNode(value,this.parent));
		}
		public void addChild(GNode node){
			getChildrens().add(node);
		}
		
		public List<GNode> getChildrens()
		{
			if(childrens == null){
				childrens = new java.util.LinkedList<GNode>();
			}
			return childrens;
		}
		public GNode getNextNotVisited(){
			List<GNode> list = this.getChildrens();
			for(GNode n : list){
				if(!n.visited){
					return n;
				}
			}
			return null;
		}
	}
}

