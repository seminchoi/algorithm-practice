import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(bk(arr, n, m, 0, 0, 0, 0));
    }

    static int bk(int[] arr, int n, int m, int s, int cnt, int sum, int max){
        if(cnt == 3){
            if(m >= sum && max < sum)
                return sum;
            else
                return max;
        }
        else {
            for (int i = s; i < n; i++) {
                sum += arr[i];
                max = Math.max(bk(arr, n, m, i + 1, cnt + 1, sum, max), max);
                sum -= arr[i];
            }
            return max;
        }
    }
}
