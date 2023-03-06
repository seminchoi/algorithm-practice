import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String s = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String arrString = br.readLine();
            List<Integer> arr = new ArrayList<>();
            if (n > 0) {
                arr = Arrays.stream(arrString.substring(1, arrString.length() - 1).split(",")).map(Integer::parseInt).collect(Collectors.toList());
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
                    arr.remove(reverse ? arr.size() - 1 : 0);
                }
            }
            if (!error) {
                if(arr.isEmpty()){
                    sb.append("[]\n");
                }
                else {
                    sb.append("[");
                    if (reverse) {
                        for (int j = arr.size() - 1; j > 0; j--) {
                            sb.append(arr.get(j)).append(",");
                        }
                        sb.append(arr.get(0)).append("]");
                    } else {
                        for (int j = 0; j < arr.size() - 1; j++) {
                            sb.append(arr.get(j)).append(",");
                        }
                        sb.append(arr.get(arr.size() - 1)).append("]");
                    }
                    sb.append("\n");
                }
            }
        }


        System.out.println(sb);
    }
}
