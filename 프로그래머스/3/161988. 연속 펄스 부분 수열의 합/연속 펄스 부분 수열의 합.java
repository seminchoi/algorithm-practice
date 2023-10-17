class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        long[][] dp = new long[2][sequence.length];
        
        dp[0][0] = sequence[0] * 1;
        dp[1][0] = sequence[0] * -1;
        
        for(int i = 1, pulse = -1; i < sequence.length; i++, pulse *= -1) {
            dp[0][i] = dp[0][i-1] + sequence[i] * pulse;
            dp[1][i] = dp[1][i-1] + sequence[i] * pulse * -1;
        }
        
        long min0 = 0L, min1 = 0L, max0 = -1000000, max1 = -1000000;
        
        for(int i = 0; i < sequence.length; i++) {
            max0 = Math.max(max0, dp[0][i] - min0);
            min0 = Math.min(min0, dp[0][i]);
            
            max1 = Math.max(max1, dp[1][i] - min1);
            min1 = Math.min(min1, dp[1][i]);
        }
            
        return Math.max(max0, max1);
    }
}