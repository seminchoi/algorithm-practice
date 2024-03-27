import java.util.*;

class Solution {
    static class Node {
        int number;
        int weight;
        
        public Node(int number, int weight) {
            this.number = number;
            this.weight = weight;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        List<Node>[] graph = createGraph(N, road);
        return (int) Arrays.stream(calcualteWeight(graph))
            .filter(value -> value <= K)
            .count();
    }
    
    private List<Node>[] createGraph(int N, int[][] road) {
        List<Node>[] graph = new List[N+1];
        for(int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < road.length; i++) {
            graph[road[i][0]].add(new Node(road[i][1], road[i][2]));
            graph[road[i][1]].add(new Node(road[i][0], road[i][2]));
        }
        
        return graph;
    }
    
    private int[] calcualteWeight(List<Node>[] graph) {
        int[] weights = new int[graph.length];
        Arrays.fill(weights, Integer.MAX_VALUE);
        weights[1] = 0;
        Node startNode = new Node(1,0);
        
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return node1.weight - node2.weight;
            }
        });
        
        queue.add(startNode);
        
        while(!queue.isEmpty()) {
            Node curNode = queue.poll();

            for(Node node : graph[curNode.number]) {
                if(curNode.weight + node.weight < weights[node.number]) {
                    weights[node.number] = curNode.weight + node.weight;
                    queue.add(new Node(node.number, weights[node.number]));
                }
            }
        }
        
        return weights;
    }
}