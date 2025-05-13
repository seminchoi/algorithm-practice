import java.util.*;

class Solution {
    boolean[] vis;
    List<Integer>[] graph;
    int diff = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        graph = new List[n + 1];
        
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] wire : wires) {
            graph[wire[0]].add(wire[1]);
            graph[wire[1]].add(wire[0]);
        }
        
        for(int i = 0; i < n - 1; i++) {
            vis = new boolean[n + 1];
            cut(n, wires[i][0], wires[i][1]);
        }
        
        return diff;
    }
    
    private void cut(int n, int cutStart, int cutEnd) {
        vis[1] = true;
        int cnt = dfs(cutStart, cutEnd, 1, 0);
        
        diff = Math.min(diff, Math.abs(cnt - (n - cnt)));
    }
    
    private int dfs(int cutStart, int cutEnd, int cur, int cnt) {
        cnt++;
        
        for(Integer next: graph[cur]) {
            if(vis[next]) continue;
            if((cur == cutStart && next == cutEnd) || (cur == cutEnd && next == cutStart))    continue;
            vis[next] = true;
            cnt = dfs(cutStart, cutEnd, next, cnt);
        }
        return cnt;
    }
}