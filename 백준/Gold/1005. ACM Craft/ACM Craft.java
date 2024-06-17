import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static List<Integer>[] graph;
    static List<Integer>[] reverseGraph;
    static int[] costs;
    static int targetNumber;
    static int nodeCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int gameCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < gameCount; i++) {
            init(br);
            playGame();
        }
    }

    private static void init(BufferedReader br) throws IOException {
        String[] nodeAndEdgeCountInput = br.readLine().split(" ");
        int node = Integer.parseInt(nodeAndEdgeCountInput[0]);
        int edge = Integer.parseInt(nodeAndEdgeCountInput[1]);
        nodeCount = node;

        graph = new List[node + 1];
        reverseGraph = new List[node + 1];
        costs = new int[node + 1];
        for (int i = 0; i <= node; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        String[] costsInput = br.readLine().split(" ");
        for (int i = 0; i < node; i++) {
            costs[i + 1] = Integer.parseInt(costsInput[i]);
        }

        for (int i = 0; i < edge; i++) {
            String[] edgeInput = br.readLine().split(" ");
            int start = Integer.parseInt(edgeInput[0]);
            int end = Integer.parseInt(edgeInput[1]);

            graph[start].add(end);
            reverseGraph[end].add(start);
        }

        targetNumber = Integer.parseInt(br.readLine());
    }

    private static void playGame() {
        List<Integer> sortedNodes = doTopologicalSort();
        int[] times = new int[nodeCount + 1];

        for(int curNodeNumber : sortedNodes) {
            int maxTime = 0;
            for (int connectedNode : reverseGraph[curNodeNumber]) {
                maxTime = Math.max(times[connectedNode], maxTime);
            }

            times[curNodeNumber] = maxTime + costs[curNodeNumber];
        }

        System.out.println(times[targetNumber]);
    }

    private static List<Integer> doTopologicalSort() {
        int[] degrees = calculateDegrees();
        boolean[] vis = new boolean[nodeCount + 1];

        List<Integer> sortedNodes = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        while (sortedNodes.size() < nodeCount) {
            findStartNode(queue, degrees, vis);
            while (!queue.isEmpty()) {
                int curNodeNumber = queue.poll();
                for (int node : graph[curNodeNumber]) {
                    degrees[node]--;
                }
                sortedNodes.add(curNodeNumber);
            }
        }

        return sortedNodes;
    }

    private static int[] calculateDegrees() {
        int[] degrees = new int[nodeCount + 1];
        for (int i = 1; i <= nodeCount; i++) {
            degrees[i] = reverseGraph[i].size();
        }
        return degrees;
    }

    private static void findStartNode(Queue<Integer> queue, int[] degrees, boolean[] vis) {
        for (int i = 1; i <= nodeCount; i++) {
            if (degrees[i] == 0 && !vis[i]) {
                queue.add(i);
                vis[i] = true;
            }
        }
    }
}
