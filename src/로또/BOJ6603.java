package 로또;

import java.util.Scanner;

public class BOJ6603 {
    static int k;
    static int[] S,res = new int[6];
    static boolean[] vis = new boolean[14];

    static void lotto(int s, int cnt) {
        if(cnt == 6){
            for (int i = s; i < k; i++) {
                res[5] = S[i];
                for(int j : res){
                    System.out.print(j + " ");
                }
                System.out.println();
            }
            return;
        }
        for (int i = s; i < k && k-i > 6 - cnt; i++) {
            res[cnt-1] = S[i];
            lotto(i+1,cnt+1);
            //k = 10 cnt = 4
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            k = sc.nextInt();
            if(k==0)
                break;
            S = new int[k];
            for (int i = 0; i < k; i++)
                S[i] = sc.nextInt();
            lotto(0,1);
            System.out.println("");
        }
    }
}
