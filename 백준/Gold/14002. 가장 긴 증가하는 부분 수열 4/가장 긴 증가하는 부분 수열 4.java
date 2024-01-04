import java.util.*;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); 
		int A = sc.nextInt();
		
		int[] arr = new int[A];
		int[] DP = new int[A];
		int[] P = new int[A];
		
		for(int i = 0; i < A; i++) 
			arr[i] = sc.nextInt();
		
		Arrays.fill(DP, 0);
		int head = 0;
		DP[0] = arr[0];
		P[0] = 0; 
		
		for(int i = 1; i < A; i++) {
			if(arr[i] > DP[head]) {
				head++; DP[head] = arr[i];
				P[i] = head;
			}
			else {
				for(int j = 0; j <= head; j++) {
					if(arr[i] <= DP[j]) {
						DP[j] = arr[i];
						P[i] = j;
						break;
					}						
				}
			}
		}
		for(int i = head; i >= 0; i--) {
			while(true) {
				A--;
				if(P[A] == i) {
					DP[i] = arr[A];
					break;
				}
			}
		}
		System.out.println(head + 1);
		for(int i = 0; i <= head; i++) 
			System.out.print(DP[i] + " ");
		}
		
}


