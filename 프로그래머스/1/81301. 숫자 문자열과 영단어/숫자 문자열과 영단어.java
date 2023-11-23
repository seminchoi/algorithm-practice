class Solution {
    public static final String[] numberWords = new String[] {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    };

    public int solution(String s) {
        for (int i = 0; i < 10; i++) {
            String numberWord = numberWords[i];
            s = s.replaceAll(numberWord, String.valueOf(i));
        }

        return Integer.parseInt(s);
    }
}