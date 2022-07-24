package 숨바꼭질4;

import java.util.*;

public class BOJ13913 {
    static int N, K;
    static LinkedList<Integer> res = new LinkedList<>();
    static List<Integer>[] gp = new List[100001];
    static boolean[] vis = new boolean[100001];
    static Queue<Integer> q = new LinkedList<>();


    public static void bfs() {
        while (q.peek() != K) {
            int n = q.poll();
            if(vis[n])
                continue;
            vis[n] = true;

            if (n > K) {
                gp[n - 1].add(n);
                q.offer(n - 1);
            } else if (n < K) {
                if (n > 1) {
                    gp[n - 1].add(n);
                    q.offer(n - 1);
                }
                if (n < 100000) {
                    gp[n + 1].add(n);
                    q.offer(n + 1);
                }
                if (n * 2 <= 100000) {
                    gp[n * 2].add(n);
                    q.offer(n * 2);
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        for (int i = 0; i < 100001; i++) {
            gp[i] = new LinkedList<>();
        }

        q.offer(N);
        bfs();

        int pos = K;
        res.add(K);
        while(pos != N){
            pos = gp[pos].get(0);
            res.addFirst(pos);
        }
        int cnt = res.size();
        System.out.println(cnt-1);

        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
