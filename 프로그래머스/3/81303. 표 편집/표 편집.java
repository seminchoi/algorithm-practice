import java.util.Stack;

class Solution {
    public static class Editor {
        Node[] nodes;
        int cursor;
        Stack<Node> clipBoards = new Stack<>();

        public Editor(int n, int cursor) {
            this.nodes = createNodes(n);
            this.cursor = cursor;
        }

        private static Node[] createNodes(int n) {
            Node[] nodes = new Node[n];
            Node startNode = new Node(0, -1, 1);
            nodes[0] = startNode;
            Node endNode = new Node(n - 1, n - 2, -1);
            nodes[n - 1] = endNode;
            for (int i = 1; i < n - 1; i++) {
                nodes[i] = new Node(i, i - 1, i + 1);
            }
            return nodes;
        }

        public void up(int count) {
            for (int i = 0; i < count; i++) {
                Node curNode = nodes[cursor];
                cursor = curNode.pre;
            }
        }

        public void down(int count) {
            for (int i = 0; i < count; i++) {
                Node curNode = nodes[cursor];
                cursor = curNode.next;
            }
        }

        public void cut() {
            Node curNode = nodes[cursor];
            clipBoards.push(curNode);
            moveCursorBeforeCut(curNode);
            cutNode(curNode);
        }

        private void moveCursorBeforeCut(Node curNode) {
            if (curNode.next == -1) {
                cursor = curNode.pre;
            } else {
                cursor = curNode.next;
            }
        }

        private void cutNode(Node curNode) {
            curNode.active = false;
            if (curNode.pre != -1) {
                nodes[curNode.pre].next = curNode.next;
            }
            if (curNode.next != -1) {
                nodes[curNode.next].pre = curNode.pre;
            }
        }

        public void rollBack() {
            if (clipBoards.isEmpty()) {
                return;
            }
            Node curNode = clipBoards.pop();
            curNode.active = true;
            if (curNode.pre != -1) {
                nodes[curNode.pre].next = curNode.index;
            }
            if (curNode.next != -1) {
                nodes[curNode.next].pre = curNode.index;
            }
        }

        public String getActiveStatus() {
            StringBuilder stringBuilder = new StringBuilder();
            for (Node node : nodes) {
                char isActive = node.active ? 'O' : 'X';
                stringBuilder.append(isActive);
            }
            return stringBuilder.toString();
        }
    }

    public static class Node {
        int index;
        int pre;
        int next;
        boolean active;

        public Node(int index, int pre, int next) {
            this.index = index;
            this.pre = pre;
            this.next = next;
            active = true;
        }
    }

    public String solution(int n, int k, String[] cmd) {
        return runEditor(n, k, cmd);
    }

    private String runEditor(int n, int k, String[] commands) {
        Editor editor = new Editor(n, k);
        for (String command : commands) {
            executeCommand(command, editor);
        }
        return editor.getActiveStatus();
    }

    private void executeCommand(String command, Editor editor) {
        String[] parsedCommand = command.split(" ");
        if (parsedCommand[0].equals("U")) {
            editor.up(Integer.parseInt(parsedCommand[1]));
        }
        if (parsedCommand[0].equals("D")) {
            editor.down(Integer.parseInt(parsedCommand[1]));
        }
        if (parsedCommand[0].equals("C")) {
            editor.cut();
        }
        if (parsedCommand[0].equals("Z")) {
            editor.rollBack();
        }
    }
}