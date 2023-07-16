import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = 1234567891;
        long l = Integer.parseInt(br.readLine()), r = 1;
        long result = 0;

        String s = br.readLine();

        for(int i = 0; i < l; i++){
            int n = s.charAt(i) - 'a' + 1;
            result = (result + (n * r)) % m;
            r = (r * 31) % m;
        }

        System.out.println(result % m);
    }
}