import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] DP = new int[100001][3];
		int T = sc.nextInt(), n, max = 0, min = 4, p = 1000000009;
		
		DP[1][0]=1; DP[1][1]=0; DP[1][2]=0;
		DP[2][0]=0; DP[2][1]=1; DP[2][2]=0;
		DP[3][0]=1; DP[3][1]=1; DP[3][2]=1;
		
		for(int i = 0; i < T; i++) {
			n = sc.nextInt();
			if(n > max)
				max = n;
			for(int j = min; j <= max; j++) {
				DP[j][0] = (DP[j-1][1] + DP[j-1][2]) % p;
				DP[j][1] = (DP[j-2][0] + DP[j-2][2]) % p;
				DP[j][2] = (DP[j-3][0] + DP[j-3][1]) % p;
			}
			System.out.println(((DP[n][0] + DP[n][1]) % p + DP[n][2]) % p);
			min = max + 1;
		}
	}
}
