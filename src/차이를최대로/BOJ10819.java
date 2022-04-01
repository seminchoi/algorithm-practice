package 차이를최대로;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ10819 {
    static int N , res = 0;
    static int[] arr, bf;
    static boolean[] vis;

    public static int dif(){
        int res = 0;
        for(int i = 0; i < N-1; i++)
            res += Math.abs(bf[i] - bf[i+1]);
        return res;
    }

    public static void maxarr(int idx){
        if( idx == N-1 ){
            for (int i = 0; i < N; i++) {
                if(vis[i]){
                    bf[idx] = arr[i];
                    res = Math.max(res,dif());
                    return;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if(vis[i]) {
                bf[idx] = arr[i];
                vis[i] = false;
                maxarr(idx+1);
                vis[i] = true;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N]; bf = new int[N]; vis = new boolean[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.fill(vis,true);
        //1.자바 sort 라이브러리로 정렬후에 앞뒤왔다갔다함
        //2.mergesort로 구현함
        maxarr(0);

        System.out.println(res);
    }
}
