import java.util.*;
import java.io.*;

public class Main {
    public static int n;
    public static int l;
    public static int r;

    public static int[][] board;
    public static boolean[][] rowBorderline;
    public static boolean[][] colBorderline;

    public static List<Set<Pos>> fe = new ArrayList<>();

    public static class Pos {
        public int x;
        public int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x && y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        l = Integer.parseInt(input[1]);
        r = Integer.parseInt(input[2]);

        board = new int[n][n];
        rowBorderline = new boolean[n-1][n];
        colBorderline = new boolean[n][n-1];

        for(int i = 0; i < n; i++) {
            String[] rowInput = br.readLine().split(" ");
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(rowInput[j]);
            }
        }

        int answer = 0;
        while(true) {
            updateBorderLine();

            if(fe.isEmpty()) {
                break;
            }

            for(Set<Pos> posSet: fe) {
                int sum = 0;
                for(Pos pos: posSet) {
                    sum += board[pos.y][pos.x];
                }
                int population = sum / posSet.size();
                for(Pos pos: posSet) {
                    board[pos.y][pos.x] = population;
                }
            }

            fe = new ArrayList<>();
            answer++;
        }

        System.out.println(answer);
    }

    public static void updateBorderLine() {
        //좌우 경계선 확인
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n-1; j++) {
                if(isInBound(board[i][j], board[i][j+1])) {
                    Pos cur1 = new Pos(j, i);
                    Pos cur2 = new Pos(j+1, i);
                    boolean con = false;
                    for(Set<Pos> posSet: fe) {
                        if(posSet.contains(cur1)) {
                            posSet.add(cur2);
                            con = true;
                            break;
                        }
                    }
                    if(!con) {
                        HashSet<Pos> set = new HashSet<>();
                        set.add(cur1);
                        set.add(cur2);
                        fe.add(set);
                    }
                }
            }
        }

        //상하 경계선 확인
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n-1; j++) {
                if(isInBound(board[j][i], board[j+1][i])) {
                    Pos cur1 = new Pos(i, j);
                    Pos cur2 = new Pos(i, j+1);
                    Set<Pos> set1 = null;
                    Set<Pos> set2 = null;

                    for(Set<Pos> posSet: fe) {
                        if(posSet.contains(cur1)) {
                            set1 = posSet;
                        }
                        if(posSet.contains(cur2)) {
                            set2 = posSet;
                        }
                        if(set1 != null && set2 != null) {
                            break;
                        }
                    }

                    if(set1 != null && set2 != null && set1 != set2) {
                        set1.addAll(set2);
                        fe.remove(set2);
                    }
                    if(set1 == null && set2 != null) {
                        set2.add(cur1);
                    }
                    if(set1 != null && set2 == null) {
                        set1.add(cur2);
                    }
                    if(set1 == null && set2 == null) {
                        HashSet<Pos> set = new HashSet<>();
                        set.add(cur1);
                        set.add(cur2);
                        fe.add(set);
                    }
                }
            }
        }
    }

    public static boolean isInBound(int a, int b) {
        int diff = Math.abs(a - b);
        return diff >= l && diff <= r;
    }
}