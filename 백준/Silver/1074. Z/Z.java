import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int cnt = 0;
    static int r, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int size = (int)Math.pow(2, n);

        recursive(0,0, size-1, size-1);
        System.out.println(cnt-1);
    }

    public static boolean recursive(int sX, int sY, int eX, int eY){
        if(sX == eX && sY == eY){
            cnt++;
            if(sY == r && sX == c){
                return true;
            }
        }
        else{
            int midX = (sX + eX) / 2;
            int midY = (sY + eY) / 2;
            if(r >= sY && r <= midY && c >= sX && c <= midX) {
                if (recursive(sX, sY, midX, midY))
                    return true;
            }
            else{
                cnt += (int)Math.pow(midX-sX + 1,2);
            }

            if(r >= sY && r <= midY && c >= midX + 1 && c <= eX) {
                if (recursive(midX + 1, sY, eX, midY))
                    return true;
            }
            else{
                cnt += (int)Math.pow(midX-sX + 1,2);
            }

            if(r >= midY + 1 && r <= eY && c >= sX && c <= midX) {
                if (recursive(sX, midY + 1, midX, eY))
                    return true;
            }
            else{
                cnt += (int)Math.pow(midX-sX + 1,2);
            }

            if(r >= midY+1 && r <= eY && c >= midX + 1 && c <= eX) {
                if (recursive(midX + 1, midY + 1, eX, eY))
                    return true;
            }
            else{
                cnt += (int)Math.pow(midX-sX + 1,2);
            }

        }
        return false;
    }
}
