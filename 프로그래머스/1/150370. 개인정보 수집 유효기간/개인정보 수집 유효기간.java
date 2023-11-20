import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
public int[] solution(String today, String[] terms, String[] privacies) {
        LocalDate todayDate = LocalDate.parse(today, DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        Map<Character, Integer> termData = new HashMap<>();
        for (String term : terms) {
            String[] parsedTerm = term.split(" ");
            char type = parsedTerm[0].charAt(0);
            int termNumber = Integer.parseInt(parsedTerm[1]);
            termData.put(type, termNumber);
        }

        List<Integer> expiresId = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i];
            String[] parsedPrivacy = privacy.split(" ");
            LocalDate createdAt = LocalDate.parse(parsedPrivacy[0], DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            char type = parsedPrivacy[1].charAt(0);
            if(isExpired(termData, todayDate, createdAt, type)) {
                expiresId.add(i + 1);
            }
        }

        return expiresId.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }


   private boolean isExpired(Map<Character, Integer> terms, LocalDate today, LocalDate createdAt, char type) {
        int term = terms.get(type);
        LocalDate expireDate = createdAt.plusMonths(term);
        return expireDate.isBefore(today) || today.isEqual(expireDate);
    }
}