import java.util.*;

class Solution {
    
    public int[] solution(int[] numbers) {
        Stack<Integer> stack = new Stack<>();
        
        int[] answer = new int[numbers.length]; 
        answer[numbers.length - 1] = -1;
        stack.push(numbers[numbers.length - 1]);
        for(int i = numbers.length - 2; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peek() <= numbers[i]) {
                stack.pop();
            }
            
            if(stack.isEmpty()) {
                answer[i] = -1;
            } else {
                answer[i] = stack.peek();
            }
            
            stack.push(numbers[i]);
        }
  
        return answer;
    }
}