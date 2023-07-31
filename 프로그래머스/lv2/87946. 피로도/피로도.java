class Solution {
    int dungeonCount;
    int maxDepth;
    
    
    public int solution(int k, int[][] dungeons) {
        dungeonCount = dungeons.length;
        boolean[] vis = new boolean[dungeonCount];
        
        return backTrack(dungeons, 0, k, vis);
    }
    
    public int backTrack(int[][] dungeons, int depth, int remain, boolean[] vis) {
        int maxDepth = 0;
        // if(depth == dungeonCount) {
        //     return depth;
        // }
        for(int i = 0; i < dungeonCount; i++) {
            if(!vis[i] && dungeons[i][0] <= remain) {
                vis[i] = true;
                maxDepth = Math.max(maxDepth, backTrack(dungeons, depth+1, remain - dungeons[i][1], vis));
                vis[i] = false;
            }
        }
        return Math.max(depth, maxDepth);
    }
}