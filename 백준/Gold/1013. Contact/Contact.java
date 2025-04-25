import java.io.*;
import java.util.regex.Pattern;

public class Main {
    public static String REGEX = "^(100+1+|01)+$";
    public static Pattern pattern = Pattern.compile(REGEX);
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            if(pattern.matcher(s).matches()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}