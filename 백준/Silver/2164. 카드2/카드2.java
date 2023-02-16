import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        Deque<Integer> deque = new LinkedList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i < n+1; i++) {
            deque.add(i);
        }

        while (deque.size() > 1){
            deque.pollFirst();
            deque.offerLast(deque.pollFirst());
        }
        System.out.println(deque.peek());
    }
}
