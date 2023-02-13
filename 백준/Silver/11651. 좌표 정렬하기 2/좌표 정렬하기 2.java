import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Pos implements Comparable<Pos>{
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pos p) {
            if(y == p.y){
                return x-p.x;
            }
            else
                return y - p.y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Pos[] posArr = new Pos[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            posArr[i] = new Pos(x,y);
        }

        Arrays.sort(posArr);

        StringBuilder sb = new StringBuilder();
        for(Pos pos : posArr){
            sb.append(pos).append("\n");
        }

        System.out.println(sb);
    }
}
