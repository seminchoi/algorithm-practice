import java.util.*;

class Solution {
    public String solution(String number, int k) {
        return replaceNumber(number, k);
    }
    
    public String replaceNumber(String number, int k) {
        Set<Integer> replaceIndexes = new HashSet<>();
        int preIndex = 0;
        int curIndex = 1;
        while(replaceIndexes.size() < k && curIndex < number.length()) {
            while(preIndex >= 0 && number.charAt(curIndex) > number.charAt(preIndex) && replaceIndexes.size() < k) {
                replaceIndexes.add(preIndex);
                preIndex--;
            }
            preIndex = curIndex;
            curIndex++;
        }
        
        return remakeNumber(number, k, replaceIndexes);
    }
    
    private String remakeNumber(String number, int k, Set<Integer> replaceIndexes) {
        StringBuilder stringBuilder = new StringBuilder();
        
        int maxCheckLength = number.length() - (k - replaceIndexes.size());
        for(int i = 0; i < maxCheckLength; i++) {
            if(replaceIndexes.contains(i)) {
                continue;
            }
            stringBuilder.append(number.charAt(i));
        }
        
        return stringBuilder.toString();
    }
}