package 설탕배달;

import java.util.Scanner;

public class BOJ2839 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(), min = 5000;
        for(int i = 0, loop = N / 5; i <= loop; i++){
            if(N % 3 == 0)
                min = min > i + N/3 ? i + N/3 : min;
            N -= 5;
        }

        if(min == 5000)
            System.out.println(-1);
        else
            System.out.println(min);
    }
}
