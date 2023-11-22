import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public static List<Node>[] graph;

    public static class Node {
        int id;
        int weight;

        public Node(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }

    public int solution(int N, int[][] road, int K) {
        graph = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < road.length; i++) {
            int[] node = road[i];
            graph[node[0]].add(new Node(node[1], node[2]));
            graph[node[1]].add(new Node(node[0], node[2]));
        }

        int[] distances = dijkstra();
        System.out.println(Arrays.toString(distances));
        return (int) Arrays.stream(distances).filter(distance -> distance <= K).count();
    }

    private int[] dijkstra() {
        int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);

        distances[1] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));
        queue.offer(new Node(1, 0));
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            if(curNode.weight > distances[curNode.id]) {
                continue;
            }

            for (Node nextNode : graph[curNode.id]) {
                if(distances[curNode.id] + nextNode.weight < distances[nextNode.id]) {
                    distances[nextNode.id] = distances[curNode.id] + nextNode.weight;
                    queue.offer(nextNode);
                }
            }
        }

        return distances;
    }
}