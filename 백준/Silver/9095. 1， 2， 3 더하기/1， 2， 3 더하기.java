import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(cases(Integer.parseInt(br.readLine()))).append("\n");
        }
        System.out.println(sb);
    }

    public static int cases(int n){
        if(n == 0){
            return 1;
        }
        int num = 0;

        if(n >= 3){
            num += cases(n-3);
        }
        if(n >= 2){
            num += cases(n-2);
        }
        if(n >= 1){
            num += cases(n-1);
        }

        return num;
    }
}
