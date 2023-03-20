import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        graph = new List[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            graph[i] = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int edge = Integer.parseInt(st.nextToken());
                if (edge == 1) {
                    graph[i].add(j);
                }
            }
        }

        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            boolean[] vis = new boolean[n];
            dfs(board, vis, i, i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void dfs(int[][] board, boolean[] vis, int s, int c) {
        if (vis[c]) {
            return;
        }

        vis[c] = true;
        for (Integer i : graph[c]) {
                board[s][i] = 1;
            dfs(board, vis, s, i);
        }
    }
}
