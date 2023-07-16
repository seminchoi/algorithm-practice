import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String s = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String arrString = br.readLine();
            Deque<Integer> arr = new LinkedList<>();

            StringTokenizer st = new StringTokenizer(arrString, "[],");
            for (int j = 0; j < n; j++) {
                arr.add(Integer.parseInt(st.nextToken()));
            }

            boolean reverse = false, error = false;
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c == 'R') {
                    reverse = !reverse;
                } else {
                    if (arr.isEmpty()) {
                        sb.append("error\n");
                        error = true;
                        break;
                    }
                    if(reverse){
                        arr.pollLast();
                    }
                    else{
                        arr.pollFirst();
                    }
                }
            }
            if (!error) {
                if(arr.isEmpty()){
                    sb.append("[]\n");
                }
                else {
                    sb.append("[");
                    int size = arr.size();
                    if (reverse) {
                        for (int j = 0; j < size-1; j++) {
                            sb.append(arr.pollLast()).append(",");
                        }
                        sb.append(arr.poll()).append("]");
                    } else {
                        for (int j = 0; j < size - 1; j++) {
                            sb.append(arr.pollFirst()).append(",");
                        }
                        sb.append(arr.poll()).append("]");
                    }
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
