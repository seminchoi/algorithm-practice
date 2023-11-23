import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

class Solution {
    private static final int[] POWER = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};

    public static class Node {
        int index;
        int cost;
        boolean reverse;
        int activateTraps = 0;

        public Node(int index, int cost, boolean reverse) {
            this.index = index;
            this.cost = cost;
            this.reverse = reverse;
        }

        public Node(int index, int cost, boolean reverse, int activateTraps) {
            this.index = index;
            this.cost = cost;
            this.reverse = reverse;
            this.activateTraps = activateTraps;
        }

        public void activateTrapIndexBit(int index) {
            activateTraps = activateTraps ^ POWER[index];
        }

        public boolean isActive(int trapIndex) {
            return POWER[trapIndex] == (activateTraps & POWER[trapIndex]);
        }
    }

    public static class DistanceKey {
        int index;
        int activateTraps;

        public DistanceKey(int index, int activateTraps) {
            this.index = index;
            this.activateTraps = activateTraps;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            DistanceKey that = (DistanceKey) object;
            return index == that.index && activateTraps == that.activateTraps;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, activateTraps);
        }
    }

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        Map<Integer, Integer> trapMap = new HashMap<>();
        for (int i = 0; i < traps.length; i++) {
            trapMap.put(traps[i], i);
        }
        List<Node>[] graph = createGraph(n, roads, trapMap);
        int dijkstra = dijkstra(graph, start, end, trapMap);
        return dijkstra;
    }

    private List<Node>[] createGraph(int n, int[][] roads, Map<Integer, Integer> trapMap) {
        List<Node>[] graph = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            graph[road[0]].add(new Node(road[1], road[2], true));
            if (trapMap.containsKey(road[0]) || trapMap.containsKey(road[1])) {
                graph[road[1]].add(new Node(road[0], road[2], false));
            }
        }

        return graph;
    }

    private int dijkstra(List<Node>[] graph, int start, int end, Map<Integer, Integer> trapMap) {
        Map<DistanceKey, Integer> distances = new HashMap<>();

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        Node startNode = new Node(start, 0, true);
        distances.put(new DistanceKey(start, 0), 0);
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            DistanceKey curDistanceKey = new DistanceKey(curNode.index, curNode.activateTraps);
            if (curNode.cost > distances.get(curDistanceKey)) {
                continue;
            }
            for (Node node : graph[curNode.index]) {
                Node nextNode = new Node(node.index, distances.get(curDistanceKey) + node.cost, node.reverse, curDistanceKey.activateTraps);

                boolean isReverse = (trapMap.containsKey(curNode.index) && curNode.isActive(trapMap.get(curNode.index)))
                        ^ (trapMap.containsKey(nextNode.index) && curNode.isActive(trapMap.get(nextNode.index)));

                if(trapMap.containsKey(node.index)) {
                    nextNode.activateTrapIndexBit(trapMap.get(node.index));
                }
                DistanceKey nextDistanceKey = new DistanceKey(nextNode.index, nextNode.activateTraps);
                if((nextNode.reverse ^ isReverse) && nextNode.cost < distances.getOrDefault(nextDistanceKey, Integer.MAX_VALUE)) {
                    distances.put(nextDistanceKey, nextNode.cost);
                    queue.offer(nextNode);
                }
            }

        }

        int minDistance = Integer.MAX_VALUE;
        for (DistanceKey distanceKey : distances.keySet()) {
            if(distanceKey.index == end) {
                minDistance = Math.min(minDistance, distances.get(distanceKey));
            }
        }
        return minDistance;
    }
}