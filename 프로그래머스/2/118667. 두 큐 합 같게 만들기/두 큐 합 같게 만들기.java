import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long oneSum = 0, twoSum = 0;
        int oneCnt = 0, twoCnt = 0, len1 = queue1.length, len2 = queue2.length;
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
            if(oneCnt >= len1+len2 || twoCnt >= len1+len2){
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