import java.util.Random;
public class MAX{
	public static void main(String arg []){
		Random ran = new Random();
		printAll(ran.nextInt(50));
		System.out.println();
	}
	public static void findMaxSubArray(int[] A){
 		int start = 0;
 		int end = 0;
 		int start_so_far = 0;
 		int max = 0;
 		int max_so_far = 0;
 		
 		for( int i = 0; i < A.length; i++)
 		{
 			max_so_far += A[i];
 			
 			if( max_so_far >= max ){
 				max = max_so_far;
 				end = i; 
 				start = start_so_far;
 			}else if(max_so_far < 0){
 				max_so_far = 0;
 				start_so_far = i + 1;
 			}
 		}
 		
 		for(int i= start; i <= end; i++){
 			System.out.print(A[i] + " , ");
 		}
 		System.out.print(" = " + max);
 	}
 	
 	public static void printAll(int t){
 		System.out.println(t + " = ");
 		for(int i=2; i <= t; i++){
 			for(int j=1; j <= i/2 ; j++){
 				System.out.print((i-j) + "+" + j);
 				for(int x=i; x < t; x++){
 					System.out.print("+1");
 				}
			System.out.println();	 			
 			}
 		}
 	}
      
}
