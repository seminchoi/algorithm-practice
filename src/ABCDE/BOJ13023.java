package ABCDE;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class BOJ13023 {

    static LinkedList<Integer>[] adj;
    static int N, M;
    static boolean[] vis;
    static boolean res;
    static int cnt;

    static void DFS(int N){
        if(res) return;

        if(vis[N])
            return;
        Iterator<Integer> it = adj[N].listIterator();
        vis[N] = true;
        System.out.println(cnt);
        while(it.hasNext()){
            int n = it.next();
            if(!vis[n]){
                if(cnt + 1 == 4){
                    res = true;
                    return;
                }
                else {
                    cnt++;
                    DFS(n);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        vis = new boolean[N];

        adj = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++){
            int x = sc.nextInt(), y = sc.nextInt();
            adj[x].add(y); adj[y].add(x);
        }

        for (int i = 0; i < N; i++) {
            cnt = 0;
            DFS(i);
            System.out.println();
        }
        if(res)
            System.out.println(1);
        else
            System.out.println(0);
    }
}
