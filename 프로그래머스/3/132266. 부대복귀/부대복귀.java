import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {

        var graph = initGraph(n, roads);
        var dp = bfs(graph, destination);
        var answer = giveAnswer(dp, sources);
        return answer;
    }
    
    public List<Integer>[] initGraph(int n, int[][] roads) {
        List<Integer>[] graph = new List[n + 1];
        
        for(int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] road: roads) {
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }
        
        return graph;
    }
    
    public int[] bfs(List<Integer>[] graph, int destination) {
        int[] dp = new int[graph.length];
        Arrays.fill(dp, -1);
        dp[destination] = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(destination);
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int next: graph[cur]) {
                if(dp[next] == -1) {
                    dp[next] = dp[cur] + 1;
                    queue.offer(next);
                }
            }
        }
        
        return dp;
    }
    
    public int[] giveAnswer(int[] dp, int[] sources) {
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++) {
            answer[i] = dp[sources[i]];
        }
        
        return answer;
    }
}