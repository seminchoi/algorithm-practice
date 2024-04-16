import java.util.*;

class Solution {
    public static Set<Integer>[] vis;
    public static int[][] tree;
    
    public int solution(int[] info, int[][] edges) {
        init(info);
        createTree(info.length, edges);
        Set<Integer> nodes = new HashSet<>();
        nodes.add(0);
        return backTraking(nodes, 0, 0, 0, info);
    }
    
    private void init(int[] info) {
        vis = new Set[info.length];
        
        for(int i = 0; i < vis.length; i++) {
            vis[i] = new HashSet<>();
        }
    }
    
    private void createTree(int infoSize, int[][] edges) {
        tree = new int[infoSize][2];
        
        for(int[] node : tree) {
            Arrays.fill(node, -1);
        }
        
        for(int[] edge : edges) {
            if(tree[edge[0]][0] == -1) {
                tree[edge[0]][0] = edge[1];
            }
            else {
                tree[edge[0]][1] = edge[1];
            }
        }
    }
    
    private int backTraking(Set<Integer> nodes, int sheepCount, int wolvesCount, int visHistory, int[] info) {
        int maxSheepCount = sheepCount;
        for(Integer curNode : nodes) {
            int curSheepCount = sheepCount, curWolvesCount = wolvesCount;
             if(info[curNode] == 0) {
                curSheepCount++;
            }

            if(info[curNode] == 1) {
                curWolvesCount++;
            }
            
            if(curSheepCount <= curWolvesCount) {
                continue;
            }
            
            int curVisHistory = createVisHistory(visHistory, curNode);
            
            if(!vis[curNode].add(visHistory)) {
                continue;
            }
            
            Set<Integer> nextNodes = new HashSet<>(nodes);
            nextNodes.remove(curNode);
            
            
            int[] children = tree[curNode];
            for(int i = 0; i < 2; i++) {
                if(children[i] == -1) {
                    continue;
                }
                
                nextNodes.add(children[i]);
            }
            
            maxSheepCount = Math.max(maxSheepCount, backTraking(nextNodes, curSheepCount, curWolvesCount, curVisHistory, info));
        }
        
        return maxSheepCount;
    }
    
    private int createVisHistory(int visHistory, int curNode) {
        int vis = 1 << curNode;
        return visHistory | vis;
    }
}