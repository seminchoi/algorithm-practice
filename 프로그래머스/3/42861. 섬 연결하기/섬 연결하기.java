import java.util.*;

class Solution {
    static int cost = 0;
    static int[] parent;
    
    public int solution(int n, int[][] costs) {
        init(n, costs);
        kruskal(n, costs);
        return cost;
    }
    
    private void init(int n, int[][] costs) {
        parent = new int[n];
        
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        Arrays.sort(costs, (c1, c2) -> c1[2] - c2[2]);
    }
    
    private int getParent(int n) {
        if(parent[n] == n) {
            return n;
        }
        parent[n] = getParent(parent[n]);
        return parent[n];
    }
    
    private void kruskal(int n, int[][] costs) {
        for(int[] cost : costs) {
            int x = getParent(cost[0]);
            int y = getParent(cost[1]);
            if(x == y) {
                continue;
            }

            parent[y] = x;
            this.cost += cost[2];
        }
    }
}