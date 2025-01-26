import java.util.*;
import java.io.*;

public class Main {
    private static int n, m;
    private static int[][] board;
    private static int[][] command;
    private static List<Pos> clouds = new ArrayList<>();
    private static boolean[][] currentCloudsBoard;
    public static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    public static int[] copyDx = {-1, 1, 1, -1};
    public static int[] copyDy = {-1, -1, 1, 1};

    public static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        n = Integer.parseInt(input1[0]);
        m = Integer.parseInt(input1[1]);

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] input2 = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input2[j]);
            }
        }

        command = new int[m][2];
        for (int i = 0; i < m; i++) {
            String[] input3 = br.readLine().split(" ");
            command[i][0] = Integer.parseInt(input3[0]);
            command[i][1] = Integer.parseInt(input3[1]);
        }

        clouds.add(new Pos(0, n - 1));
        clouds.add(new Pos(1, n - 1));
        clouds.add(new Pos(0, n - 2));
        clouds.add(new Pos(1, n - 2));

        for (int i = 0; i < m; i++) {
            rainDown(i);
            createClouds();
        }

        int sum = Arrays.stream(board)
                .flatMapToInt(Arrays::stream)
                .sum();

        System.out.println(sum);
    }

    private static void rainDown(int turn) {
        currentCloudsBoard = new boolean[n][n];

        for (int i = 0; i < clouds.size(); i++) {
            Pos cloud = clouds.get(i);
            cloud.x = (cloud.x + n + ((dx[command[turn][0]] * command[turn][1])) % n) % n;
            cloud.y = (cloud.y + n + ((dy[command[turn][0]] * command[turn][1])) % n) % n;

            currentCloudsBoard[cloud.y][cloud.x] = true;
            board[cloud.y][cloud.x]++;
        }

        for (int i = 0; i < clouds.size(); i++) {
            copyWater(clouds.get(i));
        }
        clouds = new ArrayList<>();
    }

    private static void copyWater(Pos cloud) {
        for (int i = 0; i < 4; i++) {
            int diaX = cloud.x + copyDx[i];
            int diaY = cloud.y + copyDy[i];
            if (diaX >= 0 && diaX < n && diaY >= 0 && diaY < n && board[diaY][diaX] > 0) {
                board[cloud.y][cloud.x]++;
            }
        }
    }

    private static void createClouds() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] >= 2 && !currentCloudsBoard[i][j]) {
                    clouds.add(new Pos(j, i));
                    board[i][j] -= 2;
                }
            }
        }
    }
}