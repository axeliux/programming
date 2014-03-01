import java.util.*;
public class Sort{
	public static void main(String arg[]){
	int[][] problem = {
	{ 2, 4, 8,   3, 9, 5,   7, 1, 6 },
	{ 5, 7, 1,   6, 2, 8,   3, 4, 9 },
	{ 9, 3, 6,   7, 4, 1,   5, 8, 2 },

	{ 6, 8, 2,   5, 3, 9,   1, 7, 4 },
	{ 3, 5, 9,   1, 7, 4,   6, 2, 8 },
	{ 7, 1, 4,   8, 6, 2,   9, 5, 3 },

	{ 8, 6, 3,   4, 1, 7,   2, 9, 5 },
	{ 1, 9, 5,   2, 8, 6,   4, 3, 7 },
	{ 4, 2, 7,   9, 5, 3,   8, 6, 1 } };
	
	System.out.println("Validate sudoku: " + validateSudoku(problem));
		
			
	}

	public static void rotate(int A[], int t){
		while(t-- >= 0 ){
			int tmp = A[A.length-1];
			for(int i = A.length-1; i > 0; i--){
				A[i] = A[i-1];
			}
			A[0] = tmp;
		}
	}
	public static boolean binarySearch(int A[], int target,int start,  int end){
		if(start < end){
			int middle = (start + end)/ 2;
			if(target == A[middle]){
				return true;
			}else if(A[middle] < target){
				return binarySearch(A,target,middle+1,end);
			}else{
				return binarySearch(A,target,start,middle-1);
			}
		}
		return false;
	}
	public static boolean binarySearchR(int A[],int target, int start, int end){
		if(start < end){
			int middle = (start + end) /2;
			if( target == A[middle] ){
				return true;
			}else if( target > A[middle] ){
				if(A[middle] > A[middle+1]){
					return false;
				}else{
					return binarySearchR(A,target,middle+1,end);
				}
			}else{
				if(A[middle] < A[middle-1]){
					return false;
				}else{
					return binarySearchR(A,target,start,middle-1);
				}
			}
		}
		return false;
	}
	public static void print(int A[]){
		for(int i = 0; i < A.length; i++){
			System.out.print(A[i] + " ");
		}
		System.out.println();
	}
	
	// best case complexity O (n)
	// AVG case complexity > O ( n ) < O ( n^2 )  
	// worse case complexity o ( n^2 )
	
	public static void insertionSort(int array[]){
		
		for(int i = 1; i < array.length ; i++){
			int key = array[i];
			int j = i-1;
			while(j >= 0 && array[j] >  key){
			
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = key;
		}
		
		
	}
	
	public static void insertionSort(Anagram array[]){
		for(int i = 1; i < array.length; i++){
			Anagram target = array[i];
			int j = i-1;
			while( j >= 0 && array[j].key.compareToIgnoreCase(target.key) > 0){
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = target;
		}
	}
	public static void HEAPSORT(int A[]){
		BUILD_MAX_HEAP(A);
		
		for(int i = A.length -1; i >= 1; i-- )
		{
			swap(A,0,i);
			MAX_HEAPIFY(A,0,i);
		}
	}
	public static void BUILD_MAX_HEAP(int A[] ){
		for(int i = A.length/ 2; i >= 0; i --){
			MAX_HEAPIFY(A,i,A.length);
		}
	}
	public static void MAX_HEAPIFY(int A [], int i, int heap_size){
	        
		int left = getLeftIndex(i);
		int right = getRightIndex(i);
		int largest = -1;
		if( left < heap_size && A[left] > A[i] ){
			largest = left;
		}else{
			largest = i;
		}
		if( right < heap_size && A[right] > A[largest] ){
			largest = right;
		}
		if( largest != i ){
			swap(A,i,largest);
			MAX_HEAPIFY(A,largest,heap_size);
		}
	}
	public static void swap(char A[], int a, int b){
		char tmp = A[a];
		A[a] = A[b];
		A[b] = tmp;
	}
	public static void swap(int A[],int a, int b){
		int tmp = A[a];
		A[a]=A[b];
		A[b] = tmp;
	}
	public static void swap(Person A[], int a, int b){
		Person tmp = A[a];
		A[a] = A[b];
		A[b] = tmp;
	}
	public static int getLeftIndex(int i){

		i++;
		return (2*i) - 1;
	}
	public static int getRightIndex(int i){
		i++;
		return 2*i;
	}
	public static void quicksort(Person A[], int start, int end){
		if( start < end){
			int p = partition(A,start, end);
			quicksort(A,start,p-1);
			quicksort(A,p+1,end);
		}
	}
	public static int partition(Person A[], int start, int end){
		Person key = A[end];
		int idx = start -1;
		for(int i = start; i < end; i++ ){
			if(A[i].h < key.h){
				swap(A,++idx,i);
			}
		}
		swap(A,++idx,end);
		return idx;
	}
	public static void quicksort(int A[], int start, int end){
		if(start < end){
			int p_index = partition(A,start, end);

			quicksort(A,start, p_index-1);
			quicksort(A,p_index+1, end);
		}
	}
	public static int partition(int A[], int start, int end){
		int key = A[end];
		int index = start-1; // this a key part dont forget it
		for(int i = start; i < end ;  i++ ){
			if(A[i] <= key){
				swap(A,++index,i);
			}
		}
		swap(A,++index,end);
		return index;
	}
	public static void quicksort(char A[], int start, int end){
		if(start < end){
			int p = partition(A,start,end);
			quicksort(A,start,p-1);
			quicksort(A,p+1,end);
		}

	}
	public static int partition(char A[], int start, int end){
		int key = A[end];
		int index = start -1;
		for(int i = start; i < end; i++ ){
			if(A[i] <= key){
				swap(A,++index,i);
			}
		}
		swap(A,++index,end);
		return index;
	}
	public static void mergesort(int A[],int start, int end){
		if( start < end ){

			int middle = (start + end)/2;
			mergesort(A,start,middle);
			mergesort(A,middle+1,end);
			merge(A,start,middle,end);
		}
	}	
	public static void merge(int A[],int start, int middle, int end){

		int L[] = new int[(middle - start)+1];
		int R[] = new int[(end - middle)];

		int index = start;
		for(int i = 0; i  < L.length ; i++){
			L[i] = A[index++];
		}
		for(int j = 0; j < R.length ; j++){
			R[j] = A[index++];
		}
		int i = 0, j = 0;
		for(int k = start; k  <= end ; k++){
			if (j >= R.length || (i < L.length && L[i] <= R[j])){
				A[k] = L[i++];
			}else{
				A[k] = R[j++];
			}
		}	
	}

	public static void merge2(int A[], int B[]){
		int currentIndex = A.length -1;
		int indexB = B.length-1;
		int indexA = 0;
		while(A[indexA] != 0 ){
			indexA++;
		}
		indexA--;
		while(currentIndex >= 0 ){
			if( indexB < 0 || (indexA >= 0 && A[indexA] >= B[indexB])){
				A[currentIndex--] = A[indexA--];
			}else{
				A[currentIndex--] = B[indexB--];
			}
		}
	}

	
	static class Anagram{
		public String value;
		public String key;
		public Anagram(String value){
			 this.value = value;
			 char [] s = value.toCharArray();
			 quicksort(s,0,s.length-1);
			 this.key = new String(s);
		}
		
	}
	public static void print(Person A[]){
		for(int i = 0; i < A.length; i++ ){
			System.out.print ("(" + A[i]. h +"," + A[i].w +"), ") ;
		}
		System.out.println();
	}
	public static void LongestTower(Person A[]){
		String tower [] = new String[A.length];
		int len [] = new int[A.length];
		int MAX = 0;
		//  init
		for(int i = 0; i < A.length; i++ ){
			len[i] = 1;
			tower[i] = "(" + A[i]. h +"," + A[i].w +")" ;
		}
		
		for(int i= 1; i < A.length; i++ ){
			Person c = A[i];
			for(int j = 0; j < i; j++ ){
				Person p = A[j];
				if(p.h <= c.h && p.w <= c.w && len[j] + 1 > len[i]){
					len[i] = len[j] + 1;
					tower[i] = tower[j] + ",("+A[i].h+","+A[i].w+")";
					if(MAX < len[i]){
						MAX = len[i];
					}
				}
			}
		}
		
		for(int i = 0; i < len.length ; i++ ){
			if(len[i] == MAX){
				System.out.println(tower[i]);
			}
		} 
	}
	public static boolean validateSudoku(int A[][]){
	        @SuppressWarnings("unchecked")
		Hashtable<Integer,Boolean> rows[] =(Hashtable<Integer,Boolean>[]) new Hashtable<?,?>[9];
                @SuppressWarnings("unchecked")
		Hashtable<Integer,Boolean> columns[] =(Hashtable<Integer,Boolean>[]) new Hashtable<?,?>[9];
                @SuppressWarnings("unchecked")
		Hashtable<Integer,Boolean> blocks[][] =(Hashtable<Integer,Boolean>[][]) new Hashtable<?,?>[3][3];
		for(int r = 0; r < A.length; r++ ){
			for(int c = 0; c < A[r].length; c++){
				int key = A[r][c];

				if(rows[r] != null && rows[r].containsKey(key)){
					return false;
				}else{
					rows[r] = new Hashtable<Integer,Boolean>();
					rows[r].put(key,true);
				}
				if(columns[c] != null && columns[c].containsKey(key)){
					return false;
				}else{
					columns[c] = new Hashtable<Integer,Boolean>();
					columns[c].put(key,true);
				}
				int rk = r / 3;
				int ck = c / 3;
				if(blocks[rk][ck] != null && blocks[rk][ck].containsKey(key)){
					return false;
				}else{
					blocks[rk][ck] = new Hashtable<Integer,Boolean>();
					blocks[rk][ck].put(key,true);
				}
			}
		}
		return true;
	}
	static class Person{
		public int h;
		public int w;
		public Person(int h, int w){
			this.h = h;
			this.w = w;
		}
	}
}







