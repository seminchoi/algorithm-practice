package 가르침;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1062 {
    static int N, K, ans;
    static Long[] word;

    public static Long parseBin(char c){
        int tmp =(int)Math.pow(2,c-'a');
        return tmp & 0xFFFFFFFFL;
    }

    public static void dfs(int preChar, Long standard, int depth){
        if(depth == K - 5){
            int res = 0;
            System.out.println(Long.toBinaryString(standard) + "\n");
            for (int i = 0; i < N; i++) {
                System.out.println(Long.toBinaryString(word[i]));
                if(word[i] == (standard & word[i])) res++;
            }
            System.out.println();
            ans = ans < res ? res : ans;
            return;
        }
        for (int i = preChar; i < 26 - (K - 5 - depth); i++) {
            if(i == 'a' - 'a' || i == 'c' - 'a' || i == 'n' - 'a' || i == 'i' - 'a' || i == 't' - 'a')
                continue;
            standard |= parseBin((char)(i+'a'));
            dfs(preChar+1, standard, depth+1);
            standard ^= parseBin((char)(i+'a'));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(K <= 4) {
            System.out.println(0);
            return;
        }

        word = new Long[N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int ss = s.length();
            Long bin = 0L;
            for (int j = 4; j < ss - 4; j++) {
                // bin = bin | s.charAt(j) - 'a'
                bin |= parseBin(s.charAt(j));
            }
            word[i] = bin;
        }

        Long standard = 0L;
        standard |= parseBin('a');
        standard |= parseBin('c');
        standard |= parseBin('n');
        standard |= parseBin('i');
        standard |= parseBin('t');

        dfs(1,standard,0);

        System.out.println(ans);
    }
}
