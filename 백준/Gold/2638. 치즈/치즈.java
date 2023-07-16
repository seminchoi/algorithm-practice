import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static boolean[][] board, vis;
    public static int n, m;
    public static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = st.nextToken().equals("1");
            }
        }

        int day = 0;
        while (!isCleanBoard()) {
            day++;
            meltCheese();
        }

        System.out.println(day);
    }

    public static void meltCheese() {
        boolean[][] copiedBoard = copyBoard();

        vis = new boolean[n][m];
        checkInside(copiedBoard);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j]) {
                    int exposedCount = 0;
                    for (int k = 0; k < 4; k++) {
                        if (!copiedBoard[i + dy[k]][j + dx[k]]) {
                            exposedCount++;
                        }
                    }

                    if (exposedCount >= 2) {
                        board[i][j] = false;
                    }
                }
            }
        }
    }

    public static boolean[][] copyBoard() {
        boolean[][] copyBoard = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }

        return copyBoard;
    }

    public static void checkInside(boolean[][] copiedBoard) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!copiedBoard[i][j] && !vis[i][j]) {
                    bfs(copiedBoard, i, j);
                }
            }
        }
    }

    public static void bfs(boolean[][] copiedBoard, int y, int x) {

        Queue<Point> checkQueue = new LinkedList<>(), memoryQueue = new LinkedList<>();
        checkQueue.offer(new Point(x, y));
        vis[y][x] = true;

        boolean isInside = true;

        while (!checkQueue.isEmpty()) {
            Point curPoint = checkQueue.poll();
            memoryQueue.offer(curPoint);

            for (int i = 0; i < 4; i++) {
                Point nextPoint = new Point(curPoint.x + dx[i], curPoint.y + dy[i]);

                if(isEdgeOfBoard(nextPoint))
                    isInside = false;
                
                if (!isOutOfBound(nextPoint) &&
                        !vis[nextPoint.y][nextPoint.x] &&
                        !copiedBoard[nextPoint.y][nextPoint.x]) {
                    vis[nextPoint.y][nextPoint.x] = true;
                    checkQueue.offer(nextPoint);
                }
            }
        }

        if (isInside) {

            while (!memoryQueue.isEmpty()) {
                Point point = memoryQueue.poll();
                copiedBoard[point.y][point.x] = true;
            }
        }
    }

    public static boolean isEdgeOfBoard(Point point) {
        return point.y == 0 || point.y == n - 1 || point.x == 0 || point.x == m - 1;
    }

    public static boolean isOutOfBound(Point point) {
        return point.y < 0 || point.y >= n || point.x < 0 || point.x >= m;
    }

    public static boolean isCleanBoard() {
        boolean isClean = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(board[i][j]) {
                    isClean = false;
                }
            }
        }
        return isClean;
    }
}
