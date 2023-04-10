import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int cnt = 0;
    static List<Integer>[] g;
    static boolean[] vis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int v = Integer.parseInt(br.readLine());
        int e = Integer.parseInt(br.readLine());

        g = new List[v+1];
        vis = new boolean[v+1];

        for (int i = 0; i <= v; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            g[v1].add(v2);
            g[v2].add(v1);
        }

        dfs(1);

        System.out.println(cnt-1);

    }

    public static void dfs(int s){
        if(vis[s])
            return;

        cnt++;
        vis[s] = true;
        List<Integer> v = g[s];

        for (Integer i : v) {
            dfs(i);
        }

    }
}
