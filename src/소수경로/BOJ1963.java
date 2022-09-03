package 소수경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1963 {
    static boolean[] isntPrime = new boolean[10001], vis = new boolean[10001];
    static int t;
    //idx 0은 다음 비밀번호, 1은 depth
    static Queue<int []> queue = new LinkedList<>();

    public static int bfs(int end){
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(cur[0] == end){
                return cur[1];
            }
            for (int i = 0; i < 4; i++) {
                addQueue(cur,i);
            }
        }
        return -1;
    }

    public static void addQueue(int[] cur, int digit){
        int n = cur[0] - (cur[0] % (int)Math.pow(10,digit+1));
        //digit이 0일때 아래 계산을 한다면
        if(digit != 0){
            n += (cur[0] % (int)Math.pow(10,digit));
        }
        int multi = (int)Math.pow(10,digit);
        for (int i = 0; i < 10; i++) {
            if(digit == 3 && i == 0){
                continue;
            }
            int nextN = n + (i*multi);
            if(!isntPrime[nextN] && !vis[nextN]){
//                System.out.printf("n: %d, digit: %d\n",nextN,digit);
                queue.offer(new int[] {nextN,cur[1]+1});
                vis[nextN] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int i = 2; i <= 10000; i++) {
            for (int j = i * 2; j < 10000; j= j+i) {
                isntPrime[j] = true;
            }
        }

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            queue.offer(new int[] {start,0});
            int res = bfs(end);
            if(res == -1)
                System.out.println("impossible");
            else
                System.out.println(res);
            queue.clear();
            vis = new boolean[10001];
        }
    }
}
