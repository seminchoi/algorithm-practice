package 외판원순회2;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ10971 {
    static int N, min;
    static int[][] bf;
    static boolean[] vis;

    public static void round(int s,int cnt,int res){
        if(cnt == N-1 && bf[s][0] != 0){
            res += bf[s][0];
            min = min<res? min:res;
            return;
        }
        for (int i = 0; i < N; i++) {
            if(vis[i] && bf[s][i]!=0) {
                vis[i] = false;
                round(i, cnt + 1, res + bf[s][i]);
                vis[i] = true;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        bf = new int[N][N];
        vis = new boolean[N];
        Arrays.fill(vis,true);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bf[i][j] = sc.nextInt();
            }
        }

        min =10000000;
        vis[0]=false;
        round(0,0,0);
        System.out.println(min);
    }
}
//1 2 3 4 1
//1 2 4 3 1
//1
