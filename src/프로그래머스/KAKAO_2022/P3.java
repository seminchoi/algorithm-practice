package 프로그래머스.KAKAO_2022;

import java.util.Queue;
import java.util.List;
import java.util.LinkedList;
public class P3 {


    class Solution {
        public static class Graph{
            int pos;
            int weight;
            public Graph(int pos, int weight){
                this.pos = pos;
                this.weight = weight;
            }
        }
        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
            List<Graph>[] graph = new List[n+1];
            for(int i = 0; i < n+1; i++){
                graph[i] = new LinkedList<>();
            }
            for (int[] path : paths) {
                graph[path[0]].add(new Graph(path[1], path[2]));
                graph[path[1]].add(new Graph(path[0], path[2]));
            }

            boolean[] isSummit = new boolean[n+1];
            for (int summit : summits) {
                isSummit[summit] = true;
            }

            int[] intensity = new int[n+1];
            for (int gate : gates) {
                intensity[gate] = -1;
            }

            Queue<Integer> q = new LinkedList<>();

            for (int startGate : gates) {
                q.offer(startGate);
            }
            while(!q.isEmpty()){
                int s = q.poll();
                if(isSummit[s]){
                    continue;
                }
                for(Graph next : graph[s]){
                    int nextPos = next.pos;
                    int nextIntensity = Math.max(next.weight, intensity[s]);
                    if(intensity[nextPos] == 0 || intensity[nextPos] > nextIntensity){
                        intensity[nextPos] = nextIntensity;
                        q.offer(nextPos);
                    }
                }
            }


            int min = -1;
            int[] answer = {};

            for(int i = 0; i < summits.length; i++){
                if(min == -1 || min > intensity[summits[i]]){
                    min = intensity[summits[i]];
                    answer = new int [] {summits[i],min};
                }
                else if (min == intensity[summits[i]] && answer[0] > summits[i]){
                    answer = new int [] {summits[i],min};
                }
            }

            return answer;
        }
    }
}
