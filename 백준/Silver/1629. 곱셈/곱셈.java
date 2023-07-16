import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long result = n;
        long cnt = 1;

        Stack<Long[]> stack = new Stack<>();
        stack.push(new Long[]{1L, result});

        while (cnt * 2 <= m) {
            result = (result * result) % k;
            cnt *= 2;

            stack.push(new Long[]{cnt, result});
        }

        result = 1;
        cnt = m;
        while (cnt > 0){
            if(stack.peek()[0] > cnt) {
                stack.pop();
                continue;
            }

            cnt -= stack.peek()[0];
            result = (result * stack.peek()[1]) % k;
        }

        System.out.println(result);
    }
}