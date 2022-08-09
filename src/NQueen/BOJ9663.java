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
        if(depth == N){
            for (int i = preY; i < N; i++) {
                for (int j = preX+1; j < N; j++) {
                    if(!thisBoard[i][j]){
                        res = res + 1;
                    }
                }
            }
            return res;
        }


        for (int i = preY; i < N; i++) {
            for (int j = preX+1; j < N; j++) {
                if (!thisBoard[i][j]) {
                    res = dfs(makeBoard(thisBoard, j, i), depth + 1, res, j, i);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new boolean[N][N];

        System.out.println(dfs(board,1,0,-1,0));

        System.out.println(board);
    }
}
