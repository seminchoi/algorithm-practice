import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        List<Integer> sizes = createList(tangerine);
        sizes.sort(Collections.reverseOrder());
        
        int answer = 0;
        int sum = 0;
        for(int size : sizes) {
            sum += size;
            answer++;
            
            if(sum >= k) {
                break;
            }
        }

        return answer;
    }
    
    private List<Integer> createList(int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int size : tangerine) {
            int count = map.getOrDefault(size, 0);
            map.put(size, count + 1);
        }
        
        return new ArrayList<>(map.values());
    }
}