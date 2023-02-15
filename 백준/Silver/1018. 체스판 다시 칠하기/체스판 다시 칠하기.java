import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String case1 = "BWBWBWBW", case2 = "WBWBWBWB";

        char[][] board = new char[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j);
            }
        }
        int cnt1 = 0, cnt2 = 0, res = Integer.MAX_VALUE;
        for (int i = 0; i < n-7; i++) {
            for (int j = 0; j < m-7; j++) {
                cnt1 = 0; cnt2 = 0;
                for (int k = i; k < i+8; k++) {
                    for (int l = j; l < j+8; l++) {
                        if(k % 2 == 0){
                            if(case1.charAt(l-j) != board[k][l]){
                                cnt1++;
                            }
                            if(case2.charAt(l-j) != board[k][l]){
                                cnt2++;
                            }
                        }
                        else{
                            if(case2.charAt(l-j) != board[k][l]){
                                cnt1++;
                            }
                            if(case1.charAt(l-j) != board[k][l]){
                                cnt2++;
                            }
                        }
                    }
                }
                int min = Math.min(cnt1,cnt2);
                res = Math.min(min,res);
            }
        }
        System.out.println(res);

    }
}
