package 뱀과사다리게임;

import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BOJ16928 {
    static int[] board = new int[101], dynamic = new int[101];
    static Queue<Integer>[] queues = new Queue[2];

    static int bfs(){
        while(queues[0].peek() != 100){
            int pos = queues[0].poll(), breadth = queues[1].poll();
            dynamic[pos] = breadth;
//            System.out.println(pos);
            for (int i = 1; i <= 6 && pos + i < 101; i++) {
                int nextPos = pos;
                if(board[pos+i] != 0){
                    nextPos = board[pos+i];
                }
                else
                    nextPos += i;

                if(dynamic[nextPos] != 0 && dynamic[nextPos] <= breadth){
//                    System.out.println(dynamic[nextPos]);
                    continue;}
                queues[0].offer(nextPos);
                queues[1].offer(breadth+1);
            }
        }
        return queues[1].poll()-1;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        queues[0] = new LinkedList<>();
        queues[1] = new LinkedList<>();

        queues[0].offer(1);
        queues[1].offer(1);

        System.out.println(bfs());
    }
}
