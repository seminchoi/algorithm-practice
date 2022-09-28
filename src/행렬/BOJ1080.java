package 행렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1080 {
    static int n, m;
    static boolean[][] origin, res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        origin = new boolean[n][m];
        res = new boolean[n][m];


        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                origin[i][j] = s.charAt(j) == '1';
            }
        }


        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                res[i][j] = s.charAt(j) == '1';
            }
        }

        int cnt = 0;
        if(n >= 3 && m >= 3) {
            for (int i = 0; i < n - 2; i++) {
                for (int j = 0; j < m - 2; j++) {
                    if (origin[i][j] != res[i][j]) {
                        cnt++;
                        for (int k = i; k < i + 3; k++) {
                            for (int l = j; l < j + 3; l++) {
                                origin[k][l] = !origin[k][l];
                            }
                        }
                    }
                }
            }
        }

        boolean isSame = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(origin[i][j] != res[i][j])
                    isSame = false;
            }
        }

        if(isSame)
            System.out.println(cnt);
        else
            System.out.println(-1);
    }
}
