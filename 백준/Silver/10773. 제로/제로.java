import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();

        int n = Integer.parseInt(br.readLine());
        int res = 0;
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            if(s.equals("0")){
                res -= stack.pop();
            }
            else{
                int num = Integer.parseInt(s);
                res += num;
                stack.push(num);
            }
        }

        System.out.println(res);

    }
}
