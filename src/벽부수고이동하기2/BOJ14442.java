package 벽부수고이동하기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14442 {
    static int n,m,k;
    static int[] xPos = {1,0,-1,0}, yPos = {0,1,0,-1};
    static boolean[][] board;
    static int[][]  breakBoard;
    static Queue<int[]> queue = new LinkedList<>();


    static int bfs(){
        while(!queue.isEmpty()){
            int[] curPos = queue.poll();

//            System.out.printf("x: %d  y: %d \n", posInfo.x, posInfo.y );
            if(curPos[3] > k)
                continue;

            if(curPos[0] == m-1 && curPos[1] == n-1)
                return curPos[2];

            for (int i = 0; i < 4; i++) {
                int nextX = curPos[0]+xPos[i], nextY = curPos[1]+yPos[i];
                if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n){
                    int breakDoor = board[nextY][nextX] ? curPos[3] : curPos[3]+1;
                    if(breakBoard[nextY][nextX] > breakDoor) {
                        breakBoard[nextY][nextX] = breakDoor;
                        queue.offer(new int[] {nextX, nextY, curPos[2]+1, breakDoor});
                    }
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new boolean[n][m];
        breakBoard = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j) == '0';
                breakBoard[i][j] = k+1;
            }
        }

        queue.offer(new int[] {0,0,1,0});
        breakBoard[0][0] = 0;

        System.out.println(bfs());

    }
}
