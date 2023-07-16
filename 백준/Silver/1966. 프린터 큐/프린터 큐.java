import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Docs{
        int imp;
        boolean isTarget;

        public Docs(int imp, boolean isTarget) {
            this.imp = imp;
            this.isTarget = isTarget;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            List<Docs> docsQueue = new ArrayList<>();
            List<Integer> impList = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int imp = Integer.parseInt(st.nextToken());
                docsQueue.add(new Docs(imp, j == m));
                impList.add(imp);
            }
            impList.sort(Comparator.reverseOrder());
            int seq = 1;
            int idx = 0;
            for (int j = 0; j < n; j++) {
                int imp = impList.get(j), size = docsQueue.size();
                idx = idx % size;
                while(docsQueue.get(idx).imp != imp){
                    idx = (idx + 1) % size;
                }
                Docs docs = docsQueue.get(idx);
                if(docs.isTarget){
                    sb.append(seq).append("\n");
                    break;
                }
                else{
                    seq++;
                    docsQueue.remove(idx);

                }
            }
        }

        System.out.println(sb);
    }
}
