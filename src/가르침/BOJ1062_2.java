package 가르침;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1062_2 {
    static int N, K, ans, standard;
    static int[] word;

    public static int parseBin(char c){
        if(c == 'a') return 0;
        else if(c == 'b') return (int)Math.pow(2,0);
        else if(c == 'c') return 0;
        else if(c == 'd') return (int)Math.pow(2,1);
        else if(c == 'e') return (int)Math.pow(2,2);
        else if(c == 'f') return (int)Math.pow(2,3);
        else if(c == 'g') return (int)Math.pow(2,4);
        else if(c == 'h') return (int)Math.pow(2,5);
        else if(c == 'i') return 0;
        else if(c == 'j') return (int)Math.pow(2,6);
        else if(c == 'k') return (int)Math.pow(2,7);
        else if(c == 'l') return (int)Math.pow(2,8);
        else if(c == 'm') return (int)Math.pow(2,9);
        else if(c == 'n') return 0;
        else if(c == 'o') return (int)Math.pow(2,10);
        else if(c == 'p') return (int)Math.pow(2,11);
        else if(c == 'q') return (int)Math.pow(2,12);
        else if(c == 'r') return (int)Math.pow(2,13);
        else if(c == 's') return (int)Math.pow(2,14);
        else if(c == 't') return 0;
        else if(c == 'u') return (int)Math.pow(2,15);
        else if(c == 'v') return (int)Math.pow(2,16);
        else if(c == 'w') return (int)Math.pow(2,17);
        else if(c == 'y') return (int)Math.pow(2,18);
        else if(c == 'x') return (int)Math.pow(2,19);
        else return (int)Math.pow(2,20);
    }

    public static void dfs(int preChar, int depth){
        if(depth == K - 5){
            int res = 0;
            for (int i = 0; i < N; i++) {
//                System.out.println(Long.toBinaryString(word[i]));
                if(word[i] == (standard & word[i])) res++;
            }
            ans = Math.max(ans,res);
            return;
        }

        for (int i = preChar; i < 21 - (K - 5 - depth - 1) ; i++) {

            standard |= (int)Math.pow(2,i);
//            System.out.printf("depth: %d, i: %d, bin: %s\n",depth, i, Long.toBinaryString(standard));

            dfs(i+1, depth+1);
            standard ^= (int)Math.pow(2,i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        word = new int[N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int ss = s.length();
            int bin = 0;
            for (int j = 4; j < ss - 4; j++) {
                // bin = bin | s.charAt(j) - 'a'
                bin |= parseBin(s.charAt(j));
            }
            word[i] = bin;
        }

        if(K <= 4) {
            System.out.println(0);
            return;
        }

        dfs(0,0);

        System.out.println(ans);
    }
}
