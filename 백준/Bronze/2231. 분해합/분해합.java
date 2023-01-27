import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int res = 0;
        int i;
        for (i = 1; i < n; i++) {
            int sum = 0;
            sum += i;
            int j = i;
            while(j != 0){
                sum += j % 10;
                j = j / 10;
            }
            if(sum == n){
                res = i;
                break;
            }
        }

        System.out.println(res);
    }
}
