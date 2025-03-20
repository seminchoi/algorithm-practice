import java.util.*;

class Solution {
    public static class Tree {
        Map<Integer, Integer> nodes = new HashMap<>();
        int oe = 0;
        int roe = 0;
        
        public void calculateAnswer(int[] answer) {
            if(nodes.size() == 1) {
                if(oe == 1) {
                    answer[0]++;
                } else {
                    answer[1]++;
                }
            }
            for(Integer key : nodes.keySet()) {
                int childCount = nodes.get(key);
                if(isOe(key, childCount) && (roe - 1 == 0)) {
                    answer[0]++;
                }
                if(!isOe(key,childCount) && (oe - 1 == 0)) {
                    answer[1]++;
                }
            }
        }
        
        public void put(int key, int size) {
            if(size == 0) {
                nodes.put(key, size);
                autoSetOe(key, size);
            } else {
                nodes.put(key, size);
                autoSetOe(key, size - 1);
            }
        }
        
        public void autoSetOe(int key, int size) {
            if(isOe(key, size)) {
                oe++;
            } else {
                roe++;
            }
        }
        
        public boolean isOe(int key, int size) {
            return key % 2 == size % 2;
        }
    }
    public int[] solution(int[] nodes, int[][] edges) {
        //1. 트리를 만든다.
        //2. 각 번호에 몇개가 연결된지 판단하면서 트리에 저장한다.
        //3. 트리에 저장할때, 노드 개수 - 1을 해서 홀, 짝, 역홀, 역짝 개수가 몇개인지 구한다.
        //4. 각 트리에서 루트노드를 하나씩 고르면서 +1하고, 홀짝인지 역홀짝인지 판단한다.
        

        int[] answer = new int[2];
        List<Tree> trees = createGraph(nodes, edges);
        for(Tree tree : trees) {
            tree.calculateAnswer(answer);
        }
        return answer;
    }
    
    private List<Tree> createGraph(int[] nodes, int[][] edges) {
        Set<Integer> used = new HashSet<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        for(int i = 0; i < edges.length; i++) {
            int s = edges[i][0];
            int d = edges[i][1];
            used.add(s);
            used.add(d);
            
            List<Integer> child1 = graph.getOrDefault(s, new ArrayList<>());
            child1.add(d);
            graph.put(s, child1);
            
            List<Integer> child2 = graph.getOrDefault(d, new ArrayList<>());
            child2.add(s);
            graph.put(d, child2);
        }
        
        for(int i = 0; i < nodes.length; i++) {
            if(!used.contains(nodes[i])) {
                List<Integer> children = new ArrayList<>();
                graph.put(nodes[i], children);
            }
        }
        
        return createTrees(graph);
    }
    
    private List<Tree> createTrees(Map<Integer, List<Integer>> graph) {
        List<Tree> trees = new ArrayList<>();
        Set<Integer> visited = new HashSet<Integer>();
        
        for(Integer key: graph.keySet()) {
            if(visited.contains(key)) continue;
            Tree tree = new Tree();
            dfs(key, graph, tree, visited);
            trees.add(tree);
        }
        
        return trees;
    }
    
    private void dfs(int key, Map<Integer, List<Integer>> graph, Tree tree, Set<Integer> visited) {
        visited.add(key);
        List<Integer> child = graph.get(key);
        tree.put(key, child.size());
        
        
        for(int i = 0; i < child.size(); i++) {
            int nKey = child.get(i);
            if(visited.contains(nKey)) {
                continue;
            }
            dfs(nKey, graph, tree, visited);
        }
    }
}