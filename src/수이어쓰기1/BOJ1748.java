package 수이어쓰기1;

import java.util.Scanner;

public class BOJ1748 {
    //수를 입력받는다.
    //1자리수부터 시작
    //10을 곱했을 때 N보다 반복문 변수가 커지면 해당자리수까지의 수 * 자리수
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int dig = 1, i, res = 0;
        for (i = 1; i * 10 <= N; i*=10){
            res = res + (i*10 - i)*dig;
            dig++;
        }
        res = res + (N - i + 1)*dig;
        System.out.println(res);
    }
}
