import java.util.*;
import java.util.Collections.*;

class Solution {
    static class Process {
        int idx;
        int priority;
        
        public Process(int idx, int priority) {
            this.idx = idx;
            this.priority = priority;
        }
    }
    
    public int solution(int[] priorities, int location) {
        Queue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Process> processQueue = new LinkedList<>();
        for(int i = 0; i < priorities.length; i++) {
            priorityQueue.offer(priorities[i]);
            processQueue.offer(new Process(i, priorities[i]));
        }
        
        
        int answer = 1;
        
        while(true) {
            if(processQueue.peek().idx == location && priorityQueue.peek() == processQueue.peek().priority) {
                return answer;
            }
            
            if(priorityQueue.peek() == processQueue.peek().priority) {
                priorityQueue.poll();
                processQueue.poll();
                answer++;
                continue;
            }
            
            Process process = processQueue.poll();
            processQueue.offer(process);
        }
    }
}