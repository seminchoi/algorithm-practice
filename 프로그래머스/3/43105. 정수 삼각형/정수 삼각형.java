import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int maxDepth = triangle.length;
        for(int i = 1; i < maxDepth; i++) {
            for(int j = 0; j <= i; j++) {
                if(j == 0) {
                    triangle[i][j] += triangle[i-1][j];
                }
                else if(j == i) {
                    triangle[i][j] += triangle[i-1][j-1];
                }
                else {
                    triangle[i][j] += Math.max(triangle[i-1][j], triangle[i-1][j-1]);
                }
            }
        }
        
        return Arrays.stream(triangle[maxDepth-1]).max().getAsInt();
    }
}