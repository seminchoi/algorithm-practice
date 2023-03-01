import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
            if(n > 0){
                arr = Arrays.stream(arrString.substring(1, arrString.length() - 1).split(",")).map(Integer::parseInt).collect(Collectors.toList());
            }

            long countD = s.chars().filter(c -> c == 'D').count();
            if(countD > n){
                sb.append("error\n");
            }
            else if(countD == n){
                sb.append("[]\n");
            }
            else {
                boolean reverse = false;
                for (int j = 0; j < s.length(); j++) {
                    char c = s.charAt(j);
                    if (c == 'R') {
                        reverse = !reverse;
                    } else {
                        arr.remove(reverse ? arr.size()-1 : 0);
                    }
                }
                if(reverse){
                    Collections.reverse(arr);
                }
                sb.append(arr.toString().replaceAll(" ","")).append("\n");
            }
        }

        System.out.println(sb);
    }
}

