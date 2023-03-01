import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[n+1];
        for (int i = 0; i < n+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        for (int i = 1; i < n+1; i++) {
            Collections.sort(graph[i]);
        }

        boolean[] visit = new boolean[n+1];
        StringBuilder sb = new StringBuilder();
        dfs(graph, visit, v, sb);
        sb.append("\n");
        sb.append(bfs(graph,v));

        System.out.println(sb);
    }

    public static void dfs(List<Integer>[] graph, boolean[] visit, int v, StringBuilder sb){
        visit[v] = true;
        sb.append(v).append(" ");
        for(Integer i : graph[v]){
            if(!visit[i])
                dfs(graph, visit, i, sb);
        }
    }

    public static String bfs(List<Integer>[] graph, int v){
        Queue<Integer> queue = new LinkedList<>();
        boolean visit[] = new boolean[graph.length];
        StringBuilder sb = new StringBuilder();
        queue.offer(v);
        visit[v] = true;
        while (!queue.isEmpty()){
            int curV = queue.poll();
            sb.append(curV).append(" ");
            for (Integer nextV : graph[curV]) {
                if(!visit[nextV]){
                    visit[nextV] = true;
                    queue.offer(nextV);
                }
            }
        }
        return sb.toString();
    }
}
