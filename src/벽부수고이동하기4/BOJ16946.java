package 벽부수고이동하기4;

import java.io.*;
import java.util.*;

public class BOJ16946 {
    static int n,m,clusterNum = 0;
    static boolean[][] isWall, vis;
    static int[][] board;
    static ArrayList<Integer> clusterCnt = new ArrayList<>();
    static int[] xPos = {1,0,-1,0}, yPos = {0,1,0,-1};
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void dfs(int x, int y){
        vis[y][x] = true;
        clusterCnt.set(clusterNum, clusterCnt.get(clusterNum)+1);
        board[y][x] = clusterNum;
        for (int i = 0; i < 4; i++) {
            int nextX = x+xPos[i], nextY = y+yPos[i];

            if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n &&
                    !vis[nextY][nextX]) {
                dfs(nextX, nextY);
            }
        }
    }

    static void setResBoard() throws IOException{
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int res = 0;
                if (isWall[i][j]) {
                    res = 1;
                    List<Integer> addedCluster = new ArrayList<>();
                    for (int k = 0; k < 4; k++) {
                        int nextX = j + xPos[k], nextY = i + yPos[k];
                        if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n &&
                                !isWall[i + yPos[k]][j + xPos[k]] &&
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

        isWall = new boolean[n][m];
        vis = new boolean[n][m];
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                if(s.charAt(j) == '1') {
                    vis[i][j] = true;
                    isWall[i][j] = true;
                }
                board[i][j] = s.charAt(j) - '0';
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!vis[i][j]) {
                    clusterCnt.add(0);
                    dfs(j, i);
                    clusterNum++;
                }
            }
        }

        setResBoard();

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(resBoard[i][j] % 10);
//            }
//            System.out.println();
//        }
        bw.flush();
        br.close();
        bw.close();
    }
}
