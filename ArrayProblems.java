import java.util.Random;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
public class ArrayProblems{
	public static void main(String arg[]){
		int N = 55;
		int A[] = new int[N];
		Random r = new Random();
		
		for(int i  = 0; i < A.length; i++){
			int v = r.nextInt(30);
			A[i] = v > 10? v-30:v;
			//A[i] = v;
			System.out.print(A[i] + ", ");
		}
		int k = r.nextInt(100);
		System.out.println();
		//sumK(A,k);	
		ZeroSum(A);
			
	}
	
	public static void sumK(int A[], int k){
		int start = 0;
		int currentSum = 0;
		int counter = 0;
		for(int i = 0; i < A.length; i++){
			currentSum += A[i];
			
			if(currentSum == k ){
				counter++;
				for(int j = start; j <= i; j++){
					System.out.print(A[j] + ", ");
				}
				System.out.println(" = " + k);
				currentSum -=A[start++];
			}
			
			while( currentSum > k){
				currentSum -= A[start++];
			}
		}
		if(counter == 0) {
			System.out.println("cannot find Sum K = "+ k);
		}
	}
	public static void ZeroSum(int A[]){
		int currentKey = 0;
		Hashtable<Integer,List<Integer>> table = new Hashtable<Integer,List<Integer>>();
		
		for(int i = 0; i < A.length; i++){
			currentKey += A[i];
			if(table.containsKey(currentKey)){
				table.get(currentKey).add(i);
			}else{
				List<Integer> list = new ArrayList<Integer>();
				list.add(i);
				table.put(currentKey,list);
			}
		}
		
		Set<Integer> keys = table.keySet();
		for(Integer key: keys){
			List<Integer> list = table.get(key);
			if(list.size() > 1){
				for(int i = 0; i < list.size() -1; i++){
					for(int j= i+1; j < list.size(); j++){
						for(int k = list.get(i) + 1; k <= list.get(j); k++){
							System.out.print(A[k] + " + ");
						}
						System.out.println(" = 0");
					}
				}
			}
		}

	}
	
}
