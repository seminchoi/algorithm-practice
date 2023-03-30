import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] arr = new String[n+1];
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            arr[i+1] =  s;
            map.put(s, i+1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String s = br.readLine();
            if(isInteger(s)){
                int idx = Integer.parseInt(s);
                sb.append(arr[idx] + "\n");
            }
            else{
                sb.append(map.get(s) + "\n");
            }
        }

        System.out.println(sb);
    }

    public static boolean isInteger(String s){
        try{
            Integer.parseInt(s);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
