package 네연산;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ14395 {
    static int s, t;
    static String[] dp;
    static Queue<Info> q = new LinkedList<>();

    static class Info{
        int s;
        String res;

        public Info(int s, String res) {
            this.s = s;
            this.res = res;
        }
    }

    static void bfs(){
        while (!q.isEmpty()){
            Info cur = q.poll();
            if(cur.s == t && (dp[t] == "z" || cur.res.length() <= dp[t].length())){
                if(cur.res.compareTo(dp[t]) < 0){
                    dp[t] = cur.res;
                }
            }
//            System.out.println("s: " + cur.s + ",res: " + cur.res);
            if(cur.s * cur.s <= t
                    && (dp[cur.s * cur.s].equals("z")
                    || dp[cur.s * cur.s].length() >= cur.res.length() + 1)
                    &&(cur.res + "*").compareTo(dp[cur.s*cur.s]) < 0){
                dp[cur.s] = cur.res+"*";
                q.offer(new Info(cur.s*cur.s, cur.res+"*"));
            }

            if(cur.s + cur.s <= t
                    && (dp[cur.s + cur.s].equals("z")
                    || dp[cur.s + cur.s].length() >= cur.res.length() + 1)
                    && (cur.res + "+").compareTo(dp[cur.s+cur.s]) < 0){
                dp[cur.s] = cur.res+"+";
                q.offer(new Info(cur.s+cur.s, cur.res+"+"));
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        s = sc.nextInt();
        t = sc.nextInt();

        dp = new String[t+1];
        if(s==t){
            System.out.println(0);
            return;
        }

        for (int i = 0; i < t+1; i++) {
            dp[i] = "z";
        }

        q.offer(new Info(s,""));
        if(s < t){
            dp[s] = "";
        }
        dp[0] = "-";
        q.offer(new Info(0,"-"));

        if(s != 1) {
            dp[1] = "/";
            q.offer(new Info(1, "/"));
        }
        bfs();

        if(dp[t].equals("z")){
            System.out.println(-1);
        }
        else {
            System.out.println(dp[t]);
        }
    }
}
