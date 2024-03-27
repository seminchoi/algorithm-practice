import java.util.*;

class Solution {
    public static boolean[][] vis = null;
    public static int dfsCount = 0;
    public static List<Integer> stayDate = new ArrayList<>();
    public static int[] dx = {-1,0,1,0}, dy = {0,-1,0,1};
    
    public int[] solution(String[] maps) {
        vis = new boolean[maps.length][maps[0].length()];
        
        for(int y = 0; y < maps.length; y++) {
            for(int x = 0; x < maps[0].length(); x++) {
                if(!vis[y][x] && maps[y].charAt(x) != 'X') {
                    stayDate.add(0);
                    dfs(maps,x,y);
                    dfsCount++;
                }
            }
        }
        
        if(stayDate.size() == 0) {
            return new int[] {-1};
        }
        
        stayDate.sort(Comparator.naturalOrder());
        return stayDate.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    private void dfs(String[] maps, int x, int y) {
        vis[y][x] = true;
        stayDate.set(dfsCount, stayDate.get(dfsCount) + getDate(maps,x,y));
        
        for(int i = 0; i < 4; i++) {
            int nextX = x + dx[i], nextY = y + dy[i];
            if(nextX >= 0 && nextX < maps[0].length() && nextY >= 0 && nextY < maps.length && !vis[nextY][nextX] && maps[nextY].charAt(nextX) != 'X' ) {
                dfs(maps, nextX, nextY);
            }
        }
    }
    
    private int getDate(String[] maps, int x, int y) {
        return maps[y].charAt(x) - '0';
    }
}