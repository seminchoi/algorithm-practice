import java.util.Scanner;
import java.util.Arrays;

public class Main {
	static int[] D = new int[1000001];
	
	public static int makeOne (int X) {
		
		for(int i = 2; i <= X; i++) {
			if(i % 2 == 0)
				D[i] = D[i/2] + 1;
			if(i % 3 == 0) {
				if(D[i] == 0 || D[i] > D[i/3] + 1 )
					D[i] = D[i/3] + 1;
			}
			if(D[i] == 0 || D[i] > D[i-1] + 1)
				D[i] = D[i-1] + 1;
		}
		
		return D[X];
	}
	
	public static void main (String args[]) {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
		
		Arrays.fill(D,0);
		
		System.out.println(makeOne(X));
		
	}
}
