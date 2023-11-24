import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt(), head = 0;
		int[] arr = new int[A] ,DP = new int[A];

		for(int i = 0; i < A; i++) {
			arr[i] = sc.nextInt();
		}
		
		DP[0] = arr[0];
		
		for(int i = 0; i < A; i++) {
			if(arr[i] < DP[head]) 
				DP[++head] = arr[i];
			else {
				for(int j = 0; j <= head; j++) {
					if(DP[j] <= arr[i]) {
						DP[j] = arr[i];
						break;
					}
				}
			}			
		}
		

		System.out.println(head+1);
		
	}

}
