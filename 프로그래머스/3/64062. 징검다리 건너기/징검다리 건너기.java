class Solution {
    public int solution(int[] stones, int k) {
        // k칸 안에 가장 큰 걸 찾는다
        // 그 다음 k칸 안에 가장 큰거
        
        int totalMin = Integer.MAX_VALUE;
        int[] maxs = new int[stones.length];
        
        maxs[stones.length - 1] = stones[stones.length - 1];
        for(int i = stones.length - 2; i >= 0; i--) {
            maxs[i] = Math.max(maxs[i+1], stones[i]);
        }
        
        for(int i = 0; i < stones.length - k + 1 ;) {
            int max = stones[i];
            int idx = i;
            if(max != maxs[i]) {
                for(int j = 1; j < k && i + j < stones.length ; j++) {
                    if(max <= stones[i + j]) {
                        max = stones[i + j];
                        idx = i + j;
                    }
                }
            }
            totalMin = Math.min(totalMin, max);
            i = idx + 1;
        }
        
        return totalMin;
    }
}