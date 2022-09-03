package 레이저통신;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6087 {
    static int w,h;
    static int[][] vis;
    static char[][] board;
    static int[] dx={1,-1,0,0}, dy={0,0,1,-1};
    static Queue<Pos> queue = new LinkedList<>();
    static Pos sPos, dPos;

    public static class Pos{
        int x;
        int y;

        public Pos() {
        }

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(){
        while(!queue.isEmpty()){
            if(vis[dPos.y][dPos.x] > 0){
                return;
            }
            Pos curPos = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = curPos.x, nextY = curPos.y , depth = vis[curPos.y][curPos.x]+1;
                while (true) {
                    nextX += dx[i];
                    nextY += dy[i];

                    if(nextX >= 0 && nextX < w && nextY >= 0 && nextY < h) {
                        if (board[nextY][nextX] == '*')
                            break;
                        else if (vis[nextY][nextX] == 0 || vis[nextY][nextX] > depth) {
                            vis[nextY][nextX] = depth;
                            queue.offer(new Pos(nextX, nextY));
                        }
                    }
                    else
                        break;
                }
            }
//            for (int i = 0; i < h; i++) {
//                for (int j = 0; j < w; j++) {
//                    System.out.print(vis[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        board = new char[h][w];
        vis = new int[h][w];


        sPos = null;
        dPos = null;

        for (int i = 0; i < h; i++) {
            String s = br.readLine();
            for (int j = 0; j < w; j++) {
                board[i][j] = s.charAt(j);
                if(s.charAt(j) == 'C'){
                    if(sPos == null) {
                        sPos = new Pos(j,i);
                        vis[i][j] = 1;
                    }
                    else
                        dPos = new Pos(j,i);
                    board[i][j] = '.';
                }
            }
        }
        queue.offer(sPos);

        bfs();
        System.out.println(vis[dPos.y][dPos.x]-1);

    }
}
