import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(long n, String[] bans) {
        // 알파벳을 진법으로 10진수로 변환합니다.
        // 10진수 변환 후 list를 만듭니다.
        // 10진수로 변환해서 n보다 작으면 n값을 더합니다.
        // n을 알파벳으로 변환합니다.
        List<Long> list = convertAll(bans);
        for(Long l : list) {
            if(l <= n) {
                n++;
            }
        }
        
        return nuToEn(n - 1);
    }
    
    private List<Long> convertAll(String[] bans) {
        List<Long> list = Stream.of(bans).map(this::enToNu).collect(Collectors.toList());
        list.sort((a, b) -> a - b > 0 ? 1 : -1);
        return list;
    }
    
    private long enToNu(String ban) {
        long nu = 0;
        long mult = 1;
        for(int i = ban.length() - 1; i >= 0 ; i--) {
            nu += mult;
            nu += (ban.charAt(i) - 'a') * mult;
            mult *= 26;
        }

        return nu;
    }
    
    private String nuToEn(long n) {
        String s = "";
        
        long digit = 1;
        long mult = 26;
        while(n - mult >= 0) {
            n -= mult;
            mult *= 26;
            digit++;
        }
        while(digit > 0) {
            long mod = n % 26;
            char c = (char) ((int) mod + (int) 'a');
            s = c + s;
            if(n > 0) {
                n /= 26;
            }
            digit --;
        }
        return s;
    }

}