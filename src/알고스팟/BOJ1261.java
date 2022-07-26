package 알고스팟;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class BOJ1261 {

    static int[] x = {1, 0, -1, 0}, y = {0, 1, 0, -1};
    static int[][] maze, vis_door;

    static Queue<Pos> q = new LinkedList<>();

    public static class Pos{
        int x; //x좌표
        int y; //y좌표
        int br; //부순 문의 개수
        Pos(int x, int y, int br){
            this.x = x;
            this.y = y;
            this.br = br;
        }
    }

    static int bfs(int N, int M){
        while(!q.isEmpty()){
            Pos pos =q.poll(); //객체의 주소를 전달하기때문에 생기는 문제는?

//            System.out.println(pos.x +" "+pos.y+" "+pos.br + " /n");

            pos.br += maze[pos.y][pos.x];
            if(vis_door[pos.y][pos.x] != -1 && vis_door[pos.y][pos.x] <= pos.br) //방문한 적이 있고 현재 경로에서 부순 문의 개수가 더 많거나 같으면 continue
                continue;
            else if(pos.x == M-1 && pos.y == N-1){ //위의 조건을 통과했는데 도착점이라면 도착점의 부순문의 개수를 갱신
                vis_door[N-1][M-1] = pos.br;
                continue;
            }
            vis_door[pos.y][pos.x] = pos.br;

//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < M; j++) {
//                    System.out.print(vis_door[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();

            for(int i = 0; i < 4; i++){
                Pos new_pos = new Pos(pos.x+x[i],pos.y+y[i],pos.br);
                if(new_pos.x < 0 || new_pos.x >= M || new_pos.y < 0 || new_pos.y >= N)
                    continue;
                q.offer(new_pos);
            }
        }
        return vis_door[N-1][M-1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());


        maze = new int[N][M];
        vis_door = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                maze[i][j] = s.charAt(j) - '0';
            }
        }

        q.offer(new Pos(0,0,0));

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                vis_door[i][j] = -1;
            }
        }

        System.out.println(bfs(N,M));

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(vis_door[i][j] + " ");
//            }
//            System.out.println();
//        }

    }
}
