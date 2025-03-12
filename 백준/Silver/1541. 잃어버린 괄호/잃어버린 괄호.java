import java.util.*;
import java.io.*;

public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        
        boolean plus = true;
        long answer = 0;
        String cur = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '-' || input.charAt(i) == '+') {
                long value = Long.parseLong(cur);
                if (plus) answer += value;
                else answer -= value;
                cur = "";
                if (input.charAt(i) == '-') {
                    plus = false;
                }
                continue;
            }
            cur += input.charAt(i);
        }
        long value = Long.parseLong(cur);
        if (plus) answer += value;
        else answer -= value;
        
        System.out.println(answer);
    }
}