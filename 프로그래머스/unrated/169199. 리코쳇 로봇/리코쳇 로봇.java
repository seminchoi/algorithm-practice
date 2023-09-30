import java.util.*;

class Solution {
    
    public int[] dx = {1, 0, -1, 0};
    public int[] dy = {0, 1, 0, -1};
    
    public static class Element {
        int x;
        int y;
        int depth;
        
        public Element(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
    
    public int solution(String[] board) {
        int answer = -1;
        int startX = 0, startY = 0;
        
        boolean[][] vis = new boolean[board.length][board[0].length()];
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'R') {
                    startY = i;
                    startX = j;
                    vis[startY][startX] = true;
                }
            }
        }
        
    
        
        Queue<Element> queue = new LinkedList<>(); 
        queue.offer(new Element(startX, startY, 0));
        
        while(!queue.isEmpty()) {
            Element cur = queue.poll();
            if(getChar(board, cur) == 'G') {
                answer = cur.depth;
                break;
            }
            
            for(int i = 0; i < 4; i++) {
                Element nextElement = getNextElement(board, cur, i);
                int nextX = nextElement.x, nextY = nextElement.y;
                if(!vis[nextY][nextX]) {
                    queue.offer(new Element(nextX, nextY, cur.depth + 1));
                    vis[nextY][nextX] = true;
                }
            }
        }
        
        return answer;
    }
    
    public char getChar(String[] board, Element e) {
        return board[e.y].charAt(e.x);
    }
    
    public boolean isOutOfIndex(String[] board, int x, int y) {
        return x < 0 || x >= board[0].length() || y < 0 || y >= board.length;
    }
    
    public Element getNextElement(String[] board, Element e, int dir) {
        int x = e.x, y = e.y;
        while(!isOutOfIndex(board, x, y) && board[y].charAt(x) != 'D') {
            x += dx[dir];
            y += dy[dir];
        }
        
        x -= dx[dir];
        y -= dy[dir];
        
        return new Element(x, y, e.depth + 1);
    }
}