package 돌그룹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12886 {
    static int[] rocks = new int[4];

    static void swap(int idx1, int idx2){
        int tmp = rocks[idx1];
        rocks[idx1] = rocks[idx2];
        rocks[idx2] = tmp;
    }

    static boolean sort(){
        int tmp;
        if(rocks[0] == rocks[1] && rocks[1] == rocks[2]){
            return false;
        }
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
        return true;
    }

    static int play(){
        while(sort()){
            if(rocks[1] - rocks[0] == 1 && rocks[2] - rocks[1] == 1 || rocks[0] * 2 == rocks[2] - rocks[0])
                return 0;

            for (int i = 0; i <3 ; i++) {
                System.out.print(rocks[i] + " ");
            }
            System.out.println();
            rocks[2] = rocks[2] - rocks[0];
            rocks[0] *= 2;
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rocks[0] = Integer.parseInt(st.nextToken());
        rocks[1] = Integer.parseInt(st.nextToken());
        rocks[2] = Integer.parseInt(st.nextToken());

        if((rocks[0] + rocks[1] + rocks[2]) % 3 != 0)
            System.out.println(0);
        else
            System.out.println(play());
    }
}
