package 알파벳;

// 방법1. Set을 이용해서 알파벳이 포함됐는지 Set.contains를 이용해 확인하며 dfs를 진행한다.
// 방법2. 알파벳 갯수 만큼의 크기를 가진 boolean 배열을 만들어서 포함 여부를 확인한다.
// 각 문자의index는 문자 - 'A' 값이다.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BOJ1987 {
    static int R, C, res;
    //우,하,좌,상 순서
    static int[] xPos = {1,0,-1,0}, yPos = {0,1,0,-1};
    static char[][] board;
    static boolean[] isContain = new boolean['Z' - 'A' + 1];

    static void dfs(int x, int y, int depth){
        if(depth > res)
            res = depth;

        isContain[board[y][x] - 'A'] = true;
//        System.out.printf("%s \t",board[y][x]);

        for (int i = 0; i < 4; i++) {
            int nextX = x + xPos[i], nextY = y+ yPos[i];
            if(nextX >= 0 && nextX < C && nextY >= 0 && nextY < R
                    && !isContain[board[nextY][nextX] - 'A']){
                dfs(nextX,nextY,depth+1);
            }
        }
        isContain[board[y][x] - 'A'] = false;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        dfs(0,0,1);

        System.out.println(res);
    }
}
