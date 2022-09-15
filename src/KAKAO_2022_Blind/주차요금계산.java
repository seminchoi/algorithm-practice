package KAKAO_2022_Blind;

import java.util.*;

public class 주차요금계산 {
    static public int timeParse(String[] time){
        int hour = Integer.parseInt(time[0]);
        int min = Integer.parseInt(time[1]);
        return hour*60 + min;
    }

    class Solution {
        public int[] solution(int[] fees, String[] records) {
            int[] isIn = new int[10000];
            boolean[] inHis = new boolean[10000];
            boolean[] outHis = new boolean[10000];
            int[] duration = new int[10000];
            int inNum = 0;

            for(String record : records){
                StringTokenizer st = new StringTokenizer(record);
                String[] time = st.nextToken().split(":");
                int carNum = Integer.parseInt(st.nextToken());
                boolean history = st.nextToken().equals("IN");
                if(history){
                     isIn[carNum] = timeParse(time);
                     if(!inHis[carNum])
                         inNum++;
                     inHis[carNum] = true;
                     outHis[carNum] = false;
                }
                else{
                    duration[carNum] += timeParse(time) - isIn[carNum];
                    isIn[carNum] = 0;
                    outHis[carNum] = true;
                }
            }

            for (int i = 0; i < 10000; i++) {
                if(inHis[i] && !outHis[i]){
                    duration[i] += timeParse(new String[] {"23","59"}) - isIn[i];
                    System.out.println("duration = " + duration[i]);
                }
            }

            int[] answer = new int[inNum];
            int idx = 0;

            for (int i = 0; i < 10000; i++) {
                if (inHis[i]) {
                    answer[idx] = fees[1];
                    if (duration[i] > fees[0]) {
                        int overTime = duration[i] - fees[0];
                        answer[idx] += (Math.ceil((double) overTime / (double) fees[2])) * fees[3];
                    }
                    idx++;
                }
            }

            return answer;
        }
    }

}
