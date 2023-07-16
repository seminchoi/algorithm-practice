import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //0. n층의 블록 갯수
        //1. n층까지 쌓는데 드는 시간
        //2. n층까지 쌓고 생기는 블록 (음수로 표현)
        //3. n층까지 깎는데 드는 시간
        //4. n층까지 깎고 생기는 블록
        int[][] calc = new int[5][258];

        int n, m, b;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        int maxHeight = 0, minHeight = 257;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int height = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, height);
                minHeight = Math.min(minHeight,height);
                calc[0][height] += 1;
            }
        }

        for (int i = maxHeight-1; i >= 0; i--) {
            calc[0][i] += calc[0][i+1];
        }

        for (int i = maxHeight-1; i >= minHeight; i--) {
            calc[4][i] = calc[4][i+1] + calc[0][i+1];
            calc[3][i] = calc[4][i] * 2;
        }

        for (int i = minHeight + 1; i <= maxHeight ; i++) {
            calc[2][i] = calc[2][i-1] + (calc[0][i-1] - calc[0][i]);
        }

        for (int i = minHeight + 1; i <= maxHeight; i++) {
            calc[2][i] = calc[2][i-1] + calc[2][i];
            calc[1][i] = calc[2][i];
        }

        int minTime = Integer.MAX_VALUE;
        int resHeight = maxHeight;
        for (int i = maxHeight; i >= minHeight; i--) {
            int leftB = b - calc[2][i] + calc[4][i];
            if(leftB < 0)
                continue;

            int time = calc[1][i] + calc[3][i];
            if (minTime > time){
                minTime = time;
                resHeight = i;
            }
        }

        System.out.println(minTime + " " + resHeight);

    }
}
