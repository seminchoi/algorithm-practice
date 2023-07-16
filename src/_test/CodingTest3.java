package _test;


import java.util.LinkedList;
import java.util.Queue;

public class CodingTest3 {
    class Solution {
        class Ship{
            int x;
            int y;
            int direction;
            int time;

            public Ship(int x, int y, int direction, int time) {
                this.x = x;
                this.y = y;
                this.direction = direction;
                this.time = time;
            }
        }
        public int solution(String[] worldmap) {
            //방향은 0~7 까지 있음
            int answer = 0;
            int n = worldmap[0].length();
            int m = worldmap.length;
            boolean[][] vis = new boolean[m][n];

            Queue<Ship> queue = new LinkedList<>();
            queue.offer(new Ship(0,0,3,0));
            vis[0][0] = true;

            while(!queue.isEmpty()){
                Ship curShip = queue.poll();

                if(curShip.x == n-1 && curShip.y == m-1) {
                    answer = curShip.time;
                    break;
                }

                for (int i = -1; i < 2; i++) {
                    int nextDir = (curShip.direction + i) % 8, nextX = curShip.x, nextY = curShip.y;

                    if(nextDir == 0){
                        nextX -= 1;
                        nextY -= 1;
                    }
                    else if (nextDir == 1){
                        nextY = -1;
                    }
                    else if (nextDir == 2){
                        nextX += 1;
                        nextY -= 1;
                    }
                    else if (nextDir == 3){
                        nextX += 1;
                    }
                    else if (nextDir == 4){
                        nextX += 1;
                        nextY += 1;
                    }
                    else if (nextDir == 5){
                        nextY += 1;
                    }
                    else if (nextDir == 6){
                        nextX -= 1;
                        nextY += 1;
                    }
                    else {
                        nextX -= 1;
                    }

                    if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < m &&
                    !vis[nextY][nextX] && worldmap[nextY].charAt(nextX) == '.'){
                        boolean isPos = true;
                        if(nextDir % 2 == 0){
                            if(worldmap[curShip.y].charAt(nextX) == 'X')
                                isPos = false;
                            if(worldmap[nextY].charAt(curShip.x) == 'X')
                                isPos = false;
                        }
                        if(worldmap[nextY].charAt(nextX) == 'X')
                            isPos = false;

                        if(isPos) {
                            vis[curShip.y][curShip.x] = true;
                            queue.offer(new Ship(nextX, nextY, nextDir, curShip.time + 1));
                        }
                    }
                }
            }

            return answer;
        }
    }
}
