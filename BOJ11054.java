import java.util.*;
public class BOJ11054 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt(), inshead = 0, deshead = 0;
		int[] arr = new int[A];
		int[][] DP = new int[4][A];

		for(int i = 0; i < A; i++) {
			arr[i] = sc.nextInt();
		}
		
		DP[0][0] = arr[0];
		DP[1][0] = arr[A-1];
		DP[2][0] = 0;
		DP[3][0] = 0;
		
		for(int i = 1; i < A; i++) {
			if(arr[i] > DP[0][inshead]) {
				DP[0][++inshead] = arr[i];
				DP[2][i] = inshead;
			}
			else {
				for(int j = 0; j <= inshead; j++) {
					if(DP[0][j] >= arr[i]) {
						DP[0][j] = arr[i];
						DP[2][i] = inshead;
						break;
					}
				}
			}
			if(arr[A-i-1] >	 DP[1][deshead]) {
				DP[1][++deshead] = arr[A-i-1];
				DP[3][A-i-1] = deshead;
			}
			else {
				for(int j = 0; j <= deshead; j++) {
					if(DP[1][j] >= arr[A-i-1]) {
						DP[1][j] = arr[A-i-1];
						DP[3][A-i-1] = deshead;
						break;
					}
				}
			}			
		}
		
		int max = 0;
		for(int i = 0; i < A; i++) 
			max = Math.max(DP[2][i]+ DP[3][i], max);

		System.out.println(max+1);

	}

}
