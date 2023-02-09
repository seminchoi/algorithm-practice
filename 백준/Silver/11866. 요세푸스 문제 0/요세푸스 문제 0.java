import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n, k;
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int[] res = new int[n];

        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n ; i++) {
            list.add(i);
        }

        int idx = 0;
        int resIdx = 0;
        while(!list.isEmpty()){
            idx += k - 1;
            if(idx >= list.size()){
                idx = idx % list.size();
            }
            res[resIdx++] = list.get(idx);
            list.remove(idx);
        }

        StringBuilder sb = new StringBuilder("<");
        for (int i = 0; i < n-1; i++) {
            sb.append(res[i]).append(", ");
        }
        sb.append(res[n-1]).append(">");
        System.out.println(sb);
    }
}
