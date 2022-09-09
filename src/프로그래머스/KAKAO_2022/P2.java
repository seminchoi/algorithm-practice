package 프로그래머스.KAKAO_2022;

import java.util.Queue;
import java.util.LinkedList;

//30분
public class P2 {
    class Solution {
        public int solution(int[] queue1, int[] queue2) {
            long oneSum = 0, twoSum = 0;
            int oneCnt = 0, twoCnt = 0, len = queue1.length;
            int answer = 0;


            Queue<Integer> q1 = new LinkedList<>();
            Queue<Integer> q2 = new LinkedList<>();

            for(int i = 0; i < queue1.length; i++) {
                oneSum += queue1[i];
                q1.offer(queue1[i]);
                twoSum += queue2[i];
                q2.offer(queue2[i]);
            }

            if((oneSum + twoSum) % 2 != 0){
                return -1;
            }

            while(oneSum != twoSum){
                if(oneCnt >= len && twoCnt >= len){
                    return -1;
                }
                if(oneSum > twoSum){
                    int n = q1.poll();
                    oneSum -= n;
                    q2.offer(n);
                    twoSum += n;
                    oneCnt++;
                    answer++;
                }
                else {
                    int n = q2.poll();
                    twoSum -= n;
                    q1.offer(n);
                    oneSum += n;
                    twoCnt++;
                    answer++;
                }
            }
            return answer;
        }
    }
}
