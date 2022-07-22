package 스타트와링크;

import java.util.Scanner;

public class BOJ14889 {

    static int[][] S;
    static int N, n, res = -1;
    static boolean[] vis;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        n = N / 2;
        S = new int[N][N];
        vis = new boolean[N];


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                S[i][j] = scanner.nextInt();
            }
        }
        vis[0] = true;
        team(1,1);
        System.out.println(res);
    }

    //몇 명을 골랐는지 카운트할 변수

    static void team(int s,int cnt){
        if(cnt == n){
            int diff = statCalc();
            res = res == -1 || res > diff ? diff : res;
            System.out.println(res);
            return;
        }
        else{
            for (int i = s; i < N; i++) {
                if(N - i < n - cnt) {
                    return;
                }
                if(vis[i])
                    continue;
                else{
                    vis[i]=true;
                    team(s+1,cnt+1);
                    vis[i] = false;
                }
            }
        }
    }

    static int statCalc(){
        int startStat = 0, linkStat = 0, diff = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(vis[i] && vis[j])
                    startStat = startStat + S[i][j];
                else if(!vis[i] && !vis[j]){
                    linkStat = linkStat + S[i][j];
                }
            }
        }
        diff = Math.abs(startStat - linkStat);
        return diff;
    }
}
