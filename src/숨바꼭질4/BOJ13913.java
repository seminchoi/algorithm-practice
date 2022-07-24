package 숨바꼭질4;

import java.util.*;

public class BOJ13913 {
    static int N, K;
    static LinkedList<Integer> res = new LinkedList<>();
    static int[] parent = new int[100001];
    static boolean[] vis = new boolean[100001];
    static Queue<Integer> q = new LinkedList<>();


    public static void bfs() {
        while (!q.contains(K)) {
            int n = q.poll();

            if (n > K && !vis[n-1]) {
                parent[n - 1] = n;
                vis[n-1] = true;
                q.offer(n - 1);
            } else if (n < K) {
                if (n > 1 && !vis[n-1]) {
                    parent[n - 1] = n;
                    vis[n-1] = true;
                    q.offer(n - 1);
                }
                if (n < 100000 && !vis[n+1]) {
                    parent[n + 1] = n;
                    vis[n+1] = true;
                    q.offer(n + 1);
                }
                if (n * 2 <= 100000 && !vis[n*2]) {
                    parent[n * 2] = n;
                    vis[n*2] = true;
                    q.offer(n * 2);
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        q.offer(N);
        vis[N]= true;
        bfs();

        int pos = K;
        res.add(K);

        while(pos != N){
            pos = parent[pos];
            res.addFirst(pos);
        }
        int cnt = res.size();
        System.out.println(cnt-1);

        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
