import java.util.*;

public class B2156 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] w = new int[n];
		
		int[][] DP = new int[n][4];
		
		
		
		for(int i = 0; i < n; i++) { 
			w[i] = sc.nextInt();		
			Arrays.fill(DP[i], 0);			
		}
		
		
		DP[0][0] = w[0];
		
		
		for(int i = 1; i < n; i++) {
			DP[i][0] = DP[i-1][2] > DP[i-1][3] ? w[i] + DP[i-1][2] : w[i] + DP[i-1][3] ;
			//The case of first time drink wine without continuation
			DP[i][1] = w[i] + DP[i-1][0];
			//The case of drink wine twice in a row
			DP[i][2] = DP[i-1][0] > DP[i-1][1] ? DP[i-1][0] : DP[i-1][1];
			//The case of first time do not drink wine without continuation
			DP[i][3] = DP[i-1][2];
			//The case of do not drink wine twice in a row
		}
		
		Arrays.sort(DP[n-1]);
		System.out.println(DP[n-1][3]);
	}

}
