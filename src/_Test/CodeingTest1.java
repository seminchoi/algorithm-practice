package _Test;

public class CodeingTest1 {
    class Solution {
        public String solution(String line) {
            char curC = line.charAt(0);
            int cnt = 0;
            String answer = "";

            for (int i = 1; i < line.length(); i++) {
                if(curC == line.charAt(i)){
                    cnt++;
                    continue;
                }
                else{
                    answer += curC;
                    if(cnt >= 1){
                        answer += "*";
                    }
                    curC = line.charAt(i);
                    cnt = 0;
                }
            }

            answer += curC;
            if(cnt >= 1){
                answer += "*";
            }

            return answer;
        }
    }
}
