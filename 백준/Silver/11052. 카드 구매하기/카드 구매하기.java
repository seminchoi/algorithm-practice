import java.util.Scanner;
import java.util.Arrays;
public class Main {
 

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] P = new int[1001];
		int[] DP = new int[1001];
		
		
		for(int i = 1; i <= N; i++) {
			P[i] = sc.nextInt();
		}
		
		Arrays.fill(DP, 0);
		
		for(int i = 1; i <= N; i++) {
			DP[i] = P[i];
			for(int j = 1; j < i; j++) {
				if(DP[i] < DP[i-j] + P[j])
					DP[i] = DP[i-j] + P[j];
			}
		}
		System.out.println(DP[N]);
	}

}
