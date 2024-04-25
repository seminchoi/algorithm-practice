import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        return calculateCases(classificate(clothes));
    }
    
    private Map<String, Integer> classificate(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        
        for(String[] clothe : clothes) {
            int count = map.getOrDefault(clothe[1], 0);
            map.put(clothe[1], count+1);
        }
        
        return map;
    }
    
    private int calculateCases(Map<String, Integer> clothes) {
        int cases = 1;
        for(Integer count : clothes.values()) {
            cases *= count + 1;
        }
        
        return cases - 1;
    }
}