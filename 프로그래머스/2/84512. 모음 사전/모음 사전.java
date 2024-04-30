class Solution {
    static final char[] dic = {'A','E','I','O','U'};
    static int count = 0, answer = 0;
    
    public int solution(String word) {
        backTracking(word, "", 0);
        return answer - 1;
    }
    
    private static void backTracking(String target, String cur, int depth) {
        count++;
        
        if(target.equals(cur)) {
            answer = count;
            return;
        }
        if(depth == 5) {
            return;
        }
        
        for(int i = 0; i < 5; i++) {
            backTracking(target, cur + dic[i], depth + 1);
        }
    }
}