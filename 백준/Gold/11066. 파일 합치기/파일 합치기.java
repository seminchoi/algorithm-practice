import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            int[] data = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                    .toArray();
            solution(k, data);
        }
    }

    static void solution(int k, int[] data) {
        int[][] dp = new int[k][k];

        int[] sum = new int[k+1];
        for (int i = 0; i < k; i++) {
            sum[i+1] = sum[i] + data[i];
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < k; i++) {
            dp[i][i] = 0;
        }

        for(int i = 0; i < k - 1; i++) {
            dp[i][i+1] = data[i] + data[i+1]; 
        }
        
        // dp[j][l] + dp[l+1][j+i]
        for(int i = 2; i < k; i++) {
            for (int j = 0; j < k - i; j++) {
                for (int l = 0; l < i; l++) {
                    dp[j][i+j] = Math.min(dp[j][i+j], dp[j][j+l] + dp[j+l+1][j+i] + sum[j+i+1] - sum[j]);
                }
            }
        }
        
        System.out.println(dp[0][k-1]);
    }
}