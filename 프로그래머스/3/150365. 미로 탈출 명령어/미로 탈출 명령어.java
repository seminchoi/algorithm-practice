class Solution {
    private static final String IMPOSSIBLE = "impossible";

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        // 아래 : +y, 위 : -y, 왼쪽 : -x, 오른쪽 : +x
        int distance = calculateManhattanDistance(x, y, r, c);

        if (!canEscape(distance, k)) {
            return IMPOSSIBLE;
        }

        return getRoute(n, m, x, y, r, c, k);
    }


    private boolean canEscape(int distance, int k) {
        if (k < distance || distance % 2 != k % 2) {
            return false;
        }
        return true;
    }

    private String getRoute(int n, int m, int x, int y, int r, int c, int k) {
        int curDistance = 0;
        StringBuilder sb = new StringBuilder();
        while (curDistance < k) {
            char nextDirection = getNextDirection(n, m, x, y, r, c, k, curDistance);
            sb.append(nextDirection);
            curDistance++;

            if (nextDirection == 'd') {
                x++;
            }
            if (nextDirection == 'l') {
                y--;
            }
            if (nextDirection == 'r') {
                y++;
            }
            if (nextDirection == 'u') {
                x--;
            }
        }

        return sb.toString();
    }

    private char getNextDirection(int n, int m, int x, int y, int r, int c, int k, int distance) {
        if (canGo(n, m, x + 1, y, r, c, k, distance + 1)) {
            return 'd';
        }
        if (canGo(n, m, x, y - 1, r, c, k, distance + 1)) {
            return 'l';
        }
        if (canGo(n, m, x, y + 1, r, c, k, distance + 1)) {
            return 'r';
        }
        if (canGo(n, m, x - 1, y, r, c, k, distance + 1)) {
            return 'u';
        }
        throw new RuntimeException();
    }

    private boolean canGo(int n, int m, int x, int y, int r, int c, int k, int distance) {
        return isValidPosition(n, m, x, y) && isValidDistance(x, y, r, c, k, distance);
    }

    private boolean isValidPosition(int n, int m, int x, int y) {
        return x >= 1 && x <= n && y >= 1 && y <= m;
    }

    private boolean isValidDistance(int x, int y, int r, int c, int k, int distance) {
        int remainDistance = calculateManhattanDistance(x, y, r, c);
        return distance + remainDistance <= k;
    }

    private int calculateManhattanDistance(int x, int y, int r, int c) {
        return Math.abs(x - r) + Math.abs(y - c);
    }
}