import java.util.*;

public class BOJ11057 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(), res = 0;
		
		//DP[N][K] stores the number of digits N and the last digit K.
		int[][] DP = new int[N+1][10];
			
		Arrays.fill(DP[1],1);
		
		for(int i = 2; i < N+1; i++) {
			Arrays.fill(DP[i], 0);
		}
		for(int i = 2; i < N+1; i++) {
			for(int j = 0; j < 10; j++) {
				for(int k = 0; k <= j; k++) {
					DP[i][j] +=  DP[i-1][k] % 10007;
					
				}
			}
		}
		
		for(int i = 0; i < 10; i++) {
			res = res + DP[N][i];
		}
		
		System.out.println(res%10007);
	}

}
