import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input1 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input1[0];
        int weight = input1[1];

        int[] weights = new int[n + 1];
        int[] values = new int[n + 1];

        for(int i = 1; i < n + 1; i++) {
            int[] input2 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            weights[i] = input2[0];
            values[i] = input2[1];
        }

        int[][] dp = new int[weight + 1][n + 1];
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < weight + 1; j++) {
                if(weights[i] <= j) {
                    dp[j][i] = Math.max(dp[j][i-1], dp[j-weights[i]][i-1] + values[i]);
                } else {
                    dp[j][i] = dp[j][i-1];
                }
            }
        }

        System.out.println(dp[weight][n]);
    }
}