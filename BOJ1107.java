import java.util.*;

public class BOJ1107 {
    public static void main (String[] args){

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), min = Math.abs(100 - N);
        ArrayList<Integer> ch = new ArrayList<>();

        for(int i = N; i != 0; i /= 10)
            ch.add(i % 10);

        int br = sc.nextInt();
        boolean[] btn = new boolean[10];
        Arrays.fill(btn,true);

        for(int i = 0; i < br; i++ ){
            int n = sc.nextInt();
            btn[n] = false;
        }


        int idx, chidx;
        int[] sch = new int[ch.size()];
        int[] lch = new int[ch.size()];
        for( idx = ch.size() - 1, chidx = 0; idx >= 0; idx--, chidx++){
            if(btn[ch.indexOf(idx)]) {
                sch[chidx] = ch.indexOf(idx);
                lch[chidx] = ch.indexOf(idx);
                break;
            }
            else{
                sch[chidx] = ch.indexOf(idx);
                lch[chidx] = ch.indexOf(idx);
            }
        }

        while(!btn[Math.abs(sch[chidx])])
            sch[chidx]--;

        while(!btn[lch[chidx] % 10])
            lch[chidx]++;

        int btnmax = 9, btnmin = 0;
        while(!btn[btnmin])
            btnmin++;

        while(!btn[btnmax])
            btnmax--;

        for(int i = chidx + 1; i < ch.size(); i++) {
            sch[i] = btnmax;
            lch[i] = btnmin;
        }

        int lidx = chidx;
        while(lidx > 0 && (lch[lidx] > 9 || !btn[lch[lidx]]) {
            lch
        }

    }
}
