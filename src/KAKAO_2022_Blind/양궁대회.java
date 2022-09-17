package KAKAO_2022_Blind;

public class 양궁대회 {
    class Solution {
        static int[] lion = new int[11], res = new int[11];
        static int max = 0;

        static void dfs(int[] info, int depth, int rem){
            if(depth == 0 || rem == 0){
                lion[0] = rem;
                for(int i = 0; i < 11; i++){
                    System.out.print(lion[i] + " ");
                }
                int aPoint = 0, lPoint = 0;
                for(int i = 0; i < 11; i++){
                    if(info[i] < lion[i]){
                        lPoint += 10 - i;
                    }
                    else if(info[i]==0 & lion[i] == 0){
                        continue;
                    }
                    else{
                        aPoint += 10 - i;
                    }
                }
                System.out.println("lPoint:" + lPoint + ", aPoint: " + aPoint + ", diff " + (lPoint - aPoint));
                if(max < lPoint - aPoint) {
                    max = lPoint - aPoint;
                    res = lion.clone();
                }
                return;
            }
            for(int i = rem; i >= 0; i--){
                lion[depth] = i;
                dfs(info, depth-1, rem - i);
                lion[depth] = 0;
            }
        }

        static public int[] solution(int n, int[] info) {

            dfs(info, 10, n);
            for(int i = 0; i < 11; i++){
                System.out.print(res[i] + " ");
            }
            System.out.println("\n max = " + max);
            int[] answer = res;
            return answer;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[] info = {2,1,1,1,0,0,0,0,0,0,0};
        Solution.solution(n,info);
    }
}
