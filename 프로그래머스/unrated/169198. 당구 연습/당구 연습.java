class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        for(int i = 0; i < balls.length; i++) {
            int min = -1;
            
            if(!(startX == balls[i][0] && startY < balls[i][1])) {
                min = getMin(min, calculateDistance(startX, 2*n - startY, balls[i]));
            }
            if(!(startX == balls[i][0] && startY > balls[i][1])) {
                min = getMin(min, calculateDistance(startX, startY * -1, balls[i]));
            }
            if(!(startY == balls[i][1] && startX < balls[i][0])) {
                min = getMin(min, calculateDistance(2*m - startX, startY, balls[i]));
            }
            if(!(startY == balls[i][1] && startX > balls[i][0])) {
                min = getMin(min, calculateDistance(startX*-1, startY, balls[i]));
            }                 
            
            answer[i] = min;
        }
        
        return answer;
    }
    
    public int calculateDistance(int startX, int startY, int[] ball) {
        return (startX - ball[0]) * (startX - ball[0]) + (startY - ball[1]) * (startY - ball[1]);
    }
    
    public int getMin(int a, int b) {
        if(a == -1) {
            return b;
        }
        else if(b == -1) {
            return a;
        }
        else {
            return Integer.min(a, b);
        }
    }
}