package 스도쿠;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

//if 문이 너무 많음 예외처리를 적게 하는 방법을 모색해야 할 듯 함

public class BOJ2580_2 {
    static int[][] sudoku = new int[9][9];


    static boolean dfs(int x, int y){
        if(x == 9){
            if(y == 8)
                return true;
            x = 0; y += 1;
        }

        if(sudoku[y][x] != 0){
            return dfs(x+1,y);
        }

        boolean[] isPos = new boolean[10];

        int boxX = (x / 3) * 3, boxY = (y / 3) * 3;

        for (int i = 0; i < 9; i++) {
            //row 확인
            isPos[sudoku[y][i]] = true;
            //column 확인
            isPos[sudoku[i][x]] = true;
            //box 확인
            isPos[sudoku[boxY][boxX++]] = true;
            if(boxX % 3 == 0){
                boxX -= 3; boxY++;
            }
        }

        for (int i = 1; i < 10; i++) {
            if(!isPos[i]) {
                sudoku[y][x] = i;
                boolean res = dfs(x+1, y);
                if(res)
                    return true;

            }
        }
        sudoku[y][x] = 0;
        System.out.println("값을 찾지 못함");
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //스도쿠의 0인 부분을 채우는 반복문
        dfs(0,0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }
}
