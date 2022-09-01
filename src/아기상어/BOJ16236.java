package 아기상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16236 {
    //n : 보드의 크기, shark: 상어의 크기
    static int n,shark = 2, eatFood=0, res, detectFoodDepth=100000;
    static int[] food = new int[7], dx={0,-1,1,0}, dy={-1,0,0,1};
    //보드
    static int[][] board;
    static boolean[][] vis;
    //배열 인덱스
    //0: x좌표, 1: y좌표, 2: depth
    static Queue<int[]> queue = new LinkedList<>();
    static Queue<int[]> detectFood = new PriorityQueue(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            if(o1[2] != o2[2]){
                return o1[2] - o2[2];
            }
            else{
                if(o1[1] != o2[1]){
                    return o1[1] - o2[1];
                }
                else {
                    return o1[0] - o2[0];
                }
            }
        }
    });

    static void bfs(){
        while(!queue.isEmpty()){
            //먹은 물고기수가 상어의 크기와 같아지는 순간 상어를 키워줌
            if(eatFood == shark && shark < 7){
                shark++;
                eatFood = 0;
            }
            //먹을 수 있는 물고기가 없으면 반복 종료
            boolean isFood = false;
            for (int i = 1; i <= shark && i < 7; i++) {
                if(food[i] != 0)
                    isFood = true;
            }
            if(!isFood) {
//                System.out.println("no Food");
                break;
            }

            int[] cur = queue.poll();
            if(cur[2] == detectFoodDepth){

                queue.clear();
                res = cur[2];
                eatFood++;

                int[] next = detectFood.poll();
                detectFood.clear();

                queue.offer(next);
                food[board[next[1]][next[0]]]--;
                board[next[1]][next[0]] = 0;
                vis = new boolean[n+2][n+2];
                vis[next[1]][next[0]] = true;
                detectFoodDepth = 100000;
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = cur[0]+dx[i], nextY = cur[1]+dy[i];

                if(!vis[nextY][nextX]) {
                    vis[nextY][nextX] = true;
                    if(board[nextY][nextX] == 0 || board[nextY][nextX] == shark){
                        queue.offer(new int[]{nextX, nextY, cur[2]+1});
                    }
                    else if (board[nextY][nextX] > shark) {
                        continue;
                    }
                    else {
                        detectFoodDepth = cur[2]+1;
//                        System.out.println(detectFoodDepth + " 번째 depth에서 먹이발견!");
//                        System.out.printf("먹이 위치 -> x: %d, y: %d\n",nextX,nextY);
                        detectFood.offer(new int[]{nextX, nextY, cur[2]+1});
                        queue.offer(new int[]{nextX, nextY, cur[2]+1});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        board = new int[n+2][n+2];
        vis = new boolean[n+2][n+2];

        for (int i = 0; i < n+2; i++) {
            board[i][0] = 8;
            board[i][n+1] = 8;
            board[0][i] = 8;
            board[n+1][i] = 8;
        }

        for (int i = 1; i < n+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n+1; j++) {
                int x = Integer.parseInt(st.nextToken());
                board[i][j] = x;
                if(x > 0 && x < 7){
                    food[x]++;
                }
                else if(x == 9){
                    queue.add(new int[] {j,i,0});
                    board[i][j] = 0;
                    vis[i][j] = true;
                }
            }
        }

        bfs();

        System.out.println(res);


    }
}
