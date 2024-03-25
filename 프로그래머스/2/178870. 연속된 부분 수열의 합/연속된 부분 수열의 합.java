class Solution {
    public int[] solution(int[] sequence, int k) {
        return findPartialSequence(sequence,k);
    }
    
    private int[] findPartialSequence(int[] sequence, int k) {
        // indexRange[0] 에는 정답을, indexRange[1]에는 현재 상황을 기록
        int[][] indexRange = new int[2][2];
        indexRange[0][1] = -1;
        
        int sum = 0;
        for(int i = 0; i < sequence.length; i++) {
            sum += sequence[i];
            indexRange[1][1] = i;
            while(sum > k) {
                sum -= sequence[indexRange[1][0]];
                indexRange[1][0]++;
            }
            
            if(sum == k) {
                captureAnswer(indexRange);
            }
        }
        
        return indexRange[0];
    }
    
    private void captureAnswer(int[][] indexRange) {
        int originLenth = indexRange[0][1] - indexRange[0][0];
        int curLength = indexRange[1][1] - indexRange[1][0];
        if(originLenth == -1 || originLenth > curLength) {
            indexRange[0][0] = indexRange[1][0];
            indexRange[0][1] = indexRange[1][1];
        }
    }
}