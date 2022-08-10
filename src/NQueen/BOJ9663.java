package NQueen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9663 {
    public static boolean[][] board;
    public static int N;
    public static int[] xDif = {0,1,1,1,0,-1,-1,-1}, yDif = {1,1,0,-1,-1,-1,0,1};

    public static boolean[][] makeBoard(boolean[][] thisBoard, int x, int y){
        boolean[][] newBoard = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newBoard[i][j] = thisBoard[i][j];
            }
        }

        newBoard[y][x] = true;
        for (int i = 0; i < 8; i++) {
            int xPos= x, yPos = y;
            xPos += xDif[i]; yPos += yDif[i];
            while(xPos >= 0 && xPos < N && yPos >= 0 && yPos < N){
                newBoard[yPos][xPos] = true;
                xPos += xDif[i]; yPos += yDif[i];
             }
        }
        return newBoard;
    }

    public static int dfs(boolean[][] thisBoard, int depth, int res, int preX, int preY){
//        System.out.println("depth: "+depth + "\tpreX:" + preX + "\tpreY: " + preY+"\tres: " + res);
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(thisBoard[i][j] + " ");
//            }
//            System.out.println();
//        }
        if(depth == N){
            for (int j = preX; j < N; j++) {
                if(!thisBoard[preY][j]){
                    res = res + 1;
                }
            }

            for (int i = preY+1; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(!thisBoard[i][j]){
                        res = res + 1;
                    }
                }
            }
            return res;
        }

        for (int j = preX; j < N; j++) {
            if (!thisBoard[preY][j]) {
                res = dfs(makeBoard(thisBoard, j, preY), depth + 1, res, j+1, preY);
            }
        }

        for (int i = preY+1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!thisBoard[i][j]) {
                    res = dfs(makeBoard(thisBoard, j, i), depth + 1, res, j+1, i);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new boolean[N][N];

        System.out.println(dfs(board,1,0,0,0));
        long end = System.currentTimeMillis();
        System.out.println("SDB에서 노드생성까지의 실행시간 : " + (end - start)/1000.0);
    }
}
