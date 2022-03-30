package 모든순열;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ10974 {
    static int N;
    static int[] arr;
    static boolean[] vis;
    public static void seq(int s){
        if(s == N-1){
            for(int i = 1; i<=N; i++){
                if(vis[i]){
                    arr[s] = i;
                    for(int j : arr)
                        System.out.print(j + " ");
                    System.out.println("");
                    return;
                }
            }
        }
        for(int i = 1; i <= N; i++){
            if(vis[i]) {
                arr[s] = i;
                vis[i] = false;
                seq(s+1);
                vis[i] = true;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        vis = new boolean[N+1];
        Arrays.fill(vis, true);

        seq(0);

    }
}
