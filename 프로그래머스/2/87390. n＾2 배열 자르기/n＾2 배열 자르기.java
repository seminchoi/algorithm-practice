class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left + 1)];
        
        for(long i = left, idx = 0; i <= right; i++, idx++) {
            int value = (int) Math.max(i / n, i % n) + 1;
            answer[(int)idx] = value;
        }
        
        return answer;
    }
}