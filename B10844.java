import java.util.Scanner;

public class B10844 {
	public static void main (String args[]) {
		Scanner sc = new Scanner(System.in);
		int res = 17, num = sc.nextInt(), rem = 2, p = 1000000000;
		
		if(num == 1) {
			System.out.println(9);
			return;
		}
		for(int i = 2; i < num; i++) {
			res = res * 2 - rem;
			rem = rem * 2 - 1;
			res = res % p;
			rem = rem % p;
		}
		System.out.println(res);
	}
	
}
