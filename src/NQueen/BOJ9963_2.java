package NQueen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9963_2 {
    static int N;
    //1차원 배열의 index 가 체스판의 Column, 배열 안의 값이 퀸을 놓은 row 가 된다.
    static int[] board;

    //이전 depth 에 현재 퀸을 놓으려는 자리를 공격할 수 있는 다른 퀸이 있는지 확인하는 메서드
    public static boolean isPossible(int depth){
        for (int j = 0; j < depth; j++) {
            if(board[j] == board[depth])
                return false;
            if(board[depth] == board[j] - (depth - j) || board[depth] == board[j] + (depth - j))
                return false;
        }
        return true;
    }

    public static int dfs(int depth, int res){
        if(depth == N-1){
            for(int i = 0; i < N; i++){
                board[depth] = i;

                if(isPossible(depth))
                    res = res+1;
            }
            return res;
        }
        for (int i = 0; i < N; i++) {
            board[depth] = i;
            if(isPossible(depth))
                res = dfs(depth + 1, res);
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N];

        System.out.println(dfs(0,0));

    }
}
