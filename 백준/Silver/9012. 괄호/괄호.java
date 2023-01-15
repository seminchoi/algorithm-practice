import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < t; i++){
            String s = br.readLine();
            int result = 0;
            for(int j = 0; j < s.length(); j++){
                char c = s.charAt(j);
                if(c == '('){
                    result++;
                }
                else
                    result--;
                if(result < 0){
                    break;
                }
            }
            if(result == 0)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}