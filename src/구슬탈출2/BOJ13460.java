package 구슬탈출2;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13460 {
    static int[][] board;
    static int N, M, Rx, Ry, Bx, By, Zx, Zy, res;
    static int[] xPos = {1,0,-1,0}, yPos = {0,1,0,-1};
    static Queue<Integer>[] queue = new Queue[5];

    public static void addQueue(int Rx,int Ry,int Bx,int By, int depth){
        queue[0].add(Rx); queue[1].add(Ry);
        queue[2].add(Bx); queue[3].add(By);
        queue[4].add(depth);
    }

    public static void bfs(){
        while(!queue[0].isEmpty()){
            int curRx = queue[0].poll(), curRy = queue[1].poll(),
                    curBx = queue[2].poll(), curBy = queue[3].poll(), depth = queue[4].poll();

//            System.out.printf("rx: %d ry: %d bx: %d by: %d depth: %d \n", curRx, curRy,curBx,curBy, depth);

            if(depth == 11) {
                res = -1;
                return;
            }
            else if(curRx == Zx && curRy == Zy){
                res = depth;
                return;
            }

            for (int i = 0; i < 4; i++) {
                boolean bhole = false;

                int move_R = 0, move_B = 0;
                int nextRx = curRx, nextRy = curRy, nextBx = curBx, nextBy = curBy;

                while(board[nextRy+yPos[i]][nextRx+xPos[i]] != '#') {
                    nextRx += xPos[i];
                    nextRy += yPos[i];
                    move_R++;
                    if(board[nextRy][nextRx] == 'O')
                        break;
                }

                while(board[nextBy+yPos[i]][nextBx+xPos[i]] != '#') {
                    nextBx += xPos[i];
                    nextBy += yPos[i];
                    move_B++;
                    if(board[nextBy][nextBx] == 'O') {
                        bhole = true;
                        break;
                    }
                }

                if(bhole)
                    continue;

                if(nextRx == nextBx && nextRy == nextBy) {
                    if(move_B > move_R){
                        nextBx -= xPos[i]; nextBy -= yPos[i];
                    }
                    else{
                        nextRx -= xPos[i]; nextRy -= yPos[i];
                    }
                }

                if(nextRx != curRx || nextBx != curBx || nextRy != curRy || nextBy != curBy){
                    addQueue(nextRx,nextRy,nextBx,nextBy,depth+1);
                }

            }

        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String ex = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = ex.charAt(j);
                if(ex.charAt(j) == 'B'){
                    Bx = j; By = i;
                }
                else if(ex.charAt(j) == 'R'){
                    Rx = j; Ry = i;
                }
                if(ex.charAt(j) == 'O'){
                    Zx = j; Zy = i;
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            queue[i] = new LinkedList<>();
        }

        addQueue(Rx,Ry,Bx,By,0);

        bfs();
        res = res == 0 ? -1 : res;
        System.out.println(res);

    }
}
