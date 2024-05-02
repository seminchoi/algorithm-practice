class Solution {
    static int count = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return count;
    }
    
    private void dfs(int[] numbers, int target, int depth, int curNumber) {
        if (depth == numbers.length) {
            if (target == curNumber) {
                count++;
            }
            return;
        } 
        
        dfs(numbers, target, depth + 1, curNumber + numbers[depth]);
        dfs(numbers, target, depth + 1, curNumber - numbers[depth]);
    }
}