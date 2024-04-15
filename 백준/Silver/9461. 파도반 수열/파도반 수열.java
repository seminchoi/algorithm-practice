import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long[] dp = new long[101];
    static int curIndex = 0;

    public static void main(String[] args) throws IOException {
        initDp();

        int n = read();

        for (int i = 0; i < n; i++) {
            int target = read();
            System.out.println(getSideLength(target));
        }
    }

    private static int read() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    private static void initDp() {
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        curIndex = 5;
    }

    private static long getSideLength(int index) {
        for (int i = curIndex + 1; i <= index; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }
        return dp[index];
    }
}
