import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        for(int i = 1; i < n + 1; i++) {
            String[] t = br.readLine().split(" ");
            int period = Integer.parseInt(t[0]);
            int pay = Integer.parseInt(t[1]);
            int endDate = i + period - 1;
            dp[i] = Math.max(dp[i-1], dp[i]);
            if(endDate <= n) {
                dp[endDate] = Math.max(dp[i-1] + pay, dp[endDate]);
            }
        }

        System.out.println(dp[n]);
    }
}