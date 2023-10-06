import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        int[] dp = new int[y + 1];
        dp[y] = 1;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(y);
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            
            if(cur % 3 == 0 && isValid(dp, x, cur, cur/3)) {
                dp[cur/3] = dp[cur] + 1;
                queue.offer(cur/3);
            }
            if(cur % 2 == 0 && isValid(dp, x, cur, cur/2)) {
                dp[cur/2] = dp[cur] + 1;
                queue.offer(cur/2);
            } 
            if(isValid(dp, x, cur, cur - n)) {
                dp[cur - n] = dp[cur] + 1;
                queue.offer(cur - n);
            }
        }
        return dp[x] == 0 ? -1 : dp[x] - 1;
    }
    
    public boolean isValid(int[] dp, int x, int cur, int next) {
        return next >= x && (dp[next] == 0 || dp[cur] + 1 < dp[next]);
    }
}