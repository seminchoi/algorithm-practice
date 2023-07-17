class Solution {
    public int solution(int n) {
        
        long[] dp = new long[100001];
        long[] accumulate = new long[100000];
        int MOD = 1000000007;
        int[] newPattern = {4,2,2};
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = 23;
        dp[5] = 62;
        dp[6] = 170;

        
        accumulate[1] = 2;
        accumulate[2] = 8;



        for(int i = 7; i <= n; i++) {
            long partSum = (dp[i-4]*2 + dp[i-5]*2 + dp[i-6]*4) % MOD;
            dp[i] = (dp[i-1] + dp[i-2]*2 + dp[i-3]*5 
                + partSum + accumulate[i-7] + newPattern[i%3]) % MOD;
            
            accumulate[i-4] = (partSum + accumulate[i-7]) % MOD ;
        }
        
        return (int)dp[n];
    }
}