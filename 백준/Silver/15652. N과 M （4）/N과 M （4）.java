import java.util.*;

public class Main {
	
	static int N, M;
	static int[] arr;
	
	static void NM(int s, int cur) {
		if( cur == M - 1 ) {
			for(int i = s; i <= N; i++) {
				for(int j = 0; j < M - 1; j++) {
					System.out.print(arr[j] + " ");
				}
				System.out.println(i);
			}
		}
		else {
			for(int i = s; i <= N; i++) {
				arr[cur] = i;
				NM(i,cur+1);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[M-1];		
		Arrays.fill(arr, 1);
		
		for(int i = 0; i < M; i++) {
			System.out.print("1 ");
		}
		System.out.println("");
		
		for(int i = M-1; i >= 0; i--) {
			NM(2,i);
		}

	}

}
