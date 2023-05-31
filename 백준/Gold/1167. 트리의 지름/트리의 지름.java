import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static List<Node>[] tree;
    public static boolean[] vis;
    public static int maxWeight;
    public static int x, y;

    public static class Node {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int v = Integer.parseInt(br.readLine());

        tree = new List[v + 1];
        vis = new boolean[v + 1];

        for (int i = 0; i < v + 1; i++) {
            tree[i] = new LinkedList<>();
        }

        for (int i = 0; i < v; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());

            while (true) {
                int node = Integer.parseInt(st.nextToken());
                if (node == -1) {
                    break;
                }
                int weight = Integer.parseInt(st.nextToken());

                tree[s].add(new Node(
                        node,
                        weight
                ));
            }
        }

        dfs(tree[1].get(0).node, 0);

        maxWeight = 0;

        vis = new boolean[v + 1];
        dfs(x, 0);

        System.out.println(maxWeight);
    }

    public static void dfs(int s, int weight) {
        if (vis[s])
            return;
        vis[s] = true;

        if (weight > maxWeight) {
            maxWeight = weight;
            x = s;
        }

        List<Node> nodes = tree[s];

        for (Node node : nodes) {
            if (vis[node.node])
                continue;
            dfs(node.node, weight + node.weight);
        }
    }
}
