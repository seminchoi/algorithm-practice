import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Physical{
        int weight;
        int stature;

        public Physical(int weight, int stature) {
            this.weight = weight;
            this.stature = stature;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Physical> physicals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            physicals.add(new Physical(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }


        StringBuilder sb = new StringBuilder();

        for (Physical p1 : physicals) {
            int rank = 1;
            for (Physical p2 : physicals) {
                if(p1.stature < p2.stature && p1.weight < p2.weight){
                    rank++;
                }
            }
            sb.append(rank + " ");
        }

        System.out.println(sb);
    }
}
