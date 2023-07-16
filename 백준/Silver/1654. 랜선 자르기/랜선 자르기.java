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
        long max = 0;
        for (int i = 0; i < k; i++) {
            lan[i] = Integer.parseInt(br.readLine());
            max = Math.max(lan[i], max);
        }

        if(max == 1){
            System.out.println(1);
        }
        else {
            System.out.println(bSearch(lan, 0, max+1));
        }
    }

    static long bSearch(int[] lan, long min, long max){
        if(min >= max){
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
