import java.util.*;

class Solution {
    private final int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
    public int solution(String[] storage, String[] requests) {
        char[][] border = new char[storage.length + 2][storage[0].length() + 2];
        
        for(int i = 1; i < border.length - 1; i++) {
            for(int j = 1; j < border[0].length -1; j++) {
                border[i][j] = storage[i-1].charAt(j-1);
            }
        }
        
        for(String request : requests) {
            Set<List<Integer>> set = new HashSet<>();
            char target = request.charAt(0);
            if(request.length() == 1) {
                bfsUpdate(border,target);   
            } else {
                for(int i = 1; i < border.length - 1; i++) {
                    for(int j = 1; j < border[0].length -1; j++) {
                        
                        if(border[i][j] == target) border[i][j] = '\u0000';
                    }
                }
            }
        }
        
        
        int answer = 0;
        
        for(int i = 1; i < border.length - 1; i++) {
            for(int j = 1; j < border[0].length -1; j++) {
                if(border[i][j] != '\u0000') {
                    answer++;
                }
                
            }
        }
        return answer;
    }
    
    private void bfsUpdate(char[][] border, char target) {
        boolean[][] vis = new boolean[border.length][border[0].length];
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[] {0,0});
        List<int[]> update = new ArrayList<>();
        vis[0][0] = true;
        while(!queue.isEmpty()) {
            var cur = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nx = cur[1] + dx[i];
                int ny = cur[0] + dy[i];
                
                if(nx < 0 || nx >= border[0].length || ny < 0 || ny >= border.length) continue;
                if(!vis[ny][nx] && border[ny][nx] == '\u0000') {
                    queue.offer(new int[] {ny, nx});
                    vis[ny][nx] = true;
                } else if(border[ny][nx] == target) {
                    update.add(new int[] {ny,nx});
                }
            }
        }
        for(int[] e: update) {
            border[e[0]][e[1]] = '\u0000';
        }
    }
} 