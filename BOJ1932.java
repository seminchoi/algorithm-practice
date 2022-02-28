import java.util.*;

public class BOJ1932 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] DP = new int[n][];
		
		for(int i = 0; i < n; i++) {
			DP[i] = new int[i+1];
			for(int j = 0; j <= i; j++) {
				DP[i][j] = sc.nextInt();
			}
		}
		
		for(int i = n-1; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				DP[i-1][j] += Math.max(DP[i][j], DP[i][j+1]); 
			}
		}
		
		System.out.println(DP[0][0]);
	}

}
