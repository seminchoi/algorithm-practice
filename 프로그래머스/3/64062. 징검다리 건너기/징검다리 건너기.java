class Solution {
    public int solution(int[] stones, int k) {
        int min = 0, max = Integer.MAX_VALUE -1;
        
        while (min < max) {
            int mid = (min + max) / 2;
            if(!goOver(stones, k, mid)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        
        return min - 1;
    }
    
    private boolean goOver(int[] stones, int k, int n) {
        for(int i = 0; i < stones.length - k + 1;) {
            int temp = i;
            boolean pass = false;
            for(int j = i; j < i + k; j++) {
                if(stones[j] >= n) {
                    temp = j;
                    pass = true;
                    break;
                }
            }
            
            if (pass) {
                i = temp + 1;
            } else {
                return false;
            }
        }
        
        return true;
    }
}