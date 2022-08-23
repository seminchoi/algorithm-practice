package DSLR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//중점
//1.L,R 연산 자원의 최소화
//2.bfs노드 최적화
public class BOJ9019 {
    static int A, B;
    static String res;
    static boolean[] vis;
    static Queue<Integer> aQ = new LinkedList<>();
    static Queue<String> sQ = new LinkedList<>();

    static String bfs(){
        while(!aQ.isEmpty()){
            int curA = aQ.poll();
            String curS = sQ.poll();

            if(curA == B)
                return curS;

            int nextA = (curA * 2) % 10000;
            if(!vis[nextA]) {
                sQ.offer('D' + curS);
                aQ.offer(nextA);
                vis[nextA] = true;
            }

            nextA = (curA - 1) == -1 ? 9999 : curA - 1;
            if(!vis[nextA]) {
                sQ.offer('S' + curS);
                aQ.offer(nextA);
                vis[nextA] = true;
            }
            //L
            nextA = ((curA * 10) % 10000) + curA / 1000;
            if(!vis[nextA]) {
                sQ.offer("L" + curS);
                aQ.offer(nextA);
                vis[nextA] = true;
            }
            //R
            nextA = (curA / 10) + ((curA % 10) * 1000);
            if(!vis[nextA]) {
                sQ.offer("R" + curS);
                aQ.offer(nextA);
                vis[nextA] = true;
            }
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            aQ = new LinkedList<>();
            sQ = new LinkedList<>();
            vis = new boolean[10000];

            aQ.offer(A);
            sQ.offer(" ");

            StringBuffer revRes = new StringBuffer(bfs());
            System.out.println(revRes.reverse().toString().strip());
        }
    }
}
