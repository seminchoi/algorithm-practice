package 전구와스위치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2138 {
    static int n, cnt = 0;
    static boolean[] ori, fin;

    static void solution(int idx){
        if(ori[idx-1] != fin[idx-1]){

            cnt++;
            ori[idx-1] = !ori[idx-1];

            ori[idx] = !ori[idx];

            if(idx < n - 1){
                ori[idx + 1] = !ori[idx + 1];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        ori = new boolean[n];
        fin = new boolean[n];

        String s = br.readLine();
        for (int i = 0; i < n; i++) {
            ori[i] = s.charAt(i) == '1';
        }

        s = br.readLine();
        for (int i = 0; i < n; i++) {
            fin[i] = s.charAt(i) == '1';
        }

        boolean[] tmp = ori.clone();

        for (int i = 1; i < n; i++) {
            solution(i);
        }

        boolean isSame = true;
        for (int i = 0; i < n; i++) {
            if(ori[i] != fin[i]){
                isSame = false;
                break;
            }
        }

        if(!isSame){
            ori = tmp;
            cnt = 1;
            ori[0] = !ori[0];
            ori[1] = !ori[1];
            for (int i = 1; i < n; i++) {
                solution(i);
            }
            isSame = true;
            for (int i = 0; i < n; i++) {
                if(ori[i] != fin[i]){
                    isSame = false;
                    break;
                }
            }
        }
        if(!isSame)
            System.out.println(-1);
        else
            System.out.println(cnt);
    }
}
