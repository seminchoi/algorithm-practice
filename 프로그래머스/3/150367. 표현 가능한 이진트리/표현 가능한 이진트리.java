import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String binaryString = Long.toBinaryString(numbers[i]);
            Set<Integer> validLength = new HashSet<>(Arrays.asList(new Integer[]{1, 3, 7, 15, 31, 63}));
            answer[i] = isExpressible(binaryString, validLength);
        }

        return answer;
    }

    private int isExpressible(String binaryString, Set<Integer> validLength) {
        binaryString = createBinaryString(binaryString, validLength);

        while (binaryString.length() > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < binaryString.length(); i += 4) {
                char check = checkDivide(binaryString, i);
                if (check == '2') {
                    return 0;
                }
                stringBuilder.append(check);
                if (i + 3 < binaryString.length()) {
                    stringBuilder.append(binaryString.charAt(i + 3));
                }
            }
            binaryString = stringBuilder.toString();
        }

        return 1;
    }
    
    private String createBinaryString(String binaryString, Set<Integer> validLength) {
        int length = binaryString.length();
        StringBuilder stringBuilder = new StringBuilder();
        while (!validLength.contains(length)) {
            stringBuilder.append('0');
            length++;
        }
        stringBuilder.append(binaryString);
        return stringBuilder.toString();
    }

    private char checkDivide(String binaryString, int start) {
        if (binaryString.charAt(start) == '1' || binaryString.charAt(start + 2) == '1') {
            if (binaryString.charAt(start + 1) == '0') {
                return '2';
            }
        }

        return binaryString.charAt(start + 1);
    }
}