import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static class Node implements Comparable<Node> {
        int number;
        int cost;

        public Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return cost - node.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<Node>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(dest, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(graph, start, dest));
    }

    public static int dijkstra(List<Node>[] graph, int start, int dest) {
        Queue<Node> queue = new PriorityQueue<>();

        int[] cost = new int[graph.length];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;

        queue.offer(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if(cost[cur.number] < cur.cost) {
                continue;
            }

            for (Node node : graph[cur.number]) {
                if(cost[node.number] > cur.cost + node.cost) {
                    cost[node.number] = cur.cost + node.cost;
                    queue.offer(new Node(node.number, cost[node.number]));
                }
            }
        }

        return cost[dest];
    }
}
