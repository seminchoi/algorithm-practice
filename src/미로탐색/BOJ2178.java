package 미로탐색;
//시간초과가 안나네요
import java.util.Scanner;

public class BOJ2178 {
    static int w, h;
    static boolean[][] gp,vis;
    static int[][] disCnt;

    static void bfs(int x, int y, int dis){
        vis[y][x] = true;
        if(x+1<w && gp[y][x+1] && !vis[y][x+1])
            disCnt[y][x+1] = dis+1;
        if(y+1<h && gp[y+1][x] && !vis[y+1][x])
            disCnt[y+1][x] = dis+1;
        if(x-1>=0 && gp[y][x-1] && !vis[y][x-1])
            disCnt[y][x-1] = dis+1;
        if(y-1>=0 && gp[y-1][x] && !vis[y-1][x])
            disCnt[y-1][x] = dis+1;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        h = sc.nextInt();
        w = sc.nextInt();

        gp = new boolean[h][w];
        vis = new boolean[h][w];
        disCnt = new int[h][w];

        for (int i = 0; i < h; i++){
            String tmp = sc.next();
            for (int j = 0; j < w; j++) {
                gp[i][j] = tmp.charAt(j)!='0';
            }
        }

        int dis = 1;
        disCnt[0][0] = dis;
        while(disCnt[h-1][w-1] == 0) {
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if(disCnt[i][j] == dis) {
                        bfs(j, i, dis);
                    }
                }
            }
            dis++;
        }

        System.out.println(disCnt[h-1][w-1]);
    }
}
