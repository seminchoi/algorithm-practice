package 벽부수고이동하기4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ16946_2 {
    static int n,m,clusterNum = 2;
    static int[][] board;
    static ArrayList<Integer> clusterCnt = new ArrayList<>();
    static int[] xPos = {1,0,-1,0}, yPos = {0,1,0,-1};
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int dfs(int x, int y){
        int size = 1;
        board[y][x] = clusterNum;
        for (int i = 0; i < 4; i++) {
            int nextX = x+xPos[i], nextY = y+yPos[i];
            if(board[nextY][nextX] == 0) {
                size += dfs(nextX, nextY);
            }
        }
        return size;
    }

    static void setResBoard() throws IOException{
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                int res = 0;
                if (board[i][j] == 1) {
                    res = 1;
                    List<Integer> addedCluster = new ArrayList<>();
                    for (int k = 0; k < 4; k++) {
                        int nextX = j + xPos[k], nextY = i + yPos[k];
                        if (board[nextY][nextX] > 1 &&
                        !addedCluster.contains(board[nextY][nextX])) {
                            res += clusterCnt.get(board[nextY][nextX]);
                            addedCluster.add(board[nextY][nextX]);
                        }
                    }
                }
                bw.write((res % 10)+"");
            }
            bw.newLine();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+2][m+2];

        for (int i = 0; i < n+2; i++) {
            board[i][0]=1;board[i][m+1]=1;
        }
        for (int i = 0; i < m+2; i++) {
            board[0][i]=1;board[n+1][i]=1;
        }

        for (int i = 1; i < n+1; i++) {
            String s = br.readLine();
            for (int j = 1; j < m+1; j++) {
                board[i][j] = s.charAt(j-1) - '0';
            }
        }
        clusterCnt.add(0);
        clusterCnt.add(0);
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if(board[i][j] == 0) {
                    clusterCnt.add(dfs(j, i));
                    clusterNum++;
                }
            }
        }

        setResBoard();

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(board[i][j] % 10);
//            }
//            System.out.println();
//        }
//        System.out.println();

        bw.flush();
        br.close();
        bw.close();
    }
}
