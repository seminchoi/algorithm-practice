import java.util.*;

public class BOJ11055 {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		int A = sc.nextInt();
		int[] arr = new int[A];
		int[] DP = new int[A];
		
		for(int i = 0; i < A; i++) {		
			arr[i] = sc.nextInt();
			//DP[i]를 arr[i]로 초기화
			//위의 초기화를 해주지 않을 시 현재 배열의 값보다 이전 배열의 값들이 모두 클 경우 오차발생
			DP[i] = arr[i];
		}
		
		DP[0] = arr[0];
		for(int i = 1; i < A; i++) {
			for(int j = 0; j < i; j++) {
				//증가하는 부분수열 조건에 의해 arr[i]가 arr[j]보다 클 경우에만
				if( arr[i] > arr[j] )
					//DP[j]중 가장 큰 값을 현재 배열값과 더해서 DP[i]에 저장
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
