package 집합;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ11723 {
    static boolean[] S = new boolean[21];

    static void add(int n){
        S[n] = true;
    }

    static void remove(int n){
        S[n] = false;
    }

    static void check(int n){
        if(S[n])
            System.out.println(1);
        else
            System.out.println(0);
    }

    static void toggle(int n){
        S[n] = !S[n];
    }

    static void all(){
        for (int i = 1; i < 21; i++) {
            S[i] = true;
        }
    }

    static void empty() {
        for (int i = 1; i < 21; i++) {
            S[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String oper;

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            oper = st.nextToken();
            if (oper.equals("add")) {
                int x = Integer.parseInt(st.nextToken());
                add(x);
            }
            else if (oper.equals("remove")) {
                int x = Integer.parseInt(st.nextToken());
                remove(x);
            }
            else if (oper.equals("check")) {
                int x = Integer.parseInt(st.nextToken());
                check(x);
            }
            else if (oper.equals("toggle")) {
                int x = Integer.parseInt(st.nextToken());
                toggle(x);
            }
            else if (oper.equals("all")) all();
            else empty();
        }
    }
}
