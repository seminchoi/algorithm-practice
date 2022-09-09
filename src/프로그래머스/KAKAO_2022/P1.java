package 프로그래머스.KAKAO_2022;


//30분
public class P1 {
    class Solution {
        public int getIdx(char c){
            if(c=='R'){
                return 0;
            }
            else if(c=='T'){
                return 1;
            }
            else if(c=='C'){
                return 2;
            }
            else if(c=='F'){
                return 3;
            }
            else if(c=='J'){
                return 4;
            }
            else if(c=='M'){
                return 5;
            }
            else if(c=='A'){
                return 6;
            }
            else {
                return 7;
            }
        }
        public char getChar(int idx){
            if(idx == 0){
                return 'R';
            }
            else if(idx == 1){
                return 'T';
            }
            else if(idx == 2){
                return 'C';
            }
            else if(idx == 3){
                return 'F';
            }
            else if(idx == 4){
                return 'J';
            }
            else if(idx == 5){
                return 'M';
            }
            else if(idx == 6){
                return 'A';
            }
            else {
                return 'N';
            }
        }
        public String solution(String[] survey, int[] choices) {
            int[] res = new int[8];

            for(int i=0; i < survey.length; i++){
                if(choices[i] < 4){
                    int idx = getIdx(survey[i].charAt(0));
                    res[idx] += 4 - choices[i];
                }
                else{
                    int idx = getIdx(survey[i].charAt(1));
                    res[idx] += choices[i] - 4;
                }
            }
            String answer = "";

            for(int i = 0; i < 4; i++){
                if(res[i*2] >= res[i*2 + 1]){
                    answer += getChar(i*2);
                }
                else{
                    answer += getChar(i*2+1);
                }
            }
            return answer;
        }
    }
}
