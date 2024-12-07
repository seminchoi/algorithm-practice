import java.util.*;
import java.io.*;

public class Main {
    public static class Element {
        public char c;
        public int count;

        public Element(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        //입력을 큐<Element> 에 넣는다.
        //현재 가지고있는 문자와 총 갯수를 Map에 넣는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int max = Integer.parseInt(br.readLine());
        String syntax = br.readLine();

        Queue<Element> queue = new LinkedList<>();
        Map<Character, Integer> map = new HashMap<>();

        int idx = 0;
        int answer = 0, curCount = 0;

        while(idx < syntax.length()) {
            char cur = syntax.charAt(idx);
            int count = 0;
            while(idx < syntax.length() && syntax.charAt(idx) == cur) {
                idx++;
                count++;
            }

            if(map.size() == max && !map.containsKey(cur)) {
                while(map.size() == max) {
                    Element element = queue.poll();
                    curCount -= element.count;
                    int remain = map.get(element.c) - element.count;
                    if(remain == 0) {
                        map.remove(element.c);
                    } else {
                        map.put(element.c, remain);
                    }
                }
            }

            queue.add(new Element(cur, count));
            map.put(cur, map.getOrDefault(cur, 0) + count);

            curCount += count;

            if(curCount > answer) {
                answer = curCount;
            }
        }
        System.out.println(answer);
    }
}