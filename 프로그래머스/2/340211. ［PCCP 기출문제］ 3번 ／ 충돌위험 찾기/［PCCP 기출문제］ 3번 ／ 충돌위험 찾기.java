import java.util.*;

class Solution {
    static class Position {
        int r;
        int c;
        boolean isCounted;
        
        public Position(int r, int c) {
            this.r = r;
            this.c = c;
            isCounted = false;
        }
        
        public boolean equals(Object o) {
            if(this == o) return true;
            if(o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return r == position.r && c == position.c;
        }
        
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
    
    public int solution(int[][] points, int[][] routes) {        
        int answer = 0;

        Map<Integer, Set<Position>> map = new HashMap<>();
        initMap(map, points, routes);
        for(Integer keys : map.keySet()) {
            Set<Position> set = map.get(keys);
            for(Position pos : set) {
                if(pos.isCounted) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    private void initMap(Map<Integer, Set<Position>> map, int[][] points, int[][] routes) {
        for(int[] route: routes) {
            List<Position> positions = recordRoute(route, points);
            int curSize = positions.size();
            // System.out.println(curSize);
            for(int i = 0; i < curSize; i++) {
                Set<Position> set = map.getOrDefault(i, new HashSet<>());
                
                if(set.contains(positions.get(i))) {
                    
                    set.remove(positions.get(i));
                    positions.get(i).isCounted = true;
                    set.add(positions.get(i));
                }
                else {
                    set.add(positions.get(i));
                }
                map.put(i, set);
            }
        }
    }
    
    private List<Position> recordRoute(int[] route, int[][] points) {
        List<Position> positions = new ArrayList<Position>();
        
        positions.add(new Position(points[route[0] - 1][0], points[route[0] - 1][1]));
        
        for(int i = 1; i < route.length; i++) {
            int curPoint = route[i - 1] - 1;
            int nextPoint = route[i] - 1;
            
            int curR = points[curPoint][0];
            int curC = points[curPoint][1];
            int targetR = points[nextPoint][0];
            int targetC = points[nextPoint][1];
            int drDirection = targetR - curR > 0 ? 1 : -1;
            int drDistance = Math.abs(targetR - curR);
            int dcDirection = targetC - curC > 0 ? 1 : -1;
            int dcDistance = Math.abs(targetC - curC);
            
            for(int j = 0; j < drDistance; j++) {
                positions.add(new Position(curR + drDirection, curC));
                curR += drDirection;
            }
            
            for(int j = 0; j < dcDistance; j++) {
                positions.add(new Position(curR, curC + dcDirection));
                curC += dcDirection;
            }
        }
        
        return positions;
    }
}