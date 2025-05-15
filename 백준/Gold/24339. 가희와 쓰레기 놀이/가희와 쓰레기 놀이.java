import java.util.*;
import java.io.*;

public class Main {
    static Map<Integer, Node> nodes = new HashMap<>();
    static Set<Integer> delEdges = new HashSet<>();
    static List<Node> roots = new ArrayList<>();

    public static class Node {
        int number;
        List<Edge> edges = null;
        boolean vis;

        public Node(int number) {
            this.number = number;
        }
    }

    public static class Edge {
        int id;
        Node node;
        boolean strong;

        public Edge(int id, Node node, boolean strong) {
            this.id = id;
            this.node = node;
            this.strong = strong;
        }
    }

    public static void main(String[] args) throws Exception {
        // 특정 노드에 즉시 edge를 추가하기 위해 모든 노드를 알고 있는 map이 필요
        // 루트에서 bfs를 즉시 수행하기 위해 루트들 정보를 알고있는 list필요
        // 순회 한 뒤에는 순회 한 노드와 모든 노드를 비교해서 차집합을 제거

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input1 = br.readLine().split(" ");
        int o = Integer.parseInt(input1[0]);
        int e = Integer.parseInt(input1[1]);

        for (int i = 0; i < o; i++) {
            String[] command = br.readLine().split(" ");
            made(command);
        }

        // M
        // m
        // MADE
        // ADD
        // REMOVE
        for (int i = 0; i < e; i++) {
            String[] command = br.readLine().split(" ");
            if (command[0].equals("MADE")) made(command);
            else if (command[0].equals("M")) M();
            else if (command[0].equals("m")) m();
            else if (command[0].equals("ADD")) add(command);
            else if (command[0].equals("REMOVE")) remove(command);
        }
    }

    private static void made(String[] command) {
        int addIdx = 0;
        if (command[0].equals("MADE")) {
            addIdx = 1;
        }

        int num = Integer.parseInt(command[addIdx]);

        var node = new Node(num);
        nodes.put(num, node);

        if (command[1 + addIdx].equals("ROOT")) {
            roots.add(node);
        }
    }

    private static void m() {
        search(false);
    }

    private static void M() {
        search(true);
    }

    private static void search(boolean strong) {
        // 탐색되지 않은 노드들은 모두 isDeleted 처리 해줘야 한다.
        // 이유는 엣지에 남아있을 수 있기 때문

        for (var root : roots) {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            root.vis = true;

            while (!queue.isEmpty()) {
                var cur = queue.poll();
                var curE = cur.edges;
                if(curE == null) continue;

                curE.removeIf(e -> {
                    var del = delEdges.contains(e.id) || !nodes.containsKey(e.node.number);
                    if(del) {
                        delEdges.remove(e.id);
                    }
                    return del;
                });

                for (var edge : curE) {
                    var next = edge.node;

                    if (strong && !edge.strong) continue;
                    if (next.vis) continue;
                    queue.offer(next);
                    next.vis = true;
                }
            }
        }

        nodes.entrySet().removeIf(e -> !e.getValue().vis);
        for (var node : nodes.values()) {
            node.vis = false;
        }

        System.out.println(nodes.size());
    }

    private static void add(String[] command) {
        int id = Integer.parseInt(command[1]);
        int src = Integer.parseInt(command[2]);
        int dest = Integer.parseInt(command[4]);
        boolean strong = command[3].equals("=>");

        var edge = new Edge(id, nodes.get(dest), strong);
        var e = nodes.get(src).edges;
        if(e == null) {
            e = new ArrayList<>();
        }
        e.add(edge);
        nodes.get(src).edges = e;
    }

    private static void remove(String[] command) {
        int id = Integer.parseInt(command[1]);
        delEdges.add(id);
    }
}