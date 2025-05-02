import java.util.*;
import java.io.*;
import java.util.stream.Stream;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static Set<List<Integer>> set = new HashSet<>();
    static int count;

    static int[] dx = {0, 1, 0 , -1}, dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        n = Integer.parseInt(input1[0]);
        m = Integer.parseInt(input1[1]);

        map = new int[n][m];

        for(int i = 0; i < n; i++) {
            int[] input2 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < m; j++) {
                map[i][j] = input2[j];
                if(input2[j] != 0) {
                    set.add(List.of(i, j));
                }
            }
        }

        while(bfs()) {
            count++;
        }

        if(set.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(count);
        }
    }

    private static boolean bfs() {
        if(set.isEmpty()) {
            return false;
        }
        
        var pos = set.iterator().next();
        Queue<List<Integer>> queue = new LinkedList<>();
        var copySet = new HashSet<List<Integer>>(set);
        copySet.remove(pos);
        queue.offer(pos);

        int[][] copyMap = new int[n][m];
        for(int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, copyMap[i], 0, m);
        }

        while(!queue.isEmpty()) {
            var cur = queue.poll();
            int x = cur.get(1);
            int y = cur.get(0);

            for(int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) continue;
                if(copyMap[nextY][nextX] == 0) {
                    if(map[y][x] > 0) {
                        map[y][x]--;
                    }
                    if(map[y][x] == 0) {
                        set.remove(cur);
                    }
                    continue;
                }

                var nextPos = List.of(nextY, nextX);
                if(copySet.contains(nextPos)) {
                    copySet.remove(nextPos);
                    queue.offer(nextPos);
                }
            }
        }

        return copySet.isEmpty();
    }
}