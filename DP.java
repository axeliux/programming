public class DP{
	public static void main(String [] args)
	{
		int V [] = {0,1,5,8,9,10,17,17,20,24,30};
		for(int i = 0; i < 50; i++){
			rod_cutting(V,i);
		}
		
	}
	public static void perms(char str [], int i, int n){
		if(i==n){
			System.out.println(str);
		}else{

			for(int j=i; j < n; j++ ){
				swap(str,i,j);
				perms(str,i+1,n);
				swap(str,i,j);
			}
		}
	}
	public static void swap(char str[], int a, int b){
		char tmp = str[a];
		str[a] = str[b];
		str[b] = tmp;
	}
	public static void LIS(int array[]){
		int size[] = new int[array.length];
		String path[] = new String[array.length];
		int max = 0;
		for(int i = 0; i < array.length; i++ ){
			size[i] = 1;
			path[i] = array[i]+"";
		}
		
		for(int i = 1; i < array.length; i++){
			for(int j = 0; j < i; j ++ ){
				if(array[j] <= array[i] && size[j] + 1 > size[i])
				{
					size[i] = size[j] + 1;
					path[i] = path[j] + " " + array[i];
				}
			}
		}
		
		for(int i = 0; i < array.length; i ++ ){
			System.out.print(path[i]);
			System.out.print(" = " + size[i]);
			System.out.println();
		}
		
		
	}
	public static void minMonedas(int monedas [], int sum){
		int result[] = new int[sum+1];
		String path [] = new String[sum+1];
		result[0] = 0;
		path[0] = "0";

		
		for(int i = 1; i < result.length; i++ ){
			result[i] = Integer.MAX_VALUE;
			path[i] = "";
			for(int j= 0; j < monedas.length; j++){
				if (monedas[j] <= i && (result[i - monedas[j]] + 1) < result[i]){
					result[i] = result[i - monedas[j]] +1;
					path[i] = path[i - monedas[j]] + " + " +monedas[j];
				}
			}
		}
		for(int index = 0; index < result.length; index ++ ){
			System.out.println(path[index] + " = " + index + " (" + result[index] + " coins)");
		}
		
	}
	
	public static void rod_cutting(int V[], int N){
		int B[] = new int[N+1];
		String path []= new String[N+1];
		B[0] = 0;
		path[0] = "0";
		
		for(int i = 1; i <= N ; i++){
			int max =  1;
			path[i] = i + "ps ($" +i +")";
			for(int j = 1; j < i; j++){
				int current = B[j] + B[i-j];
				if(current > max){
					max = current;
					path[i] = j + "ps ($"+B[j]+")  + " + (i-j) + "ps ($"+(B[i-j])+")";
				}
			}
			B[i] = max;
		}
		System.out.println(path[N] + " = "+N+" ($" + B[N]+")");		
	}
}
