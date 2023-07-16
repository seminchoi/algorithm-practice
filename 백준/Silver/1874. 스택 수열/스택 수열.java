import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        StringBuilder sb = new StringBuilder();
        int i = 1, inCnt = 1;
        int in = Integer.parseInt(br.readLine());
        while(i <= n) {
            stack.push(i++);
            sb.append("+\n");
            while (!stack.empty() && stack.peek() == in){
                stack.pop();
                sb.append("-\n");
                if(inCnt < n) {
                    inCnt++;
                    in = Integer.parseInt(br.readLine());
                }
            }
        }

        if(stack.empty()) {
            System.out.println(sb);
        }
        else
            System.out.println("NO");
    }
}
