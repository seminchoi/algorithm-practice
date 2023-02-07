import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Map<Integer,Integer> hMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(st.nextToken());
            Integer cnt = hMap.getOrDefault(input, 0) + 1;
            hMap.put(input, cnt);
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < m; i++) {
            Integer value = hMap.getOrDefault(Integer.parseInt(st.nextToken()), 0);
            res.append(value).append(" ");
        }

        System.out.println(res);
    }
}
