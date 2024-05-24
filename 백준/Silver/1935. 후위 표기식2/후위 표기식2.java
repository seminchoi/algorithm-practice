import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int operandCount = Integer.parseInt(br.readLine());
        String expression = br.readLine();

        Stack<Double> stack = new Stack<>();
        Map<Character, Integer> operand = new HashMap<>();
        char key = 'A';
        for (int i = 0; i < operandCount; i++) {
            operand.put(key++, Integer.parseInt(br.readLine()));
        }

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if(c >= 'A' && c <= 'Z') {
                stack.push((double) operand.get(c));
            }
            else {
                double n1 = stack.pop();
                double n2 = stack.pop();
                if(c == '+') {
                    stack.push(n1+n2);
                }
                if(c== '-') {
                    stack.push(n2-n1);
                }
                if(c=='/') {
                    stack.push(n2/n1);
                }
                if(c=='*') {
                    stack.push(n2*n1);
                }
            }
        }

        System.out.printf("%.2f", stack.pop());
    }
}