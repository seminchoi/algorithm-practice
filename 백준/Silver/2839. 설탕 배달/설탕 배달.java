import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(), min = 5000;
        int M = N /5;
        for(int i = 0; i <= M; i++){
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
