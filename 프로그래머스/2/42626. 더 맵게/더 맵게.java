import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] scoville, int K) {
        Queue<Integer> queue = new PriorityQueue(Arrays.stream(scoville).boxed().collect(Collectors.toList()));
        return makeSpicy(queue, K);
    }
    
    public int makeSpicy(Queue<Integer> queue, int K) {
        int mixCount = 0;
        while(queue.size() > 1 && queue.peek() < K) {
            mixCount++;
            mixFood(queue);
        }
        
        if(queue.peek() < K) {
            return -1;
        }
        return mixCount;
    }
    
    public void mixFood(Queue<Integer> queue) {
        int firstLeastSpicy = queue.poll();
        int secondLeastSpicy = queue.poll();
        queue.offer(firstLeastSpicy + secondLeastSpicy * 2);
    }
}