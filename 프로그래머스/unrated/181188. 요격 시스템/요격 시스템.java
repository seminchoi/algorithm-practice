import java.util.Arrays;


class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (t1, t2) -> t1[1] - t2[1]);

        int answer = 1;
        int fire = targets[0][1];
        for(int[] target : targets) {
            if(fire <= target[0]) {
                fire = target[1];
                answer++;
            }
        }
        return answer;
    }
}