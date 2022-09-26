package 동전0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11047 {
    static int n,k,coin = 0;
    static int[] aPrice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        aPrice = new int[n];
        for (int i = 0; i < n; i++) {
            aPrice[i] = Integer.parseInt(br.readLine());
        }

        while (k != 0){
            for (int i = n-1; i >= 0; i--) {
                while (aPrice[i] <= k){
                    coin += k / aPrice[i];
                    k %= aPrice[i];
                }
            }
        }
        System.out.println(coin);
    }
}
