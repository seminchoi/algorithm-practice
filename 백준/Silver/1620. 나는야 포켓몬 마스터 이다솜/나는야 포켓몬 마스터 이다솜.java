import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class PocketInfo implements Comparable<PocketInfo>{
        int idx;
        String s;

        public PocketInfo(int idx, String s) {
            this.idx = idx;
            this.s = s;
        }

        @Override
        public int compareTo(PocketInfo p) {
            return s.compareTo(p.s);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] arr = new String[n];
        PocketInfo[] sortedArr = new PocketInfo[n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
            sortedArr[i] = new PocketInfo(i+1, arr[i]);
        }

        Arrays.sort(sortedArr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String s = br.readLine();
            if(isInteger(s)){
                int idx = Integer.parseInt(s);
                sb.append(arr[idx-1]+"\n");
            }
            else{
                sb.append(binSearch(sortedArr, s, 0, n-1)+"\n");
            }
        }

        System.out.println(sb);
    }

    public static boolean isInteger(String s){
        try{
            Integer.parseInt(s);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public static int binSearch(PocketInfo[] arr, String s, int bottom, int top){
        int mid = (bottom + top) / 2;

        int comp = s.compareTo(arr[mid].s);
        if(comp == 0) {
            return arr[mid].idx;
        }
        else if(comp < 0){
            return binSearch(arr, s, bottom, mid);

        }
        else{
            return binSearch(arr, s, mid + 1, top);

        }
    }
}
