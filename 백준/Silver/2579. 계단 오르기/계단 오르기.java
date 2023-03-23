import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] stair = new int[301];
        int[] dp = new int[301];

        for (int i = 0; i < n; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = stair[0];
        dp[1] = dp[0] + stair[1];
        dp[2] = stair[2] + Math.max(stair[0], stair[1]);
        for (int i = 3; i < n; i++) {
            dp[i] = stair[i] + Math.max(dp[i-2], dp[i-3] + stair[i-1]);
        }

        System.out.println(dp[n-1]);
    }
}
