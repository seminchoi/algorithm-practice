class Solution {
    public long solution(int r1, int r2) {
        long pointCount1 = calculateEntirePoints(r1, true);
        long pointCount2 = calculateEntirePoints(r2, false);
        
        return pointCount2 - pointCount1;
    }
    
    public long calculateEntirePoints(int r, boolean inner) {
        return calculateOneQuadrantPoints(r, inner) * 4;
    }
    
    public long calculateOneQuadrantPoints(int r, boolean inner) {
        long sum = 0L;
        for (int i = 1; i <= r; i++) {
            sum += calculateAxisPoints(r, i, inner);
        }
        return sum;
    }
    
    public long calculateAxisPoints(int r, int x, boolean inner) {
        double y = Math.sqrt((long)r*r - (long)x*x);
        long points = (long) y;
        if((double)(long) y == y && inner) {
            points -= 1;
        }
        return points + 1;
    }
}