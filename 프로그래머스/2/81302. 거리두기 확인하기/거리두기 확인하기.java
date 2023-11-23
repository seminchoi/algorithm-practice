import java.util.LinkedList;
import java.util.Queue;

class Solution {
     private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, -1, 0, 1};
    public static class Node {
        int row;
        int column;
        int distance;

        public Node(int row, int column, int distance) {
            this.row = row;
            this.column = column;
            this.distance = distance;
        }

        public boolean isValidNode() {
            return (row >= 0 && row < 5) && (column >= 0 && column < 5) && distance <= 2;
        }
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            answer[i] = isPlaceDistancing(places[i]) ? 1 : 0;
        }

        return answer;
    }

    public boolean isPlaceDistancing(String[] place) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(place[i].charAt(j) == 'P') {
                    if(!isCandidateDistancing(place, i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isCandidateDistancing(String[] place, int row, int column) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(row, column, 0));
        boolean[][] visit = new boolean[5][5];
        visit[row][column] = true;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            for (int i = 0; i < 4; i++) {
                Node nextNode = new Node(
                        curNode.row + dy[i], curNode.column + dx[i], curNode.distance+1
                );
                if(nextNode.isValidNode() && !visit[nextNode.row][nextNode.column]) {
                    char nextNodeElement = place[nextNode.row].charAt(nextNode.column);
                    if(nextNodeElement == 'P') {
                        return false;
                    }
                    if(nextNodeElement == 'O') {
                        visit[nextNode.row][nextNode.column] = true;
                        queue.offer(nextNode);
                    }
                }
            }
        }

        return true;
    }
}