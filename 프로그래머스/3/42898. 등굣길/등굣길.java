class Solution {
    static final int MOD = 1_000_000_007;
    static int[][] map;
    public int solution(int m, int n, int[][] puddles) {
        init(m, n, puddles);
        search(m, n);
        
        return map[n-1][m-1];
    }
    
    private void init(int m, int n, int[][] puddles) {
        map = new int[n][m];
        
        for(int[] puddle : puddles) {
            map[puddle[1]-1][puddle[0]-1] = -1;
        }
        
        for(int i = 0; i < n; i++) {
            if(map[i][0] == -1) {
                break;
            } 
            map[i][0] = 1;
        }
        
        for(int i = 0; i < m; i++) {
            if(map[0][i] == -1) {
                break;
            } 
            map[0][i] = 1;
        }
    }
    
    private void search(int m, int n) {
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(map[i][j] == -1) {
                    continue;
                }
                if(map[i-1][j] != -1) {
                    map[i][j] += map[i-1][j];
                }
                if(map[i][j-1] != -1) {
                    map[i][j] += map[i][j-1];
                }
                map[i][j] %= MOD;
            }
        }
    }
}