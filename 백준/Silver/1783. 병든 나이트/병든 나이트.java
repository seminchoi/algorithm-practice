import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        long n, m, result;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        n = Integer.parseInt(input.nextToken());
        m = Integer.parseInt(input.nextToken());

        if(n == 1){
            result = 1;
        }
        else if(n == 2){
            result = (long)Math.ceil((double)m/2);
            if(result > 4)
                result = 4;
        }
        else{
            if(m < 4){
                result = m;
            }
            else if(m < 7){
                result = 4;
            }
            else
                result = 5 + m - 7;
        }
        System.out.println(result);
    }
}
