import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for(int i = 1; i < s1.length() + 1; i++) {
            for(int j = 1; j < s2.length() + 1; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1);
                }
            }
        }

        int n = dp[s1.length()][s2.length()];
        System.out.println(n);
        int y = s1.length();
        int x = s2.length();
        Stack<Character> stack = new Stack<>();

        while(n > 0) {
            if(dp[y][x] > dp[y-1][x-1] && s1.charAt(y-1) == s2.charAt(x-1)) {
                stack.push(s1.charAt(y-1));
                x--;
                y--;
                n--;
            }
            else if(dp[y][x] == dp[y-1][x]) {
                y--;
            } else {
                x--;
            }
        }

        StringBuilder answer = new StringBuilder();
        while(!stack.isEmpty()) {
            answer.append(stack.pop());
        }

        System.out.println(answer);

    }
}