import java.util.StringTokenizer;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] answer = {};
        
        int[] dx = {0,0,-1,1};
        int[] dy = {-1,1,0,0};
        int h = park.length;
        int w = park[0].length();
        int x = 0, y = 0;
        
        loop : for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(park[i].charAt(j) == 'S'){
                    y = i; x = j;
                    break loop;
                }
            }
        }
        
        
        for(String s : routes){
            StringTokenizer st = new StringTokenizer(s);
            char dirChar = st.nextToken().charAt(0);
            int dir;
            int weight = Integer.parseInt(st.nextToken());
            
            int curX = x, curY = y;
            if(dirChar == 'N'){
                dir = 0;
            }
            else if(dirChar == 'S'){
                dir = 1;
            }
             else if(dirChar == 'W'){
                dir = 2;
            }
            else{
                dir = 3;
            }
            
            for(int i = 0; i < weight; i++){
                curX += dx[dir];
                curY += dy[dir];
                
                if(curX < 0 || curX > w - 1 || curY < 0 || curY > h - 1 ||
                   park[curY].charAt(curX) == 'X'){
                    curX = x;
                    curY = y;
                    break;
                }
            }
            
            x = curX;
            y = curY;
        }
        
        answer = new int[2];
        answer[0] = y;
        answer[1] = x;
        return answer;
    }
}