package 데스나이트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16948 {

    static int N;
    static int[] xPos = {-2,-2,0,0,2,2}, yPos = {-1,+1,-2,2,-1,1};
    static boolean[][] board;
    static Queue<PosAndBreadth> queues = new LinkedList<>();

    static class PosAndBreadth{
        public int x;
        public int y;
        public int breadth;

        PosAndBreadth(int x, int y, int breadth){
            this.x = x;
            this.y = y;
            this.breadth = breadth;
        }
    }

    public static int bfs(PosAndBreadth end){
        while(!queues.isEmpty()){
            if(queues.peek().x == end.x && queues.peek().y == end.y){
                return queues.poll().breadth;
            }
            PosAndBreadth curPos = queues.poll();
            for (int i = 0; i < 6; i++) {
                PosAndBreadth nextPos = new PosAndBreadth(curPos.x+xPos[i],
                        curPos.y+yPos[i],
                        curPos.breadth+1);
                if(nextPos.x >= 0 && nextPos.x < N &&
                        nextPos.y >= 0 && nextPos.y < N &&
                        !board[nextPos.y][nextPos.x]){
                    board[nextPos.y][nextPos.x] = true;
                    queues.offer(nextPos);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        board = new boolean[N][N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        PosAndBreadth start = new PosAndBreadth(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                0);
        board[start.y][start.x] = true;

        PosAndBreadth end = new PosAndBreadth(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                0);

        queues.offer(start);

        System.out.println(bfs(end));

    }

}
