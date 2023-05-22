import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int sum = 0, size = 0;

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(st.nextToken());
            queue.offer(p);
            sum += p;

            if(sum >= s){
                while(true){
                    if(sum - queue.peek() >= s){
                        sum -= queue.poll();
                    }
                    else break;
                }
                size = size == 0 || size > queue.size() ? queue.size() : size;
            }
        }

        System.out.println(size);
    }
}
