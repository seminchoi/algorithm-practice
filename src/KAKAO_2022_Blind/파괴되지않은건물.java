package KAKAO_2022_Blind;

//누적합 개념을 2차원 배열에 적용합니다.
public class 파괴되지않은건물 {
    class Solution {
        static public int solution(int[][] board, int[][] skill) {
            //스킬 인덱스
            //0 : 타입 (1 = 공격, 2 = 파괴), 1: y1, 2: x1, 3: y2, 4: x2, 5: 데미지
            int[][] cal = new int[board.length+1][board[0].length+1];
            for(int[] sk : skill){
                if(sk[0] == 1)
                    sk[5] = -sk[5];
                cal[sk[1]][sk[2]] += sk[5];
                cal[sk[1]][sk[4]+1] -= sk[5];
                cal[sk[3]+1][sk[2]] -= sk[5];
                cal[sk[3]+1][sk[4]+1] += sk[5];
            }


            for(int i = 0; i < board.length+1; i++){
                for(int j = 0; j < board[0].length+1; j++){
                    System.out.print(cal[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[0].length-1; j++){
                    cal[i][j+1] += cal[i][j];
                }
            }

            for(int i = 0; i < board.length+1; i++){
                for(int j = 0; j < board[0].length+1; j++){
                    System.out.print(cal[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            for(int i = 0; i < board[0].length; i++){
                for(int j = 0; j < board.length-1; j++){
                    cal[j+1][i] += cal[j][i];
                }
            }

            for(int i = 0; i < board.length+1; i++){
                for(int j = 0; j < board[0].length+1; j++){
                    System.out.print(cal[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            int answer = 0;
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[0].length; j++){
                    if(cal[i][j] + board[i][j] > 0)
                        answer++;
                }
            }
            return answer;
        }

    }
    public static void main(String[] args) {
        int[][] board = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
        int[][] skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
        Solution.solution(board,skill);
    }
}
