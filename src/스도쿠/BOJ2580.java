package 스도쿠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2580 {
    static int num = 0;
    static int[] row = new int[9], col = new int[9], box = new int[9];
    static int[][] sudoku = new int[9][9];

    public static void rowCheck(int r){
        boolean[] checkNum = new boolean[10];
        int c = 0;
        for (int i = 0; i < 9; i++) {
            if(sudoku[r][i] == 0)
                c = i;
            checkNum[sudoku[r][i]] = true;
        }
        for (int i = 1; i < 10; i++) {
            if(!checkNum[i]){
                row[r]--; col[c]--; box[c / 3 + ((r / 3) * 3)]--;
                num--;
                sudoku[r][c] = i;
            }
        }
    }

    public static void colCheck(int c){
        boolean[] checkNum = new boolean[10];
        int r = 0;
        for (int i = 0; i < 9; i++) {
            if(sudoku[i][c] == 0)
                r = i;
            checkNum[sudoku[i][c]] = true;
        }

        for (int i = 0; i < 9; i++) {
            if(!checkNum[i]){
                row[r]--; col[c]--; box[c / 3 + ((r / 3) * 3)]--;
                num--;
                sudoku[r][c] = i;
            }
        }
    }

    public static void boxCheck(int b){
        boolean[] checkNum = new boolean[10];
        int r = 0,c = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(sudoku[i + (b / 3) * 3][j + (b % 3) * 3] == 0) {
                    r = i + (b / 3) * 3; c = j + (b % 3) * 3;
                }
                checkNum[sudoku[i + (b / 3) * 3][j + (b % 3) * 3]] = true;
            }
        }

        for (int i = 1; i < 10; i++) {
            if(!checkNum[i]){
                row[r]--; col[c]--; box[b]--;
                num--;
                sudoku[r][c] = i;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int n = Integer.parseInt(st.nextToken());
                sudoku[i][j] = n;
                if (n == 0) {
                    num++;
                    row[i]++;
                    col[j]++;
                    box[((i / 3) * 3) + (j / 3)]++;
                }
            }
        }

        while (num > 0){
            for (int i = 0; i < 9; i++) {
                if(row[i] == 1)
                    rowCheck(i);
                if(col[i] == 1)
                    colCheck(i);
                if(box[i] == 1)
                    boxCheck(i);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }
}

//for (int i = 0; i < 9; i++) {
//for (int j = 0; j < 9; j++) {
//System.out.print(sudoku[i][j] + " ");
//}
//System.out.println();
//}
//System.out.println();