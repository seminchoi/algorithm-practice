package BFS스페셜저지;

import java.util.*;

import static java.lang.System.exit;

// 해결법 1. 각 계층에 대한 배열을 만들고 각 배열을 sort하여 비교
// 해결법 2. 각 계층에 대한 Set을 만들고 각 계층의 집합의 요소가 같은지 비교
public class BOJ16940 {
    static int N;
    static int[] order;
    static List<Integer>[] gp;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        gp = new LinkedList[N+1];
        order = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            gp[i] = new LinkedList<>();
        }

        for (int i = 0; i < N-1; i++) {
            int parent = sc.nextInt();
            int child = sc.nextInt();
            gp[parent].add(child);
        }

        for (int i = 1; i < N+1; i++) {
            int ans = sc.nextInt();
            order[ans] = i;
            q.offer(ans);
        }

        for(int i=1; i <= N; i++){
            Collections.sort(gp[i], new Comparator<Integer>(){
                public int compare(Integer u, Integer v){
                    if(order[u] < order[v]){
                        return -1;
                    }
                    else {
                        return 1;
                    }
                }
            });
        }

        Queue<Integer> temp = new LinkedList<>();
        temp.offer(1);
        q.poll();
        while(!temp.isEmpty()){
            int pos = temp.poll();
            Iterator<Integer> it =gp[pos].listIterator();
            while(it.hasNext()){
                int i = it.next();
                if(i == q.peek()){
                    temp.offer(i);
                    q.poll();
                }
            }
        }
        if(q.isEmpty())
            System.out.println(1);
        else
            System.out.println(0);

    }

}
