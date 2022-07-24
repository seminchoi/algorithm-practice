package DFS스페셜저지;

import java.util.*;

public class BOJ16964 {

    static int N, idx;
    static List<Integer>[] gp;
    static boolean[] vis;
    static int[] order, res;

    public static void dfs(int s){
        if(vis[s])
            return;
        vis[s]= true;
        res[idx++] = s;
        Iterator<Integer> it = gp[s].listIterator();
        while(it.hasNext()){
            int n = it.next();
            dfs(n);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        gp = new List[N+1];
        order = new int[N+1];
        res = new int[N+1];
        vis = new boolean[N+1];

        for (int i = 0; i < N+1; i++)
            gp[i] = new LinkedList<>();

        for (int i = 0; i < N-1; i++) {
            int parent = sc.nextInt();
            int child = sc.nextInt();
            gp[parent].add(child);
            gp[child].add(parent);
        }

        for (int i = 1; i < N+1; i++) {
            int ans = sc.nextInt();
            order[ans] = i;
        }

        for (int i = 1; i < N+1; i++) {
            Collections.sort(gp[i], new Comparator<Integer>(){
                public int compare(Integer u, Integer v){
                    return order[u] < order[v] ? -1 : 1;
                }
            });
        }

        if(order[1] != 1){
            System.out.println(0);
            return;
        }

        idx = 1;
        dfs(1);

        for (int i = 1; i < N+1; i++) {
            if(i == order[res[i]])
                continue;
            else{
                System.out.println(0);
                return;
            }
        }

        System.out.println(1);

    }
}