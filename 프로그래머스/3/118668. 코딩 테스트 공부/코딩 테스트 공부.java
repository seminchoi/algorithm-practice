import java.util.*;

class Solution {
        public int solution(int alp, int cop, int[][] problems) {
            int maxAlp = 0, maxCop = 0;
            for(int[] p : problems){
                maxAlp = Math.max(maxAlp,p[0]);
                maxCop = Math.max(maxCop, p[1]);
            }

            int[][] dp = new int[maxAlp+2][maxCop+2];
            for(int i = 0; i <= maxAlp; i++){
                for(int j = 0; j <= maxCop; j++){
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
            alp = Math.min(alp, maxAlp);
            cop = Math.min(cop, maxCop);

            dp[alp][cop] = 0;

            for (int i = alp; i <= maxAlp; i++) {
                for (int j = cop; j <= maxCop; j++) {
                    dp[i+1][j] = Math.min(dp[i+1][j],dp[i][j]+1);
                    dp[i][j+1] = Math.min(dp[i][j+1],dp[i][j]+1);

                    //0: 필요 알고, 1: 필요 코딩, 2: 증가 알고, 3: 증가 코딩, 4: 소요시간
                    for(int[] p : problems){
                        if(p[0] <= i && p[1] <= j){
                            int nextAlp = Math.min(maxAlp, i+p[2]), 
                            nextCop = Math.min(maxCop, j+p[3]);
                            dp[nextAlp][nextCop] 
                                = Math.min(dp[nextAlp][nextCop], dp[i][j]+p[4]);
                        }
                    }
                }
            }
            int answer = dp[maxAlp][maxCop];
            return answer;
        }
    }
