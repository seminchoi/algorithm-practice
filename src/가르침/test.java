package 가르침;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class test {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int k, n, num;
    static int [] x;
    static int ans = 0;

    //조합
    static void dfs(int newc, int cnt, int learn ) throws IOException {
        if( cnt == k ) {
            int max_cnt = 0;
            for( int i=0; i<n; i++) {
                if( ( learn & x[i] ) == x[i] ) {
                    max_cnt++;
                }
            }
            if( ans < max_cnt ) {
                ans = max_cnt;
            }
            return;
        }

        for( int i=newc; i<26; i++ ) {
            num++;
            if ( ( learn & (1 << i)) == 0  ) {
                learn |= ( 1 << i );
                dfs(i+1, cnt+1, learn);
                learn &= ~(1 << i );
            }
        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st= new StringTokenizer( br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int learn = ( 1 << ( 'a' - 'a' ) );
        learn |= ( 1 << ( 'n' - 'a' ) );
        learn |= ( 1 << ( 't' - 'a' ) );
        learn |= ( 1 << ( 'i' - 'a' ) );
        learn |= ( 1 << ( 'c' - 'a' ) );

        x = new int[n];
        for( int i=0; i<n ; i++ ) {
            char comm[] = br.readLine().toCharArray();
            for( int j=0; j<comm.length; j++ ) {
                x[i] |= ( 1 << ( comm[j] - 'a' ) );
            }
        }
        if( k < 5 ) bw.write("0\n");
        else {
            dfs( 0, 5, learn );
            bw.write( ans + "\n");
        }
        System.out.println(num);
        br.close();
        bw.flush();
        bw.close();
    }
}