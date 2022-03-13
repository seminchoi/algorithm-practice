import java.util.*;

public class BOJ1107 {

    static ArrayList<Integer> ch;
    static boolean[] btn;

    // 입력할 수 있는 채널 중 목표 채널보다 작은 숫자중 가장 큰 숫자 sch
    // 입력할 수 있는 채널 중 목표 채널보다 큰 숫자중 가장 작은 숫자 lch
    static int[] sch, lch;
    static int N, br;

    //sch와 lch에 채널 복사
    static void fillCh() {
        int size = ch.size();
        for (int i = 0; i < size; i++) {
            int chval = ch.get(i);
            sch[i] = chval;
            lch[i] = chval;
        }
    }

    static int getMaxBtn(){
        int idx = 9;
        while(!btn[idx]){
            idx--;
        }
        return idx;
    }

    static int getMaxBtn(int max){
        int idx = max - 1;
        while(idx >= 0 && !btn[idx] ){
            idx--;
        }
        return idx;
    }


    static int getMinBtn(){
        int idx = 0;
        while(!btn[idx]){
            idx++;
        }
        return idx;
    }

    static int getMinBtn(int min){
        int idx = min + 1;
        while(idx <= 9 && !btn[idx]){
            idx++;
        }
        return idx >= 10 ? -1 : idx;
    }

    static int calch(){
        int res = 0, sRes = 0, lRes = 0;
        //고장난 버튼이 없을경우 idx는 -2
        int idx = -1;
        for(int i = 0; i < ch.size(); i++){
            if(!btn[ch.get(i)]) {
                idx = i;
                break;
            }
        }
        //고장난 버튼이 없을 경우 채널의 자리수를 그대로 반환
        if(idx == -1){
            return ch.size();
        }

        int maxbtn = getMaxBtn();
        if(maxbtn == 0)
            return res = N + 1;

        if(N >= getMinBtn()) {
            //sidx는 loop가 멈춘 sch의 idx
            for (int i = idx; i >= -1; i--) {
                if (i == -1) {
                    sch[0] = 0;
                    for (int j = 1; j < sch.length; j++)
                        sch[j] = maxbtn;
                    break;
                }
                sch[i] = getMaxBtn(sch[i]);
                if (sch[i] != -1) {
                    for (int j = i + 1; j < sch.length; j++)
                        sch[j] = maxbtn;
                    break;
                }
            }

            for (int i = sch.length - 1, dec = 1; i >= 0; i--, dec *= 10) {
                sRes += sch[i] * dec;
            }

            int dig = ch.size();
            for (int i = 0; i < sch.length; i++)
                if (sch[i] != 0) {
                    dig = dig - i;
                    break;
                }
            res = N - sRes + dig;
        }

        int minbtn = getMinBtn();
        for(int i = idx; i >= -1; i--){
            if(i == -1) {
                lch[0] = getMinBtn(0)*10 + minbtn;
                for(int j = 1; j < lch.length; j++ )
                    lch[j] = minbtn;
                lRes++;
                break;
            }
            lch[i] = getMinBtn(lch[i]);
            if(lch[i] != -1) {
                for(int j = i + 1; j < lch.length; j++ )
                    lch[j] = minbtn;
                break;
            }
        }

        //입력한 뒷자리수 부터는 모두 입력할 수 있는 수 중 가장 작은 수를 입력

        for(int i = lch.length - 1, dec = 1; i >= 0; i--, dec *= 10){
            lRes += lch[i] * dec;
        }
        if(res == 0)
            return  lRes - N + ch.size();
        res = Math.min(res, lRes - N + ch.size());
        return res;
    }

    public static void main (String[] args){

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int min = Math.abs(100 - N);
        ch = new ArrayList<>();

        for(int i = N; i != 0; i /= 10)
            ch.add(i % 10);

        //ch에 담긴 채널 자리수를 정방향으로 뒤집기
        Collections.reverse(ch);

        br = sc.nextInt();
        btn = new boolean[10];
        Arrays.fill(btn,true);

        //고장난 버튼의 index를 false로 저장
        for(int i = 0; i < br; i++ ){
            int n = sc.nextInt();
            btn[n] = false;
        }

        if(min == 0){
            System.out.println(min);
            return;
        }
        else if(br == 10){
            System.out.println(min);
            return;
        }
        else if (N == 0){
            if(!btn[0]){
                System.out.println(getMinBtn()+1);
                return;
            }
            else{
                System.out.println(1);
                return;
            }

        }

        sch = new int[ch.size()];
        lch = new int[ch.size()];

        fillCh();
        min = Math.min(min, calch());

        System.out.println(min);
    }
}
