import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n,k;

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if(n/2 < k){
            k = n - k;
        }

        int lRes = 1, rRes = 1;
        for (int i = 0; i < k; i++) {
            lRes = lRes * (n-i);
            rRes = rRes * (i+1);
        }

        System.out.println(lRes/rRes);

    }
}
