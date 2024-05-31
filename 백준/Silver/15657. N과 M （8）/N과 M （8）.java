import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int N, M;
	static int[] arr, bf;
	
	static void NM(int s, int cur) {
		if( cur == M - 1 ) {
			for(int i = s; i < N; i++) {
				bf[cur] = arr[i];
				for(int j : bf) {
					System.out.print(j + " ");
				}
				System.out.println("");
			}
			return;
		}
		for(int i = s; i < N ; i++) {
			bf[cur] = arr[i];
			NM(i,cur+1);
			}		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		bf = new int[M]; 
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
	
		for(int i = 0; i < N ; i++) {
			bf[0] = arr[i];
			if(M == 1) System.out.println(bf[0]);
			else NM(i,1);
		}
		

	}
}
