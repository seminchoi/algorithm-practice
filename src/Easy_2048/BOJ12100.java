package Easy_2048;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ12100 {
    static int N, max;
    static int[][] board;
    static Queue<int[][]> q = new LinkedList<>();
    static Queue<Integer> breadth = new LinkedList<>();

    static public void checkMax(int[][] board){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[j][i] > max)
                    max = board[j][i];
            }
        }
    }

    static public int[][] rotationBoard(int[][] board){
        int[][] newBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newBoard[N-j-1][i] = board[i][j];
            }
        }
        return newBoard;
    }

    static public void pressBoard(int[][] board){
        boolean[][] isCombined = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = N-1; j >= 0; j--) {
                max = max < board[i][j] ? board[i][j] : max;
                if(board[j][i] != 0 && j != N-1) {
                    int y = j;
                    while (y < N-1 && board[y+1][i] == 0) {
                        y++;
                    }

                    if(y < N-1 && board[j][i] == board[y+1][i] && !isCombined[y + 1][i]) {
                            board[y + 1][i] *= 2;
//                        board[y+1][i] *= 3;
                            board[j][i] = 0;
                            isCombined[y + 1][i] = true;
                    }
                    else if(y == j){
                        continue;
                    }
                    else{
                        board[y][i] = board[j][i];
                        board[j][i] = 0;
                    }
                }
            }
        }
    }

    static void bfs(){
        while(!q.isEmpty()){
            int[][] board = q.poll();
            int b = breadth.poll();
//            System.out.println(b);
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    System.out.print(board[i][j] + " ");
//                }
//                System.out.println();
//            }
            for (int i = 0; i < 4; i++) {
                int[][] newBoard = rotationBoard(board);
                for (int j = 0; j < N; j++) {
                    board[j] = newBoard[j].clone();
                }
                pressBoard(newBoard);
                if(b == 4){
                    checkMax(newBoard);
                    continue;
                }
                q.offer(newBoard); breadth.offer(b+1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        q.offer(board);
        breadth.offer(0);

        bfs();

        System.out.println(max);
    }
}
