import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Edge {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = end;
            this.end = start;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        int[] nodeEdge = read();
        int v = nodeEdge[0];
        int e = nodeEdge[1];

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            int[] edge = read();
            edges.add(new Edge(edge[0], edge[1], edge[2]));
        }

        System.out.println(mst(v, edges));
    }

    private static int mst(int v, List<Edge> edges) {
        edges.sort(Comparator.comparingInt(e -> e.weight));

        int[] parent = new int[v + 1];
        for (int i = 0; i < v + 1; i++) {
            parent[i] = i;
        }

        int length = 0;
        int count = 0;
        for (Edge edge : edges) {
            if(unionRoot(parent, edge)) {
                length += edge.weight;
                count++;
            }

            if(count == v - 1) {
                break;
            }
        }

        return length;
    }

    private static boolean unionRoot(int[] parent, Edge edge) {
        int x = findRoot(parent, edge.start);
        int y = findRoot(parent, edge.end);

        if (x != y) {
            parent[y] = x;
            return true;
        }

        return false;
    }


    private static int findRoot(int[] parent, int vertex) {
        if (parent[vertex] == vertex) {
            return vertex;
        }
        return parent[vertex] = findRoot(parent, parent[vertex]);
    }

    private static int[] read() throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
