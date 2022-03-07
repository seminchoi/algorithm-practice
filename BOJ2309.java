package 일곱난쟁이;

import java.util.*;

public class BOJ2309 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] BF = new int[9];
		int sum = 0;
		for(int i = 0; i < 9; i++) {
			BF[i] = sc.nextInt();
			sum += BF[i];
		}
		
		Loop : for(int i = 0; i < 9; i++) {
			if(sum - BF[i] > 100) {
				for(int j = i + 1; j < 9; j++) {
					if(sum - BF[i] - BF[j] == 100) {
						BF[i] = 0; BF[j] =0;
						break Loop;
					}
				}
			}
		}
		
		Arrays.sort(BF);
		for(int i = 2; i < 9; i++) {
				System.out.println(BF[i]);
		}
	}

}
