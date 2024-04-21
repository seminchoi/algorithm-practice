import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        return calculateBoat(people, limit);
    }
    
    private int calculateBoat(int[] people, int limit) {
        int start = 0, end = people.length - 1;
        int answer = 0;
        while(start <= end) {
            if(start == end) {
                answer++;
                break;
            }
            
            if(people[start] + people[end] > limit) {
                answer++;
                end--;
            }
            else {
                answer++;
                start++;
                end--;
            }
        }
        return answer;
    }
}