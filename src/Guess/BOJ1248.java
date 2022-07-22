package Guess;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1248 {
//    static int k;
//    static String sign;
//    static int[] N;
//    static boolean[] vis = new boolean[10];
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        k = sc.nextInt();
//        N = new int[k + 1];
//        sign = sc.next();
//
//        for (int i = 0; i < 10; i++) {
//            N[0] = i;
//            vis[i] = true;
//            solution(1);
//            vis[i] = false;
//        }
//    }
//
//    static void solution(int cnt) {
//        if (cnt == k + 1) {
//            if (min == null)
//                min = Arrays.toString(N).replaceAll("[^0-9]","");
//            max = Arrays.toString(N).replaceAll("[^0-9]","");
//        } else {
//
//            if (sign.charAt() == '<') {
//                for (int i = pre + 1; i < 10; i++) {
//                    if (!vis[i]) {
//                        N[cnt] = i;
//                        vis[i] = true;
//                        solution(cnt + 1);
//                        vis[i] = false;
//                    }
//                }
//            } else {
//                for (int i = 0; i < pre; i++) {
//                    if (!vis[i]) {
//                        N[cnt] = i;
//                        vis[i] = true;
//                        solution(cnt + 1);
//                        vis[i] = false;
//                    }
//                }
//            }
//        }
//    }
}
