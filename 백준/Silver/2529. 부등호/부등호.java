import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int k;
    static String min, max;
    static int[] N;
    static char[] inequal;
    static boolean[] vis = new boolean[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        N = new int[k + 1];
        inequal = new char[k];
        for (int i = 0; i < k; i++) {
            inequal[i] = sc.next().charAt(0);
        }

        for (int i = 0; i < 10; i++) {
            N[0] = i;
            vis[i] = true;
            solution(1);
            vis[i] = false;
        }

        System.out.println(max);
        System.out.println(min);
    }

    static void solution(int cnt) {
        if (cnt == k + 1) {
            if (min == null)
                min = Arrays.toString(N).replaceAll("[^0-9]","");
            max = Arrays.toString(N).replaceAll("[^0-9]","");
        } else {
            int pre = N[cnt - 1];
            if (inequal[cnt - 1] == '<') {
                for (int i = pre + 1; i < 10; i++) {
                    if (!vis[i]) {
                        N[cnt] = i;
                        vis[i] = true;
                        solution(cnt + 1);
                        vis[i] = false;
                    }
                }
            } else {
                for (int i = 0; i < pre; i++) {
                    if (!vis[i]) {
                        N[cnt] = i;
                        vis[i] = true;
                        solution(cnt + 1);
                        vis[i] = false;
                    }
                }
            }
        }
    }
}
