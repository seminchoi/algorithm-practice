import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Triangle{
        int[] edges;

        public Triangle(int[] edges) {
            this.edges = edges;
        }

        String checking(){
            Arrays.sort(edges);
            if(edges[0]*edges[0] + edges[1]*edges[1] == edges[2]*edges[2]){
                return "right\n";
            }
            else
                return "wrong\n";
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] edges = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            if(edges[0] == 0 && edges[1] == 0 && edges[2] == 0)
                break;
            Triangle triangle = new Triangle(edges);
            sb.append(triangle.checking());

        }

        System.out.println(sb);
    }
}
