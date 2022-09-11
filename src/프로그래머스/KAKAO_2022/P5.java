package 프로그래머스.KAKAO_2022;

public class P5 {
    static class Solution {
        static public int[][] rotate(int[][] rc, int cnt){
            int row = rc.length, col = rc[0].length;
            int[][] newRc = new int[row][col];
            for(int i = 0; i < row; i++){
                for(int j = 0; j < col; j++){
                    if(i == 0 || i == row-1 || j == 0 || j == col -1){
                        int pos = 0;
                        if(i==0)
                            pos += j;
                        else if (j == col - 1)
                            pos += col - 1 + i ;
                        else if (i == row-1)
                            pos += col - 1 + row - 1 + (col - 1 - j);
                        else
                            pos += col - 1 + row - 1 + col - 1 + (row - 1 - i);
                        pos = (pos + cnt) % (row * 2 + (col-2) * 2 );
                        int dx, dy;
                        if(pos < col){
                            dx = pos;
                            dy = 0;
                        }
                        else if(pos < col + row - 1){
                            dx = col -1;
                            dy = pos - col + 1;
                        }
                        else if (pos < col + row + col - 2){
                            dx = (col - 1) - (pos - (row + col - 2));
                            dy = row - 1;
                        }
                        else{
                            dx = 0;
                            dy = (row - 1) - (pos - (row + col + row - 3));
                        }
                        newRc[dy][dx] = rc[i][j];
                    }
                    else{
                        newRc[i][j] = rc[i][j];
                    }
                    for (int l = 0; l < 3; l++) {
                        for (int k = 0; k < 3; k++) {
                            System.out.print(newRc[l][k]);
                        }
                        System.out.println();
                    }
                    System.out.println();
                }
            }
            return newRc;
        }
        static public int[][] shift(int[][] rc, int cnt){
            int row = rc.length, col = rc[0].length;
            int[][] newRc = new int[row][col];
            for(int i = 0; i < row; i++){
                int newCol = (i + cnt) % row;
                for(int j = 0; j < col; j++){
                    newRc[newCol][j] = rc[i][j];
                }
            }
            return newRc;
        }
        static public int[][] solution(int[][] rc, String[] operations) {
            int cnt = 0;
            String oper;
            for(int i = 0; i < operations.length; i++){
                if(i < operations.length - 1 && operations[i].equals(operations[i+1])){
                    cnt++;
                    continue;
                }
                oper = operations[i];
                cnt++;
                if(oper == "ShiftRow"){
                    rc = shift(rc,cnt);
                }
                else
                    rc = rotate(rc,cnt);

                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        System.out.print(rc[j][k]);
                    }
                    System.out.println();
                }
                System.out.println();
                cnt = 0;
            }
            int[][] answer = rc;
            return answer;
        }
    }

    public static void main(String[] args) {
        int[][] rc = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        String[] oper = {"Rotate", "ShiftRow"};
        System.out.println(Solution.solution(rc,oper));
    }
}
