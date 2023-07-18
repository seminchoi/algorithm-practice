import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[][] original, shortest, longest;

    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        original = new int[n][3];
        longest = new int[n][3];
        shortest = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                original[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dpPath();

        printSolution();
    }

    public static void dpPath() {
        setFirstRow();

        for (int i = 1; i < n; i++) {
            dpShortestPathInRow(i);
            dpLongestPathInRow(i);
        }
    }

    public static void setFirstRow() {
        for (int i = 0; i < 3; i++) {
            shortest[0][i] = original[0][i];
            longest[0][i] = original[0][i];
        }
    }

    public static void dpLongestPathInRow(int row) {
        longest[row][0] = Math.max(longest[row - 1][0], longest[row - 1][1]) + original[row][0];
        longest[row][1] = maxFor3Args(longest[row - 1][0], longest[row - 1][1], longest[row - 1][2]) + original[row][1];
        longest[row][2] = Math.max(longest[row - 1][1], longest[row - 1][2]) + original[row][2];
    }

    public static void dpShortestPathInRow(int row) {
        shortest[row][0] = Math.min(shortest[row - 1][0], shortest[row - 1][1]) + original[row][0];
        shortest[row][1] = minFor3Args(shortest[row - 1][0], shortest[row - 1][1], shortest[row - 1][2]) + original[row][1];
        shortest[row][2] = Math.min(shortest[row - 1][1], shortest[row - 1][2]) + original[row][2];
    }

    public static int maxFor3Args(int arg1, int arg2, int arg3) {
        return Math.max(Math.max(arg1, arg2), arg3);
    }

    public static int minFor3Args(int arg1, int arg2, int arg3) {
        return Math.min(Math.min(arg1, arg2), arg3);
    }

    public static void printSolution() {
        System.out.printf("%d %d",
                maxFor3Args(longest[n - 1][0], longest[n - 1][1], longest[n - 1][2]),
                minFor3Args(shortest[n - 1][0], shortest[n - 1][1], shortest[n - 1][2]));
    }
}
