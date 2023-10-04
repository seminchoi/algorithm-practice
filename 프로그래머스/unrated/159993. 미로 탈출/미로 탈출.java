import java.util.*;

class Solution {
    public static int[] dx = {0,-1,0,1}, dy = {-1,0,1,0};
    
    public static class Node {
        int x;
        int y;
        int depth;
        
        public Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
    
    public int solution(String[] maps) {
        int answer = 0;
        
        Node startNode = null;
        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[i].length(); j++) {
                if(maps[i].charAt(j) == 'S') {
                    startNode = new Node(j, i, 0);
                }
            }
        }
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(startNode);
        
        Node leverNode = bfs(maps, queue, 'L');
        if(leverNode == null) {
            return -1;
        }
        
        queue = new LinkedList<>();
        queue.offer(leverNode);
        Node endNode = bfs(maps, queue, 'E');
        
        if(endNode == null) {
            return -1;
        }
        
        
        return endNode.depth;
    }
    
    public Node bfs(String[] maps, Queue<Node> queue, char target) {
        boolean[][] vis = new boolean[maps.length][maps[0].length()];
        
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            
            if(getChar(maps, cur) == target) {
                return cur;
            }
            
            for(int i = 0; i < 4; i++) {
                Node next = new Node(cur.x + dx[i], cur.y + dy[i], cur.depth + 1);
                if(!isOutOfBound(maps, next) && !vis[next.y][next.x] && maps[next.y].charAt(next.x) != 'X') {
                    queue.offer(next);
                    vis[next.y][next.x] = true;
                }
            }
        }
        
        return null;
    }

    public boolean isOutOfBound(String[] maps, Node node) {
        int maxRow = maps[0].length();
        int maxColumn = maps.length;
        return node.x < 0 || node.x >= maxRow || node.y < 0 || node.y >= maxColumn;
    }

    public char getChar(String[] maps, Node node) {
        return maps[node.y].charAt(node.x);
    }
}