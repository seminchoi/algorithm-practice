import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<String> input = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            input.add(br.readLine());
        }

        input.sort((s1, s2) -> {
            if(s1.length() != s2.length())
                return s1.length() - s2.length();
            else
                return s1.compareTo(s2);
        });

        StringBuilder sb = new StringBuilder();
        String preString = null;
        for (String s : input) {
            if(preString == null || !preString.equals(s))
                sb.append(s +"\n");
            preString = s;
        }
        System.out.println(sb);
    }
}