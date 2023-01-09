package 터렛;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1002 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        int[][] input = new int[t][6];


        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < t; i++) {
            int xDiff = input[i][0] - input[i][3];
            int yDiff = input[i][1] - input[i][4];
            int dist = xDiff * xDiff + yDiff * yDiff;

            int r1 = input[i][2], r2 = input[i][5];


            int result = 0;
            if(dist == 0 && r1 == r2){
                result = -1;
            }
            else if((r1 + r2)*(r1 + r2) == dist || (r1-r2)*(r1-r2) == dist){
                result = 1;
            }
            else if((r1-r2)*(r1-r2) < dist && (r1 + r2)*(r1 + r2) > dist){
                result = 2;
            }
            else{
                result = 0;
            }
            System.out.println(result);
        }
    }
}
