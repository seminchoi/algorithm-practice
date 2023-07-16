import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int tree[] = new int[1000000];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int max = 0, min = 0;
        for (int i = 0; i < n; i++) {
            int wood = Integer.parseInt(st.nextToken());
            tree[i] = wood;
            max = Math.max(max, wood);
        }
        int i = binSearch(tree, n, min, max, m, Long.MAX_VALUE, -1);

        System.out.println(i);
    }

    public static int binSearch(int[] tree, int n, int min, int max, int m, long near, int nearH){
        int h = (min + max) / 2;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.max(0, tree[i] - h);
        }
        if(sum == m){
            return h;
        }
        else{
            if(near > sum && m < sum) {
                near = sum;
                nearH = h;
            }
            if(max - min == 1)
                return nearH;
            else if(sum < m)
                return binSearch(tree, n, min, h, m, near, nearH);
            else
                return binSearch(tree, n, h, max, m, near, nearH);
        }

    }
}
