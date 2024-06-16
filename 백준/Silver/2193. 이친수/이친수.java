import java.util.*;

public class Main {

	public static void main(String[] args) {
		long [][] DP = new long[91][2];
		DP[1][0] = 0; DP[1][1] = 1;
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		for(int i = 2; i<= N; i++) {
			for(int j = 0; j <= 1; j++) {
				if(j == 0)
					DP[i][j] = DP[i-1][0] + DP[i-1][1];
				else
					DP[i][j] = DP[i-1][0];
			}
		}

		long res = DP[N][0] + DP[N][1];
		System.out.println(res);
	}

}
