import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public static class Score implements Comparable<Score> {
        int score1;
        int score2;
        
        public Score(int score1, int score2) {
            this.score1 = score1;
            this.score2 = score2;
        }
        
        public int compareTo(Score a) {
            if(score1 == a.score1) {
                return score2 - a.score2;
            }
            else {
                return a.score1 - score1;
            }
        }
    }
    public int solution(int[][] scores) {
        int answer = 0;
        
        
        int[] wanScore = scores[0];
        List<Score> scoreList = Arrays.stream(scores).map(score -> new Score(score[0], score[1])).collect(Collectors.toList());
        
        Collections.sort(scoreList);
        
        int max = 0;
        for(int i = 0; i < scoreList.size(); i++) {
            Score score = scoreList.get(i);

            if(score.score1 > wanScore[0] && score.score2 > wanScore[1]) {
                return -1;
            }
            
            if(score.score2 >= max) {
                if(wanScore[0] + wanScore[1] < score.score1 + score.score2)
                    answer++;
                max = score.score2;
            }
        }
        
        return answer + 1;
    }
}