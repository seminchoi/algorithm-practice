import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main{
    static int r,c,curD=0;
    static int[] dx= {1,0,-1,0}, dy={0,1,0,-1};
    static Pos dPos = new Pos();
    static char[][] board;
    static boolean[][] vis;
    static Queue<Pos> queue = new LinkedList<>();

    static class Pos {
        int x;
        int y;
        int depth;

        public Pos() {
        }

        public Pos(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    public static void bfs(){
        while (!queue.isEmpty()){
            Pos curPos = queue.poll();
            if(curPos.x == dPos.x && curPos.y == dPos.y){
                dPos.depth = curPos.depth;
                break;
            }
            if(curD == curPos.depth){
                curD++;
                waterfall();
            }
            for (int i = 0; i < 4; i++) {
                int nextX = curPos.x+dx[i], nextY = curPos.y+dy[i];
                if(board[nextY][nextX] != '*' && board[nextY][nextX] != 'X' && !vis[nextY][nextX]){
                    queue.add(new Pos(nextX,nextY,curPos.depth+1));
                    vis[nextY][nextX] = true;
                }
            }
        }
    }

    private static void waterfall() {
        for (int i = 1; i < r+1; i++) {
            for (int j = 1; j < c+1; j++) {
                if(board[i][j] == '*'){
                    for (int k = 0; k < 4; k++) {
                        int nextX = j+dx[k], nextY = i+dy[k];
                        if(board[nextY][nextX] != '*' && board[nextY][nextX] != 'X' && board[nextY][nextX] != 'D'){
                            board[nextY][nextX] = 'n';
                        }
                    }
                }
            }
        }
        for (int i = 1; i < r+1; i++) {
            for (int j = 1; j < c+1; j++) {
                if(board[i][j] == 'n'){
                    board[i][j] = '*';
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r+2][c+2];
        vis = new boolean[r+2][c+2];

        Pos sPos = new Pos();
        for (int i = 0; i < r+2; i++) {
            board[i][0] = 'X';
            board[i][c+1] = 'X';
        }

        for (int i = 0; i < c+2; i++) {
            board[0][i] = 'X';
            board[r+1][i] = 'X';
        }

        for (int i = 1; i < r+1; i++) {
            String s = br.readLine();
            for (int j = 1; j < c+1; j++) {
                board[i][j] = s.charAt(j-1);
                if(s.charAt(j-1)=='D') {
                    dPos.x = j;
                    dPos.y = i;
                }
                if(s.charAt(j-1)=='S'){
                    sPos.x=j;
                    sPos.y=i;
                    vis[i][j] = true;
                }
            }
        }
        sPos.depth=0;

        queue.offer(sPos);

        bfs();

        if(dPos.depth == 0)
            System.out.println("KAKTUS");
        else
            System.out.println(dPos.depth);
    }
}