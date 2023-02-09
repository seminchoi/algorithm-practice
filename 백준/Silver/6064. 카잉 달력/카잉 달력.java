import java.util.Scanner;

public class Main {

    static int gcd(int a, int b){
        while( b!= 0 ){
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }

    static int lcm(int a, int b){
        return a*b/gcd(a,b);
    }

    static int cain(int[] c){


        int res = 0, cnt = 0,
                idx = c[0] > c[1] ? 0 : 1,
                revidx = idx == 0 ? 1 : 0,
                maxcnt = lcm(c[0],c[1])/c[idx];

        while(cnt < maxcnt){
            res = c[idx] * cnt + c[idx+2];
            if((res - c[revidx+2]) % c[revidx] == 0)
                return res;
            cnt++;
        }
        return -1;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for(int i = 0; i < N; i++){
            int[] c = new int[4];
            for(int j = 0; j < 4; j++)
                c[j] = sc.nextInt();
            System.out.println(cain(c));
        }
    }
}
