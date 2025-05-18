import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    // N번째 학생에 대한 간선 기록
    static class Node {
        int src;
        List<Integer> dests = new ArrayList<>();
    }

    static Node[] graph;
    static List<Integer> marked = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int[] input1 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input1[0];
        int m = input1[1];

        graph = new Node[n+1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new Node();
        }

        for(int i = 0; i < m; i++) {
            int[] input2 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int m1 = input2[0];
            int m2 = input2[1];

            graph[m2].src++;
            graph[m1].dests.add(m2);
        }

        Deque<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            if(graph[i].src == 0) {
                queue.offer(i);
            }
        }

        List<Integer> answer = new ArrayList<>();

        while(!queue.isEmpty()) {
            var curN = queue.poll();
            var cur = graph[curN];
            answer.add(curN);

            for(var nextN: cur.dests) {
                var next = graph[nextN];
                next.src--;
                if(next.src == 0) {
                    queue.addFirst(nextN);
                }
            }
        }

        System.out.println(answer.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}