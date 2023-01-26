import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        int[] lan = new int[k];
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            lan[i] = Integer.parseInt(br.readLine());
            max = Math.max(lan[i], max);
            min = Math.min(lan[i], min);
        }

        if(max == 1){
            System.out.println(1);
        }
        else if (max == min && n == k){
            System.out.println(max);
        }
        else {
            System.out.println(bSearch(lan, 1L, max));
        }
    }

    static long bSearch(int[] lan, long min, long max){
        if(min == max){
            return min - 1;
        }
        long mid = (min + max) / 2;
        long cnt = 0;
        for (int i : lan) {
            cnt = cnt + (i / mid);
        }
        if(cnt >= n){
            return bSearch(lan, mid + 1, max);
        }
        else{
            return bSearch(lan, min, mid);
        }
    }
}
