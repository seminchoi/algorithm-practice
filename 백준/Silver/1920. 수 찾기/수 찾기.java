import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int t = Integer.parseInt(st.nextToken());
            int min = 0;
            int max = n;
            int mid;
            while(true){
                if(min >= max){
                    sb.append("0\n");
                    break;
                }
                mid = (min + max) / 2;

                if(arr[mid] == t){
                    sb.append("1\n");
                    break;
                }

                else if(arr[mid] < t){
                    min = mid + 1;
                }

                else{
                    max = mid;
                }
            }
        }
        System.out.println(sb);
    }
}
