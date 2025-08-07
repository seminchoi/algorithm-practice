import java.util.*;
import java.util.Arrays;

class Solution {
    public static class Node {
        int value;
        int x;
        int y;
        int minX;
        int maxX;
        Node[] children;
        
        public Node(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
            this.children = new Node[2];
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        // y값이 큰게 가장 상위 노드
        // 그래프를 만들자
        // y 값이 크고, (y값이 같은 경우)x값이 작은게 앞으로 오게 정렬하자
        // 중위탐색 결과를 바탕으로 그래프를 만들자
        // 큐에 넣고 왼쪽, 오른쪽을 채워나간다. 
        // 왼쪽이 나왓는데 또 왼쪽이 나오거나 오른쪽이 있는데 또 오른쪽이 나오면 pop
        
        //라벨링
        Node root = makeTree(nodeinfo);
        
        List<Integer> preOrderList = new ArrayList<>();
        processPreOrder(preOrderList, root);
        int[] preOrder = preOrderList.stream().mapToInt(n -> n).toArray();
        
        List<Integer> postOrderList = new ArrayList<>();
        processPostOrder(postOrderList, root);
        int[] postOrder = postOrderList.stream().mapToInt(n -> n).toArray();
        
        
        
        int[][] answer = { preOrder, postOrder};
        return answer;
    }
    
    private Node makeTree(int[][] nodeinfo) {
        int[][] numberNodeInfo = new int[nodeinfo.length][3];
        
        for(int i = 0; i < nodeinfo.length; i++) {
            numberNodeInfo[i][0] = nodeinfo[i][0];
            numberNodeInfo[i][1] = nodeinfo[i][1];
            numberNodeInfo[i][2] = i + 1;
        }
        
        //y가 같으면 x가 작은순
        //y가 다르면 y가 큰순
        Arrays.sort(numberNodeInfo, (n1, n2) -> {
            if(n1[1] == n2[1]) {
                return n1[0] - n2[0];
            } else {
                return n2[1] - n1[1];
            }
        });
        
        Queue<Node> queue = new LinkedList<>();
        
        Node root = new Node(numberNodeInfo[0][2],numberNodeInfo[0][0], numberNodeInfo[0][1]);
        root.minX = -1;
        root.maxX = Integer.MAX_VALUE;

        queue.offer(root);
        
        int idx = 1;

        while(!queue.isEmpty() && numberNodeInfo.length > idx) {
            Node cur = queue.peek();
        
            Node next = new Node(numberNodeInfo[idx][2], numberNodeInfo[idx][0], numberNodeInfo[idx][1]);
            if(next.x < cur.x) {
                
                if(cur.children[0] == null && next.x > cur.minX) {
                    cur.children[0] = next;
                    next.minX = cur.minX;
                    next.maxX = cur.x;
                    queue.offer(next);
                    idx++;
                }
                else {
                    queue.poll();
                }
            } else {
                if(cur.children[1] == null && next.x < cur.maxX) {
                    cur.children[1] = next;
                    next.minX = cur.x;
                    next.maxX = cur.maxX;
                    queue.offer(next);
                    idx++;
                } else {
                    queue.poll();
                }
            }
        }
        
        return root;
    }
    
    private void processPreOrder(List<Integer> result, Node cur) {
        result.add(cur.value);
        if(cur.children[0] != null) {
            processPreOrder(result, cur.children[0]);
        }
        if(cur.children[1] != null) {
            processPreOrder(result, cur.children[1]);
        }
    }
    
    private void processPostOrder(List<Integer> result, Node cur) {
        if(cur.children[0] != null) {
            processPostOrder(result, cur.children[0]);
        }
        if(cur.children[1] != null) {
            processPostOrder(result, cur.children[1]);
        }
        result.add(cur.value);
    }
}