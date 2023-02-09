import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int idx = 1;
        int res = 666;
        while(idx != n){
            res++;
            if(numCheck(res)) {
                idx++;
            }
        }

        System.out.println(res);
    }

    static boolean numCheck(int res){
        String s = res+"";
        int con = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '6'){
                con++;
            }
            else
                con = 0;
            if(con == 3){
                return true;
            }
        }
        return false;
    }
}
