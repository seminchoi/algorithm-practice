import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String infix = br.readLine();

        System.out.println(postfix(infix));
    }

    private static String postfix(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char cur = infix.charAt(i);

            if (cur >= 'A' && cur <= 'Z') {
                sb.append(cur);
            }

            if (cur == '(') {
                stack.push(cur);
            }

            if (cur == ')') {
                while (true) {
                    char operator = stack.pop();
                    if (operator == '(') {
                        break;
                    }
                    sb.append(operator);
                }
            }

            if (cur == '*' || cur == '/') {
                while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
                    sb.append(stack.pop());
                }
                stack.push(cur);
            }

            if (cur == '+' || cur == '-') {
                if (!stack.isEmpty()) {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                }
                stack.push(cur);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
