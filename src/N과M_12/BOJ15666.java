package N과M_12;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ15666 {
    static int N, M, len = 0;
    static int[] arr, bf;
    static void NM(int s, int now){
        if(now == M-1){
            for(int i = s; i < len; i++){
                bf[now] = arr[i];
                for(int j : bf)
                    System.out.print(j + " ");
                System.out.println("");
            }
            return;
        }
        for(int i = s; i < len; i++){
            bf[now] = arr[i];
            NM(i,now+1);
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N];
        Arrays.fill(arr,10001);
        Loop : for(int i = 0; i < N; i++) {
            int tmp = sc.nextInt();
            for(int j = 0; j < i; j++){
                if(tmp == arr[j])
                    continue Loop;
            }
            arr[len++] = tmp;
        }
        Arrays.sort(arr);
        bf = new int[M];

        NM(0,0);

    }
}

