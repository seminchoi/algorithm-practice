import java.util.*;

public class B1912 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(), max = 0;
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		max = arr[0];
		for(int i = 1; i < n; i++) {
			arr[i] = arr[i] > arr[i] + arr[i-1] ? arr[i] : arr[i] + arr[i-1];
			max = max < arr[i] ? arr[i] : max;
		}
		
		System.out.println(max);
	}

}
