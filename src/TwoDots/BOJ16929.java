package TwoDots;

import java.util.Scanner;

public class BOJ16929 {

    static int w, h;
    static char[][] gp;
    static boolean[][] vis;
    static boolean res;

    static void dfs(int x, int y, int px, int py){
        if(vis[y][x]){
            res = true;
            return;
        }
        vis[y][x] = true;
        if(x+1<w && gp[y][x+1]==gp[y][x] && ( x+1!=px || y != py)) {
            dfs(x + 1, y, x, y);
        }
        if(x-1>= 0 && gp[y][x-1]==gp[y][x] && ( x-1!=px || y != py)) {
            dfs(x - 1, y, x, y);
        }
        if(y+1<h && gp[y+1][x]==gp[y][x] && ( x!=px || y+1!= py)) {
            dfs(x, y+1, x, y);
        }
        if(y-1>=0 && gp[y-1][x]==gp[y][x] && ( x!=px || y-1 != py)) {
            dfs(x, y-1, x, y);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        h = sc.nextInt();
        w = sc.nextInt();

        gp = new char[h][w];
        vis = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            String tmp = sc.next();
            for (int j = 0; j < w; j++) {
                gp[i][j] = tmp.charAt(j);
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if(vis[i][j]) continue;
                    dfs(j,i,-1,-1);
            }
        }

        if(res)
            System.out.println("Yes");
        else
            System.out.println("No");

    }
}
