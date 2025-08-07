import java.util.*;

class Solution {
    private static List<Node>[] graph;
    public static class Node {
        int value;
        int cost;
        
        public Node(int value, int cost) {
            this.value = value;
            this.cost = cost;
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        makeGraph(n, fares);
        int[] baseCost = dijkstra(s);
        int[] aCost = dijkstra(a);
        int[] bCost = dijkstra(b);
        int cost = baseCost[a] + baseCost[b];
        
        for(int i = 1; i <= n; i++) {
            cost = Math.min(cost, baseCost[i] + aCost[i] + bCost[i]);
        }
        

        return cost;
    }
    
    private void makeGraph(int n, int[][] fares) {
        graph = new List[n+1];
        for(int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] fare : fares) {
            int src = fare[0];
            int dst = fare[1];
            int cost = fare[2];
            graph[src].add(new Node(dst, cost));
            graph[dst].add(new Node(src, cost));
        }
    }
    
    private int[] dijkstra(int src) {
        int[] costs = new int[graph.length];
        Arrays.fill(costs, Integer.MAX_VALUE);
        
        costs[src] = 0;
        
        Queue<Node> queue = new PriorityQueue<>((n1, n2) -> {
            return n1.cost - n2.cost;
        });
        
        queue.offer(new Node(src, 0));
        
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            
            for(Node next : graph[cur.value]) {
                int nextCost = cur.cost + next.cost;
                if(nextCost >= costs[next.value]) {
                    continue;
                }
                Node nextCostNode = new Node(next.value, nextCost);
                costs[next.value] = nextCost;
                queue.offer(nextCostNode);
            }
        }
        
        return costs;
    }
}