import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[n][n];
        boolean[][] vis = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j) == '1';
            }
        }

        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};

        List<Integer> complex = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!map[i][j] || vis[i][j])
                    continue;

                int cnt = 1;
                Queue<Integer> xQ = new LinkedList<>();
                Queue<Integer> yQ = new LinkedList<>();

                if(!vis[i][j]) {
                    xQ.add(j);
                    yQ.add(i);
                    vis[i][j] = true;
                }

                while (!xQ.isEmpty()){
                    int curX = xQ.poll(); int curY = yQ.poll();
                    for (int k = 0; k < 4; k++) {
                        int nextX = curX + dx[k];
                        int nextY = curY + dy[k];

                        if(nextY >= 0 && nextY < n && nextX >= 0 && nextX < n && !vis[nextY][nextX] && map[nextY][nextX]){
                            xQ.offer(nextX);
                            yQ.offer(nextY);
                            vis[nextY][nextX] = true;
                            cnt++;
                        }
                    }
                }

                complex.add(cnt);
            }
        }

        complex.sort(Comparator.naturalOrder());

        System.out.println(complex.size());

        for (Integer integer : complex) {
            System.out.println(integer);
        }
    }
}
