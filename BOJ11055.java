import java.util.*;

public class BOJ11055 {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		int A = sc.nextInt();
		int[] arr = new int[A];
		int[] DP = new int[A];
		
		for(int i = 0; i < A; i++) {		
			arr[i] = sc.nextInt();
			//DP[i]를 arr[i]와 같은 값으로 초기화
			//위의 초기화를 하지 않을 시 이전 배열의 값들이 모두 현재배열보다 클 경우에 현재 배열값이 무시되는 경우 발생
			DP[i] = arr[i];
		}
		
		DP[0] = arr[0];
		for(int i = 1; i < A; i++) {
			for(int j = 0; j < i; j++) {
				//증가하는 부분수열의 조건에 의해 arr[i]보다 작은 arr[j]중
				if( arr[i] > arr[j] )
					//합이 가장 큰 DP[j]를 arr[i]와 더해서 저장
					DP[i] = DP[i] < DP[j] + arr[i] ? DP[j] + arr[i] : DP[i];
			}
		}		
		
		int max = 0;
		for(int i = 0; i< A; i++) {
			max = DP[i] > max ? DP[i] : max;
		}
		
		System.out.println(max);		
		
	}

}
