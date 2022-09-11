package 프로그래머스.KAKAO_2022;

import java.util.*;

public class P4 {
    static class Solution {
        static public int solution(int alp, int cop, int[][] problems) {
            int maxAlp = 0, maxCop = 0, minAlp=151, minCop = 151;
            for(int[] p : problems){
                maxAlp = maxAlp < p[0] ? p[0] : maxAlp;
                maxCop = maxCop < p[1] ? p[1] : maxCop;
            }

            Queue<int []> q = new LinkedList<>();
            int[][] vis = new int[maxAlp][maxCop];
            for(int i = alp; i < 151; i++){
                for(int j = cop; j < 151; j++){
                    vis[i][j] = i-alp + j-cop;
                }
            }
            if(minAlp > alp && minCop > cop){
                q.offer(new int[] {minAlp, minCop, vis[minAlp][minCop]});
            }
            else if(minAlp > alp){
                q.offer(new int[] {minAlp, cop, vis[minAlp][cop]});
            }
            else if(minCop > cop){
                q.offer(new int[] {alp, minCop, vis[alp][minCop]});
            }
            else{
                q.offer(new int[] {alp, cop, 0});
            }
            int answer = vis[maxAlp][maxCop];
            while(!q.isEmpty()){
                int[] cur = q.poll();
                if(cur[0] >= maxAlp && cur[1] >= maxCop){
                    if(answer > cur[2])
                        answer = cur[2];
                    continue;
                }
                int remainAlp = Math.max(maxAlp - cur[0], 0);
                int remainCop = Math.max(maxCop - cur[1], 0);
                if(answer > vis[cur[0]][cur[1]] + remainAlp + remainCop){
                    answer = vis[cur[0]][cur[1]] + remainAlp + remainCop;
                    vis[maxAlp][maxCop] = answer;
                }
                for(int[] next : problems){
                    int alpCost = 0, copCost = 0,
                            nextAlp = cur[0] + next[2],
                            nextCop = cur[1] + next[3];
                    if(cur[0] < next[0]){
                        alpCost = next[0] - cur[0];
                        nextAlp += alpCost;
                    }
                    if(cur[1] < next[1]){
                        copCost = next[1] - cur[1];
                        nextCop += copCost;
                    }
                    nextAlp = Math.min(nextAlp, 150);
                    nextCop = Math.min(nextCop, 150);

                    int nextCost = cur[2] + next[4] + alpCost + copCost;
                    System.out.println("nextAlp: " + nextAlp + ", nextCop: " + nextCop + ",nextCost : " + nextCost);

                    if(vis[nextAlp][nextCop] > nextCost){
                        System.out.println("\n 통과");

                        q.offer(new int[] {nextAlp, nextCop, nextCost});
                        vis[nextAlp][nextCop] = nextCost;
                        System.out.println("nextAlp: " + nextAlp + ", nextCop: " + nextCop + ",nextCost : " + nextCost);
                    }
                }
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        int alp = 0;
        int cop = 0;
        int[][] pro = new int[][] {{0,0,2,1,2},{4,5,3,1,2},{4,11,4,0,2},{10,4,0,4,2}};
        System.out.println(Solution.solution(alp,cop,pro));

        String s = "helo";
        String n = "hi";
    }
}
