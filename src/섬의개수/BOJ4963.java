package 섬의개수;

import java.util.Scanner;

public class BOJ4963 {

    static int w, h, land = 0;
    static boolean[][] gp,vis;

    static void dfs(int x, int y){
        if(vis[y][x])
            return;
        vis[y][x] = true;
        if(x+1<w && gp[y][x+1])
            dfs(x+1,y);
        if(y+1<h && gp[y+1][x])
            dfs(x,y+1);
        if(x-1>=0 && gp[y][x-1])
            dfs(x-1,y);
        if(y-1>=0 && gp[y-1][x])
            dfs(x,y-1);
        if(x+1<w && y+1<h && gp[y+1][x+1])
            dfs(x+1,y+1);
        if(x-1>=0 && y+1<h && gp[y+1][x-1])
            dfs(x-1,y+1);
        if(x+1<w && y-1>=0 && gp[y-1][x+1])
            dfs(x+1,y-1);
        if(x-1>=0 && y-1>=0 && gp[y-1][x-1])
            dfs(x-1,y-1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true){
            w = sc.nextInt();
            h = sc.nextInt();

            if (w == 0 && h == 0)       break;

            gp = new boolean[h][w];
            vis = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    int tmp = sc.nextInt();
                    gp[i][j] = tmp == 0 ? false : true;
                }
            }



            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if(!vis[i][j] && gp[i][j]){
                        land++;
                        dfs(j,i);
                    }
                }
            }
            System.out.println(land);
            land = 0;
        }
    }


}
