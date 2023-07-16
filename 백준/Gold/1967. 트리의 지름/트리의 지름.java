import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static List[] tree;
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

        int n = Integer.parseInt(br.readLine());

        tree = new List[n + 1];
        vis = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++) {
            tree[i] = new LinkedList<Node>();
        }

        for (int i = 0; i < n - 1; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());

            int node = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree[s].add(new Node(
                    node,
                    weight
            ));

            tree[node].add(new Node(
                    s,
                    weight
            ));
        }

        dfs(1, 0);

        maxWeight = 0;

        vis = new boolean[n + 1];
        dfs(x, 0);

        System.out.println(maxWeight);
    }

    public static void dfs(int s, int weight) {
        vis[s] = true;

        if (weight > maxWeight) {
            maxWeight = weight;
            x = s;
        }

        List<Node> nodes = tree[s];

        for (Node node : nodes) {
            if(vis[node.node])
                continue;
            dfs(node.node, weight + node.weight);
        }
    }
}
