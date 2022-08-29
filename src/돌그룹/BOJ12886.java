package 돌그룹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12886 {
    static int sum;
    static int[] rocks = new int[3];
    static boolean[][] vis = new boolean[1000][1000];
    static Queue<int[]> queue = new LinkedList<>();

    static void swap(int idx1, int idx2){
        int tmp = rocks[idx1];
        rocks[idx1] = rocks[idx2];
        rocks[idx2] = tmp;
    }

    static void sort(int[] rocks){
        int tmp;
        if(rocks[2] < rocks[0]){
            tmp = rocks[0];
            rocks[0] = rocks[2];
            rocks[2] = tmp;
        }
        if(rocks[1] < rocks[0]){
            tmp = rocks[0];
            rocks[0] = rocks[1];
            rocks[1] = tmp;
        }
        if(rocks[2] < rocks[1]){
            tmp = rocks[1];
            rocks[1] = rocks[2];
            rocks[2] = tmp;
        }
    }

    static int play(){
        while(!queue.isEmpty()){
            int[] rocks = queue.poll();
            if(rocks[0] == rocks[1] && rocks[1] == rocks[2])
                return 1;

            sort(rocks);
            if(vis[rocks[0]][rocks[1]] || rocks[0] == 0)
                continue;

            vis[rocks[0]][rocks[1]] = true;
            for (int i = 0; i <3 ; i++) {
                System.out.print(rocks[i] + " ");
            }
            System.out.println();

            int[] nextrocks = {rocks[0]*2, rocks[1], rocks[2] - rocks[0]};
            queue.offer(nextrocks);
            int[] nextrocks2 = {rocks[0], rocks[1]*2, rocks[2] - rocks[1]};
            queue.offer(nextrocks2);
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rocks[0] = Integer.parseInt(st.nextToken());
        rocks[1] = Integer.parseInt(st.nextToken());
        rocks[2] = Integer.parseInt(st.nextToken());

        sum = rocks[0] + rocks[1] + rocks[2];
        sort(rocks);
        if(sum % 3 != 0 )
            System.out.println(0);
        else if ( sum % 6 != 0 && !(rocks[0] == rocks[1] && rocks[1]== rocks[2])){
            System.out.println(0);
        }
        else {
            queue.offer(rocks);
            System.out.println(play());
        }
    }
}
