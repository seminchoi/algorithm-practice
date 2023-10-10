class Solution {
    public long solution(int[] weights) {
        int[][] correction = new int[4][4005];
        
        for(int w : weights) {
            for(int i = 0; i < 3; i++) {
                correction[i][w*(i+2)]++;
            }
        }
        
        long answer = 0;
        
        for(int w : weights) {
            if(correction[0][w * 2] > 1) {
                answer += correction[0][w * 2] - 1;
            }
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(i == j) {
                        continue;
                    }
                    answer += correction[i][w * (j + 2)];
                }
            }
        }
        return answer/2;
    }
}