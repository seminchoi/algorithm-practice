package 적록색약;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ10026 {
    static int comArea = 0, rgArea = 0, n;
    static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
    static char[][] picture;
    static boolean[][] vis;

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void bfs(int x, int y){
        comArea+=1; rgArea+=1;
        Queue<Pos> q = new LinkedList<>();
        Queue<Pos> subQ = new LinkedList<>();
        q.offer(new Pos(x,y));

        while(!q.isEmpty()){
            Pos cur = q.poll();
//            System.out.printf("%d %d\n",cur.y, cur.x);
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i], nextY = cur.y + dy[i];
                if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n
                        && !vis[nextY][nextX]) {

//                    System.out.println("color = " + color);
//                    System.out.println("picture[nextY][nextX] = " + picture[nextY][nextX]);
                    if(picture[cur.y][cur.x] == picture[nextY][nextX]) {
                        q.offer(new Pos(nextX,nextY));
                        vis[nextY][nextX] = true;
                    }
                    else if((picture[cur.y][cur.x] == 'R' || picture[cur.y][cur.x] == 'G')
                            && (picture[nextY][nextX] == 'R' || picture[nextY][nextX] == 'G')){
                           subQ.offer(new Pos(nextX,nextY));
                    }
                }
            }
        }

        while (!subQ.isEmpty()){
            Pos sub = subQ.poll();
            if(!vis[sub.y][sub.x]){
                vis[sub.y][sub.x] = true;
                rgArea--;
                bfs(sub.x, sub.y);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        picture = new char[n][n];
        vis = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                picture[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!vis[i][j]){
//                    System.out.println("x  = " + i + ",y = " + j);
                    vis[i][j] = true;
                    bfs(j,i);
                }
            }
        }

        System.out.printf("%d %d\n",comArea, rgArea);

    }
}
