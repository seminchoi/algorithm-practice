import java.util.*;

class Solution {
    private int pointDiff = Integer.MIN_VALUE;
    private int[] answer = new int[11];
        
    public int[] solution(int n, int[] info) {
        // 어피치가 안쏜곳은 한번이상 쏠 필요 없다.
        // 어피치가 쏜 곳은 어피치가 쏜 것 보다 한번 이상 쏘거나 쏘지 않는다.
        // 화살이 남으면 1점에 다 몰빵한다.
        
        play(new int[11], n, info, 0);
        
        if(pointDiff <= 0) {
            return new int[] {-1};
        }

        return answer;
    }
    
    private void play(int[] result, int n, int[] info, int idx) {
        if(idx == 11) {
            result[10] += n;
            int curDiff = calculatePointDiff(result, info);

            if(pointDiff < curDiff 
               || (pointDiff == curDiff && lower(result))) {
                pointDiff = curDiff;
                for(int i = 0; i < 11; i++) {
                    answer[i] = result[i];
                }
            }
            result[10] -= n;
            return;
        } 
        
        if(n > info[idx]) {
            result[idx] = info[idx] + 1; 
            play(result, n - result[idx], info, idx + 1);
            result[idx] = 0;
        } 
        
        play(result, n, info, idx + 1);
    }
    
    private int calculatePointDiff(int[] lion, int[] apeach) {
        int lionPoint = 0;
        int apeachPoint = 0;
        for(int i = 0; i < 11; i++) {
            if(lion[i] > apeach[i]) {
                lionPoint += 10 - i;
            } else if(apeach[i] > 0) {
                apeachPoint += 10 - i;
            }
        }
        
        return lionPoint - apeachPoint;
    }
    
    private boolean lower(int[] result) {
        for(int i = 10; i >= 0; i--) { 
            if(result[i] > answer[i]) {
                return true;  
            }
            if(result[i] < answer[i]) {
                return false;
            }
        }
        return false;
    }
}