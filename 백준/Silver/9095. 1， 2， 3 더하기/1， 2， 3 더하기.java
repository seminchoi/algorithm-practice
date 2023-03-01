import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		int[] N = new int[12];	
		Scanner sc = new Scanner(System.in);
		int input, num = sc.nextInt(); 
		N[1]=1;
		N[2]=2;
		N[3]=4;
		
		for(int i = 4; i <= 11; i++) 
			N[i] = N[i-1] + N[i-2] + N[i-3];
		
		for(int i = 0; i < num; i++) {
			input = sc.nextInt();
			System.out.println(N[input]);
		}
	}
}
