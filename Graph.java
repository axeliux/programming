//Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
import java.util.*;
public class Graph{
    public static void main(String [] args){
        GNode startPoint = new GNode(100);
        Random random = new Random();
       
        for(int w = 0; w < 5; w ++){
            startPoint.addGNode(random.nextInt(100));
        }
        List<GNode> secondLevel = startPoint.getNodes();
        for(int i = 0; i < secondLevel.size(); i++){
            GNode node = secondLevel.get(i);
            for(int z = 0; z < 5; z ++){
                GNode n = new GNode(random.nextInt(100),node.value);
                node.getNodes().add(n);
                for(int c = 0; c< 5; c++)
                n.getNodes().add(new GNode(random.nextInt(100),n.value));
            }
           
        }
       
       
       
        printBFSGraph(startPoint);
        int target = random.nextInt(100);
        System.out.println("target: " + target    );
        searchDFS(startPoint,target);
	System.out.println();	
	searchBFS(startPoint,target);
    }
   
    public static void printBFSGraph(GNode node){
        Queue<GNode> q = new LinkedList<GNode>();
        q.add(node);
        while(q.peek() != null){
            GNode current = q.poll();
            System.out.println("[ {"+current.parent+"},"+ current.value +" ] ");
            List<GNode> childrens = current.getNodes();
            for(GNode ne : childrens){
                q.add(ne);
            }
        }
    }
    public static void searchDFS(GNode node, int value)
    {
        Stack<GNode> stack = new Stack<GNode>();
        stack.push(node);
        boolean found = false;
        while(!stack.empty()){
                GNode current = stack.peek();
                if(current.value == value){
                    found  =true;
                    break;
                }else{
                    GNode next = current.getNextNotVisited();
                    if(next == null){
                        current.visited =true;
                        stack.pop();
                    }else{
                        stack.push(next);
                    }
                }
        }
        if(found){
            while(!stack.empty())
                System.out.print(stack.pop().value + " <- ");
        }else{
            System.out.println("No found");
        }
    }
    public static void searchBFS(GNode node, int value){
        Queue<GNode> queue = new LinkedList<GNode>();
        queue.add(node);
        while(queue.peek() != null)
        {
           GNode current = queue.poll();
		if(current.value == value){
			System.out.println("Item Found");
			return;		
		}
		List<GNode> nodes = current.getNodes();
		for(GNode n : nodes){
			queue.add(n);		
		}
        }
	System.out.println("Item not found");
    }

    static class GNode{
        public int value;
        public int parent;
        public boolean visited;
        public List<GNode> nodes;
        public GNode(int val){
            this.value = val;
            this.parent = -666;
        }
        public GNode(int val,int parent){
            this.value =val;
            this.parent = parent;
        }
        public void addGNode(int value){
            if(nodes == null){
                nodes = new ArrayList<GNode>();
               
            }
           
            nodes.add(new GNode(value,this.value));
        }
        public List<GNode> getNodes(){
            if(nodes == null){
                nodes = new ArrayList<GNode>();
            }
            return nodes;
        }
        public GNode getNextNotVisited(){
            for(GNode node:nodes){
                if(!node.visited)return node;
            }
            return null;
        }
    }
}
