import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int m = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();

        String s = br.readLine();

        for (int i = 0; i < s.length() - 1; i++) {
            if(s.charAt(i) == 'I'){
                int len = 0;
                while(i < s.length() - 1){
                    char cur = s.charAt(i);
                    i++;
                    char next = s.charAt(i);
                    if(cur == next)
                        break;
                    else{
                        len++;
                    }
                }
                len = len / 2;
                int cnt = map.getOrDefault(len, 0) + 1;
                map.put(len, cnt);
                i--;
            }
        }

        int res = 0;

        for (Integer len : map.keySet()) {
            int cnt = map.get(len);
            if(len >= n) {
                res = res + (len - n + 1) * cnt;
            }
        }

        System.out.println(res);
    }
}
