package 탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//보드를 저장하는 arrayList 를 생성
//수정: 이전 보드를 저장할 필요가 없음, 큐에서 poll할 때 depth가 변하는 순간을 감지하여 물을 채우면 됨, 즉 list를 만들 필요도 없음
//추가 : curDepth가 올라갈 때 보드를 바꾸므로 비버가 이미 이동한 자리에 물이 덮칠 수 있음. curboard, nextboard를 만들거나 list를 만들거나 덮쳤을때 예외처리를 해줘야함.
//보드가 크지 않아서 보드 arraylist를 만드는게 제일 성능이 좋을것으로 예상
//depth 가 높아지면 보드를 추가
//고슴도치의 위치는 큐에 따로 저장
//고슴도치가 이동하는 경우의 수마다 보드를 안만들어도 되므로 메모리를 아낄수 있음
//이동할 때 마다 상하좌우로 물이 퍼지는 연산도 줄어들음으로 시간도 아낄 수 있음


public class BOJ3055{
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
//                if(board[curPos.y][curPos.x] == '*')
//                    continue;
//                for (int i = 1; i < r+1; i++) {
//                    for (int j = 1; j < c+1; j++) {
//                        System.out.print(board[i][j]);
//                    }
//                    System.out.println();
//                }
//                System.out.println();
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