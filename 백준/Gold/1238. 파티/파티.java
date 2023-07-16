import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<NodeInfo>[] nodeInfos;
    static List<NodeInfo>[] reverseNodeInfos;

    public static class NodeInfo {
        int node;
        int weight;

        public NodeInfo(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());


        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        nodeInfos = new List[n + 1];
        reverseNodeInfos = new List[n + 1];


        for (int i = 0; i < n + 1; i++) {
            nodeInfos[i] = new ArrayList<>();
            reverseNodeInfos[i] = new ArrayList<>();
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nodeInfos[s].add(new NodeInfo(d, w));
            reverseNodeInfos[d].add(new NodeInfo(s, w));
        }

        int[] to = new int[n + 1];
        bfs(nodeInfos, x, to);

        int[] from = new int[n + 1];
        bfs(reverseNodeInfos, x, from);

        int max = 0;
        for (int i = 1; i < n + 1; i++) {
            max = Math.max(to[i] + from[i], max);
        }

        System.out.println(max);
    }


    public static int[] bfs(List<NodeInfo>[] graph, int x, int[] weightInfo) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);

        while (!queue.isEmpty()) {
            Integer cur = queue.poll();

            List<NodeInfo> curNodeInfos = graph[cur];

            for (NodeInfo nodeInfo : curNodeInfos) {
                if (nodeInfo.node != x && (weightInfo[nodeInfo.node] == 0
                        || weightInfo[nodeInfo.node] > weightInfo[cur] + nodeInfo.weight)) {
                    weightInfo[nodeInfo.node] = weightInfo[cur] + nodeInfo.weight;
                    queue.offer(nodeInfo.node);
                }
            }
        }
        return weightInfo;
    }
}
