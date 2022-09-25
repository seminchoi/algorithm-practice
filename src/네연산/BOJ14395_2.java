package 네연산;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ14395_2 {
    static long s, t;
    static HashMap<Long, String> map = new HashMap<>();
    static Queue<Info> q = new LinkedList<>();

    static class Info{
        long s;
        String res;

        public Info(long s, String res) {
            this.s = s;
            this.res = res;
        }
    }

    static void bfs(){
        while (!q.isEmpty()){
            Info cur = q.poll();
            if(cur.s == t ){
                if(!map.containsKey(t))
                    map.put(t,cur.res);
                else if (cur.res.length() <= map.get(t).length() &&
                cur.res.compareTo(map.get(t)) < 0){
                    map.put(t,cur.res);
                }
            }
//            System.out.println("s: " + cur.s + ",res: " + cur.res);
            if(cur.s * cur.s <= t){

                if(!map.containsKey(cur.s *cur.s)) {
                    map.put(cur.s * cur.s, cur.res + "*");
                    q.offer(new Info(cur.s * cur.s, cur.res + "*"));
                }

                else if( map.get(cur.s * cur.s).length() >= cur.res.length() + 1
                    && (cur.res + "*").compareTo(map.get(cur.s * cur.s)) < 0 ) {
                    map.replace(cur.s*cur.s, cur.res + "*");
                    q.offer(new Info(cur.s*cur.s, cur.res+"*"));
                }

            }
            if(cur.s + cur.s <= t){

                if(!map.containsKey(cur.s + cur.s)) {
                    map.put(cur.s + cur.s, cur.res + "+");
                    q.offer(new Info(cur.s + cur.s, cur.res + "+"));
                }

                else if( map.get(cur.s + cur.s).length() >= cur.res.length() + 1
                        && (cur.res + "+").compareTo(map.get(cur.s + cur.s)) < 0 ) {
                    map.replace(cur.s + cur.s, cur.res + "+");
                    q.offer(new Info(cur.s+cur.s, cur.res+"+"));
                }

            }

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        s = sc.nextLong();
        t = sc.nextLong();

        if(s==t){
            System.out.println(0);
            return;
        }


        q.offer(new Info(s,""));
        map.put(s,"");

        q.offer(new Info(0,"-"));
        map.put(0L,"-");

        if(s != 1) {
            q.offer(new Info(1, "/"));
            map.put(1L,"/");
        }
        bfs();

        if(map.containsKey(t)) {
            System.out.println(map.get(t));
        }
        else
            System.out.println(-1);
    }
}
