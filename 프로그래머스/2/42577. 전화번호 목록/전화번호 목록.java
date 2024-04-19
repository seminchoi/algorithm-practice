import java.util.*;

class Solution {
    public boolean solution(String[] phoneBook) {
        return checkPrefix(phoneBook);
    }
    
    public boolean checkPrefix(String[] phoneBook) {
        Arrays.sort(phoneBook);
        
        Set<String> set = new HashSet<>();
        for(String phone : phoneBook) {
            int length = phone.length();
            for(int i = 0; i < length; i++) {
                if(set.contains(phone.substring(0,i + 1))) {
                    return false;
                }
            }
            set.add(phone);
        }
        
        return true;
    }
}