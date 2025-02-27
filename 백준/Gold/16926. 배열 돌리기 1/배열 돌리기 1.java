import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");

        int y = Integer.parseInt(input1[0]);
        int x = Integer.parseInt(input1[1]);
        int r = Integer.parseInt(input1[2]);

        int[][] square = new int[y][x];
        for (int i = 0; i < y; i++) {
            String[] input2 = br.readLine().split(" ");
            for (int j = 0; j < x; j++) {
                square[i][j] = Integer.parseInt(input2[j]);
            }
        }
        List<Queue<Integer>> linears = unfold(square, x, y);
        int[][] rotatedSquare = rotation(linears, x, y, r);

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(rotatedSquare[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static List<Queue<Integer>> unfold(int[][] square, int x, int y) {
        List<Queue<Integer>> linears = new ArrayList<>();

        int min = Math.min(x, y);

        for (int i = 0; i < min / 2; i++) {
            int curX = i; int curY = i;
            Queue<Integer> linear = new LinkedList<>();
            linears.add(linear);
            int dx = 1;
            int dy = 0;
            do {
                linear.add(square[curY][curX]);
                curX += dx;
                curY += dy;

                if (dx == 1 && curX + dx == x - i) {
                    dx = 0;
                    dy = 1;
                }
                if (dx == -1 && curX + dx == i - 1) {
                    dx = 0;
                    dy = -1;
                }
                if (dy == 1 && curY + dy == y - i) {
                    dx = -1;
                    dy = 0;
                }
            } while (curX != i || curY != i);
        }

        return linears;
    }

    private static int[][] rotation(List<Queue<Integer>> linears, int x, int y, int r) {
        for (int i = 0; i < linears.size(); i++) {
            Queue<Integer> linear = linears.get(i);
            int curR = r % linear.size();
            for (int j = 0; j < curR; j++) {
                int n = linear.poll();
                linear.add(n);
            }
        }

        int[][] square = new int[y][x];

        for (int i = 0; i < linears.size(); i++) {
            int curX = i; int curY = i;

            Queue<Integer> linear = linears.get(i);
            int dx = 1;
            int dy = 0;
            do {

                square[curY][curX] = linear.poll();
                curX += dx;
                curY += dy;

                if (dx == 1 && curX + dx == x - i) {
                    dx = 0;
                    dy = 1;
                }
                if (dx == -1 && curX + dx == i - 1) {
                    dx = 0;
                    dy = -1;
                }
                if (dy == 1 && curY + dy == y - i) {
                    dx = -1;
                    dy = 0;
                }
            } while(!linear.isEmpty());
        }

        return square;
    }
}