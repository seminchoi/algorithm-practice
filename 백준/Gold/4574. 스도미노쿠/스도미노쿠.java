import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int cnt = 1;
    static int[][] sudoku = new int[9][9];
    static boolean[][] block;


    static boolean[] playSudoku(int x, int y, int x1, int y1){
        boolean[] isPos = new boolean[20];

        int boxX = (x / 3) * 3, boxY = (y / 3) * 3;
        int boxX1 = (x1 / 3) * 3, boxY1 = (y1 / 3) * 3;


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
            //row 확인
            isPos[sudoku[y1][i]+10] = true;
            //column 확인
            isPos[sudoku[i][x1]+10] = true;
            //box 확인
            isPos[sudoku[boxY1][boxX1++]+10] = true;
            if(boxX1 % 3 == 0){
                boxX1 -= 3; boxY1++;
            }
        }
        return isPos;
    }

    static boolean dfs(int x, int y){
        if(x == 9){
            if(y == 8)
                return true;
            x = 0; y += 1;
        }

        if(sudoku[y][x] != 0){
            return dfs(x+1,y);
        }
        else if((y+1 != 9 && sudoku[y+1][x] != 0) && (x+1 != 9 && sudoku[y][x+1] != 0))
            return false;

        boolean res;
        if(x+1 != 9 && sudoku[y][x+1] == 0){
            boolean[] isPos = playSudoku(x,y,x+1,y);

            for (int i = 1; i < 10; i++) {
                if(!isPos[i]){
                    for (int j = 1; j < 10; j++) {
                        if(i!=j && !isPos[j+10]){
                            if(!block[i][j]) {
                                block[i][j] = true; block[j][i] = true;
                                sudoku[y][x] = i; sudoku[y][x+1] = j;
//                                System.out.printf("%d %d %d %d\n",y,x,i,j);
//                                for (int k = 0; k < 9; k++) {
//                                    for (int t = 0; t < 9; t++) {
//                                        System.out.print(sudoku[k][t] + " ");
//                                    }
//                                    System.out.println();
//                                }
//                                System.out.println();
                                res = dfs(x + 2, y);
                                if(res)
                                    return true;
                                block[i][j] = false; block[j][i] = false;
                            }
                        }
                    }
                }
            }
            sudoku[y][x] = 0; sudoku[y][x+1] = 0;
        }

        if(y+1 != 9 && sudoku[y+1][x] == 0){
            boolean[] isPos = playSudoku(x,y,x,y+1);

            for (int i = 1; i < 10; i++) {
                if(!isPos[i]){
                    for (int j = 1; j < 10; j++) {
                        if(i!=j && !isPos[j+10]){
                            if(!block[i][j]) {
                                block[i][j] = true; block[j][i] = true;
                                sudoku[y][x] = i; sudoku[y+1][x] = j;
//                                for (int k = 0; k < 9; k++) {
//                                    for (int t = 0; t < 9; t++) {
//                                        System.out.print(sudoku[k][t] + " ");
//                                    }
//                                    System.out.println();
//                                }
//                                System.out.println();
                                res = dfs(x+1,y);
                                if(res)
                                    return true;
                                block[i][j] = false; block[j][i] = false;
                            }
                        }
                    }
                }
            }
            sudoku[y][x] = 0; sudoku[y+1][x] = 0;

            return false;
        }


        sudoku[y][x] = 0;
//        System.out.println("값을 찾지 못함");
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            block = new boolean[10][10];
            sudoku = new int[9][9];
            int N = Integer.parseInt(br.readLine());
            if(N == 0)
                return;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int[] blockN = new int[2];
                for (int j = 0; j < 2; j++) {
                    blockN[j] = Integer.parseInt(st.nextToken());
                    String s = st.nextToken();
                    int yPos = s.charAt(0)-'A';
                    int xPos = s.charAt(1)-'1';
                    sudoku[yPos][xPos] = blockN[j];
                }
                block[blockN[0]][blockN[1]] = true; block[blockN[1]][blockN[0]] = true;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i < 10; i++) {
                String s = st.nextToken();
                int yPos = s.charAt(0)-'A';
                int xPos = s.charAt(1)-'1';
                sudoku[yPos][xPos] = i;
            }

            dfs(0,0);

            System.out.println("Puzzle " + cnt++);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(sudoku[i][j]);
                }
                System.out.println();
            }
        }
    }
}
