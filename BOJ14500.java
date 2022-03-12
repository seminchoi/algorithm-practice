import java.util.Scanner;

public class BOJ14500 {

    static int N,M,NM, rtCnt = 0;
    static int[][] board;
    static int max = 0;
    static boolean rev = false;

    static void trm1(){
        if(rtCnt >= 2)
            return;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M - 3; j++) {
                int tmp = 0;
                tmp =board[i][j] +board[i][j+1] +board[i][j+2] +board[i][j+3];
                max = Math.max(max,tmp);
            }
        }
    }

    static void trm2(){
        if(rtCnt >= 1)
            return;
        for(int i = 0; i < N - 1; i++){
            for(int j = 0; j < M - 1; j++) {
                int tmp = 0;
                tmp =board[i][j] +board[i][j+1] +board[i+1][j] +board[i+1][j+1];
                max = Math.max(max,tmp);
            }
        }
    }

    static void trm3(){
        for(int i = 0; i < N - 2; i++){
            for(int j = 0; j < M - 1; j++) {
                int tmp = 0;
                tmp =board[i][j] +board[i+1][j] +board[i+2][j] +board[i+2][j+1];
                max = Math.max(max,tmp);
            }
        }
    }


    static void trm4(){
        for(int i = 0; i < N - 2; i++){
            for(int j = 0; j < M - 1; j++) {
                int tmp = 0;
                tmp =board[i][j] +board[i+1][j] +board[i+1][j+1] +board[i+2][j+1];
                max = Math.max(max,tmp);
            }
        }
    }

    static void trm5(){
        if(rev == true)
            return;
        for(int i = 0; i < N - 1; i++){
            for(int j = 0; j < M - 2; j++) {
                int tmp = 0;
                tmp =board[i][j] +board[i][j+1] +board[i][j+2] +board[i+1][j+1];
                max = Math.max(max,tmp);
            }
        }
    }

    static void boardChk(){
        trm1();trm2();trm3();trm4();trm5();
    }

    static void boardRotation() {
        boardChk();
        rtCnt++;
        int[][] tmpboard = new int[NM][NM];
        int tmp = N; N = M; M =tmp;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmpboard[i][j] = board[M-j-1][i];
            }
        }
        board = tmpboard;
    }

    static void boardRev() {
        int[][] tmpboard = new int[NM][NM];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M ; j++) {
                tmpboard[i][j] = board[i][M-j-1];
            }
        }
        board = tmpboard;
    }

        public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        NM = Math.max(N,M);
        board = new int[NM][NM];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++)
                board[i][j] = sc.nextInt();
        }

        for(int i = 0; i < 4; i++)
            boardRotation();

        boardRev();

        for(int i = 0; i < 4; i++)
            boardRotation();

        System.out.println(max);
    }
}
