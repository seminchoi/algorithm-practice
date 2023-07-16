import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static List<Node>[] graph;
    public static int n, e;

    public static class Node implements Comparable<Node> {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return weight - node.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new List[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[n1].add(new Node(n2, w));
            graph[n2].add(new Node(n1, w));
        }

        st = new StringTokenizer(br.readLine());

        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());


        int[] startNode1 = dik(1);
        int[] startV1 = dik(v1);
        int[] startV2 = dik(v2);

        int result1 = startNode1[v1] * startV1[v2] * startV2[n] < 0 ? Integer.MAX_VALUE : startNode1[v1] + startV1[v2] + startV2[n];
        int result2 = startNode1[v2] * startV2[v1] * startV1[n] < 0 ? Integer.MAX_VALUE : startNode1[v2] + startV2[v1] + startV1[n];

        if(result1 == Integer.MAX_VALUE && result2 == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else
            System.out.println(Math.min(result1, result2));
}

    public static int[] dik(int s) {
        PriorityQueue<Node> queue = new PriorityQueue<>();

        int[] weight = new int[n + 1];
        Arrays.fill(weight,-1);

        queue.offer(new Node(s, 0));
        weight[s] = 0;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            if(curNode.weight > weight[curNode.node]){
                continue;
            }
            List<Node> nodes = graph[curNode.node];

            for (Node node : nodes) {
                if (node.node != s && weight[node.node] == -1 || weight[node.node] > node.weight + curNode.weight) {
                    weight[node.node] = node.weight + curNode.weight;
                    queue.offer(new Node(node.node, weight[node.node]));
                }
            }
        }

        return weight;
    }
}
