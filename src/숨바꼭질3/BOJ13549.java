package 숨바꼭질3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ13549 {
    static int N, K;
    static Queue<Integer>[] q = new Queue[2];

    public static int bfs(){
        int[] vis_time = new int[100001];

        while(!q[0].isEmpty()){
            int pos = q[0].poll();
            int time = q[1].poll();

            if(vis_time[pos] != 0 && vis_time[pos] <= time)
                continue;

            vis_time[pos] = time;

            if(pos == K)
                continue;
            if(K < pos){
                q[0].offer(pos-1);
                q[1].offer(time+1);
            }
            else{
                if(pos > 1){
                    q[0].offer(pos-1);
                    q[1].offer(time+1);
                }
                if(pos < 100000){
                    q[0].offer(pos+1);
                    q[1].offer(time+1);
                }
                if(pos <= 50000){
                    q[0].offer(pos*2);
                    q[1].offer(time);
                }
            }
        }
        return vis_time[K] - 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        q[0] = new LinkedList<>();
        q[1] = new LinkedList<>();

        q[0].offer(N);
        q[1].offer(1);

        System.out.println(bfs());
    }
}
