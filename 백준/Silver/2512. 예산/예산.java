import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    static int max = 0;
    public static void main (String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] m = Stream.of(br.readLine().split(" ")).mapToInt(Main::mapAndFind).toArray();
        int limit = Integer.parseInt(br.readLine());
        max++;
        int min = 0;

        while(min < max) {
            int mid = (min + max) / 2;
            int res = 0;
            for(int i = 0; i < n; i++) {
                res += Math.min(mid, m[i]);
            }
            if(res <= limit) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        System.out.println(min - 1);
    }

    private static int mapAndFind(String s) {
        int n = Integer.parseInt(s);
        max = Math.max(n, max);
        return n;
    }

}