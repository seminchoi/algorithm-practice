package 토마토;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ7576 {
    static int T, I, X, Y;
    static int[][] gp;
    static Queue<String> q;

    static void bfs(int x, int y,int cnt){
        if(x+2 < I && y+1 < I && gp[y+1][x+2] == 0) {
            q.add((x + 2) + " " + (y+1));
            gp[y+1][x+2] = cnt+1;
        }
        if(x+2 < I && y-1 >= 0 && gp[y-1][x+2] == 0) {
            q.add((x + 2) + " " + (y-1));
            gp[y-1][x+2] = cnt+1;
        }
        if(x-2 >= 0 && y+1 < I && gp[y+1][x-2] == 0) {
            q.add((x - 2) + " " + (y+1));
            gp[y+1][x-2] = cnt+1;
        }
        if(x-2 >= 0 && y-1 >= 0 && gp[y-1][x-2] == 0) {
            q.add((x - 2) + " " + (y-1));
            gp[y-1][x-2] = cnt+1;
        }
        if(y+2 < I && x+1 < I && gp[y+2][x+1] == 0) {
            q.add((x + 1) + " " + (y+2));
            gp[y+2][x+1] = cnt+1;
        }
        if(y+2 < I && x-1 >= 0 && gp[y+2][x-1] == 0) {
            q.add((x-1) + " " + (y+2));
            gp[y+2][x-1] = cnt+1;
        }
        if(y-2 >= 0 && x+1 < I && gp[y-2][x+1] == 0) {
            q.add((x+1) + " " + (y-2));
            gp[y-2][x+1] = cnt+1;
        }
        if(y-2 >= 0 && x-1 >= 0 && gp[y-2][x-1] == 0) {
            q.add((x-1) + " " + (y-2));
            gp[y-2][x-1] = cnt+1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int res = 0;

        T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            q = new LinkedList<>();
            I = sc.nextInt();

            gp = new int[I][I];

            int cur_x = sc.nextInt();
            int cur_y = sc.nextInt();
            gp[cur_y][cur_x] = 1;

            X = sc.nextInt();
            Y = sc.nextInt();

            q.add(cur_x + " " + cur_y);
            while(true){
                String pos = q.poll();
                String[] st = pos.split(" ");
                int x = Integer.parseInt(st[0]);
                int y = Integer.parseInt(st[1]);
                int cnt = gp[y][x];
                if(x== X && y == Y) {
                    System.out.println(cnt - 1);
                    break;
                }
                bfs(x,y,cnt);
            }
        }
    }
}

