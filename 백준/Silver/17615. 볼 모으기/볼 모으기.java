import java.util.*;
import java.io.*;

public class Main {
    public static class Node {
        char color;
        int count;

        public Node(char color, int count) {
            this.color = color;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input1 = br.readLine();
        String input2 = br.readLine();
        int length = Integer.parseInt(input1);

        List<Node> nodes = new ArrayList<>();
        Node cur = null;
        for(int i = 0; i < length; i++) {
            char curColor = input2.charAt(i);
            if(cur == null || cur.color != curColor) {
                Node newNode = new Node(curColor, 1);
                cur = newNode;
                nodes.add(newNode);
            }
            else {
                cur.count++;
            }
        }

        int answer = Math.min(calculateMovingNumber(nodes, 'B'), calculateMovingNumber(nodes, 'R'));
        System.out.println(answer);
    }

    private static int calculateMovingNumber(List<Node> nodes, char color) {
        int movingCount = nodes.stream()
                .mapToInt(n -> {
                    if(n.color == color) {
                        return n.count;
                    }
                    return 0;
                }).sum();
        int pre = nodes.get(0).color == color ? nodes.get(0).count : 0;
        int post = nodes.get(nodes.size()-1).color == color ? nodes.get(nodes.size()-1).count : 0;
        int stopCount = Math.max(pre, post);
        return movingCount - stopCount;
    }
}