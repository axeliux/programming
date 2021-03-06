public class Searching{
	public static void main(String args[]){
		//char [] str = "AABAACAADAABAAABAA".toCharArray();
		//char [] pattern = "AABA".toCharArray();
		//search(str,pattern);
		
		int A[] = new int[15];
		for(int i = 0; i < A.length; i++){
			A[i] = i;
		}
		
		print(A);
		rotate(A,5);
		print(A);
		System.out.println("find 0 " + binarySearch2(A,0,A.length-1,0));
		System.out.println("find 5 " + binarySearch2(A,0,A.length-1,5));
		System.out.println("find 10 " + binarySearch2(A,0,A.length-1,10));
		System.out.println("find 15 " + binarySearch2(A,0,A.length-1,15));
	}
	
	public static void print(int A[]){
		for(int i = 0; i < A.length; i++){
			System.out.print(A[i] + " ");
		}
		System.out.println();
	}
	/**
	* Naive pattern Searching.
	* Complexity  O (k (n-k))  where k = pattern lenght and n length of str
	*/
	public static void search(char str[], char pattern[]){
		int limit = str.length - pattern.length + 1;
		for(int i = 0; i < limit; i++){
			int j = 0;
			for( j = 0; j < pattern.length; j ++){
				if(str[i+j] != pattern[j])
					break;
			}
			if(j == pattern.length){
				System.out.println("Pattern found at: " + i);
			}
		}
	}
	
	public static boolean match(char [] str,int i, char pattern[], int j){
	    if(i < str.length && j == pattern.length)return false;
		if(i == str.length && j == pattern.length){
			return true;
		}
		if(i == str.length && j+1< pattern.length && pattern[j] == '*'){
			return false;
		}
		
		if(pattern[j] == '?' || pattern[j] == str[i]){
			return match(str,i+1,pattern,j+1);
		}
		if(pattern[j] == '*'){
			return match(str,i,pattern,j+1) || match(str,i+1,pattern,j);
		}
		
		return false;
	}
	public static char[] getLongestPalindrome(char str[]){
		int max = 0;
		char longest [] = new char[0];
		for(int i = 0; i < str.length; i++){
			char t[] = getPalindrome(str,i,i);
			if(max < t.length){
				max = t.length;
				longest = t;
			}
			t = getPalindrome(str,i, i+1);
			if(max < t.length){
				max = t.length;
				longest = t;
			}
		}
		
		System.out.println(longest);
		return longest;
	}
	public static char[] getPalindrome(char str[], int i, int j){
		while(i>=0 && j < str.length && str[i] == str[j]){
			i--;
			j++;
		}
		
		char result[] = new char[(j-i)+1];
		int index = 0;
		for(int k=i+1; k < j; k++){
			result[index++] = str[k];
		}
		return result;
	}
	public static boolean binarySearch2(int A[],int i ,int j, int T){
		if( i <= j){
			int m = (i+j)/2;
			if(A[m] == T){
				return true;
			}else{
				return binarySearch2(A,i,m-1,T) || binarySearch2(A,m+1,j,T);
			}
		}
		return false;
	}
	public static void rotate(int A[],int k){
		for(int j = 0; j < k; j++){
			int tmp = A[A.length-1];
			for(int i = A.length-1; i > 0 ; i--){
				A[i] = A[i-1];		
			}
			A[0] = tmp;
		}
	}
	public static boolean binarySearch(int A[],int i, int j, int T){
		if(i <= j){
		
			int m = (j + i) /2;
			if(A[m] == T){
				return true;
			}else if(A[m] > T){
				return binarySearch(A,i,m-1,T);
			}else{
				return binarySearch(A,m+1,j,T);
			}
		}
		
		return false;
	}
}

