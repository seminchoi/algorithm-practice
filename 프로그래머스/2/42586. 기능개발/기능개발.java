import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int curIndex = 0;
        List<Integer> deliveryCounts = new ArrayList<>();
        while(curIndex < progresses.length) {
            doProgress(progresses, speeds);
            int nextIndex = checkDeliveryIndex(progresses, curIndex);
            if(curIndex < nextIndex) {
                deliveryCounts.add(nextIndex-curIndex);
                curIndex = nextIndex;
            }
        }
        
        return deliveryCounts.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private void doProgress(int[] progresses, int[] speeds) {
        int length = progresses.length;
        for(int i = 0; i < length; i++) {
            progresses[i] += speeds[i];
            progresses[i] = progresses[i] >= 100 ? 100 : progresses[i];
        }
    }
    
    private int checkDeliveryIndex(int[] progresses, int curIndex) {
        while(curIndex < progresses.length && progresses[curIndex] >= 100) {
            curIndex++;
        }
        return curIndex;
    }
}