import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] board;
    static int bCnt = 0, wCnt = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        board = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = st.nextToken().equals("0");
            }
        }

        divide(0, 0, n-1, n-1);

        System.out.println(wCnt);
        System.out.println(bCnt);
    }

    public static void divide(int sx, int sy, int ex, int ey) {
        if(sx == ex || boardCheck(sx, sy, ex, ey)){
            bCnt = board[sy][sx] ? bCnt : bCnt + 1;
            wCnt = board[sy][sx] ? wCnt + 1 : wCnt;
        }
        else{
            int midx = (sx + ex) / 2;
            int midy = (sy + ey) / 2;
            divide(sx,sy, midx, midy);
            divide(midx+1, sy, ex, midy);
            divide(sx, midy+1, midx, ey);
            divide(midx+1,midy+1, ex, ey);
        }
    }
    
    public static boolean boardCheck(int sx, int sy, int ex, int ey){
        boolean b = board[sy][sx];
        for (int i = sy; i <= ey; i++) {
            for (int j = sx; j <= ex; j++) {
                if(board[i][j] != b)
                    return false;
            }
        }
        return true;
    }
}
