class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        return binarySearch(diffs, times, limit);
    }
    
    private int binarySearch(int[] diffs, int[] times, long limit) {
        int min = 1;
        int max = 100_000;
        
        
        //level이 커질수록 푸는 시간이 작아진다.
        //푸는 시간이 limit를 넘지 않는 최소 level을 찾아야한다.
        while(min < max) {
            int level = (min + max) / 2;
            long totalTime = calculateTotalTime(level, diffs, times);
            
            // 5 5 5 5 4 4 1
            // 타겟보다 찾은 값이 크면 min을 mid + 1 해야함
            // 그렇지 않으면 max를 mid로 해야함
            
            if(totalTime > limit) {
                min = level + 1;
            }
            else {
                max = level;
            }
        }
        
        return min;
    }
    
    private int findMaxDiff(int[] diffs) {
        int puzzleCount = diffs.length;
        int maxDiff = 0;
        for(int i = 0; i < puzzleCount; i++) {
            maxDiff = Math.max(maxDiff, diffs[i]);
        }
        return maxDiff;
    }
    
    private long calculateTotalTime(int level, int[] diffs, int[] times) {
        int puzzleCount = diffs.length;
        long totalTime = times[0];
        for(int i = 1; i < puzzleCount; i++) {
            totalTime += solve(level, diffs[i], times[i-1], times[i]);
        }
        return totalTime;
    }
    
    private long solve(int level, int diff, int prevTime, int curTime) {
        if(level >= diff) {
            return curTime;
        }
        return (diff - level) * (prevTime + curTime) + curTime;
    }
}