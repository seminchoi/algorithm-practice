import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        process(br);
    }

    public static void process(BufferedReader br) throws IOException {
        int n = 0;
        int p = 1;

        while (true) {
            n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            int[][] map = new int[n][n];
            for(int i = 0; i < n; i++) {
                int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                System.arraycopy(input, 0, map[i], 0, n);
            }
            bfs(n, map, p);
            p++;
        }

    }

    public static void bfs(int n, int[][] map, int p) {
        int[][] dp = new int[n][n];
        for(int[] row: dp) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(n2 -> n2[2]));
        queue.add(new int[] {0, 0, map[0][0]});
        dp[0][0] = map[0][0];

        while(!queue.isEmpty()) {
            var cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            if(cur[2] > dp[y][x]) {
                continue;
            }
            for(int i = 0; i < 4; i++) {
                int nextY = y + dy[i];
                int nextX = x + dx[i];

                if(nextY < 0 || nextY >= n || nextX < 0 || nextX >= n) continue;
                int weight = dp[y][x] + map[nextY][nextX];
                if(dp[nextY][nextX] == -1 || dp[nextY][nextX] > weight) {
                    dp[nextY][nextX] = weight;
                    queue.offer(new int[] {nextY, nextX, weight});
                }
            }
        }

        System.out.printf("Problem %d: %d\n", p, dp[n-1][n-1]);
    }
}