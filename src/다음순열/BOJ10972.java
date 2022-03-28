package 다음순열;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ10972 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        boolean[] vis = new boolean[N+1];
        Arrays.fill(vis,false);
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int bridx = -1;
        Loop : for (int i = N - 1; i >= 0; i--) {
            if(arr[i] < N){
                for(int j = i + 1; j < N; j++){
                    if(arr[i] < arr[j]){
                        int tmp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = tmp;
                        bridx = i;
                        break Loop;
                    }
                }
            }
        }

        if(bridx == -1) {
            System.out.println(-1);
            return;
        }

        for(int i = 0; i <= bridx; i++){
            vis[arr[i]] = true;
        }

        for(int i = 1; i < N+1 && bridx < N - 1; i++){
            if(!vis[i]){
                arr[++bridx] = i;
                vis[i] = true;
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
