import java.util.*;

class Solution {
    int t1;
    int t2;
    int a;
    int b;
    int[] onboard;
    int temp;
    int length;
    Map<Integer, Integer>[] map;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        temp = temperature;
        this.t1 = t1;
        this.t2 = t2;
        this.a = a;
        this.b = b;
        this.onboard = onboard;
        this.length = onboard.length;
        // 1. a만큼소비
        // 2. b만큼 소비
        // 3. 소비 x

        // 현재 소비 전력이 가장 낮으면 된다.
        // 손님이 있을 때 적정온도여야 한다.
        // 백 트래킹으로 전체 탐색을한다.
        int length = onboard.length;
        map = new Map[length];
        for (int i = 0; i < length; i++) {
            map[i] = new HashMap<>();
        }

        return bfs();
    }


    // 전체탐색을 하되, 다음 기준에서는 포기한다.
    // 같은 온도인데 비용이 더 비싼것이 발생되면 해당 경로는 포기한다.
    // 손님이 탔는데 온도가 안맞으면 해당 경로도 포기한다.
    // 배열 구성 - [시간] [현재 온도] = 비용
    private int bfs() {
        //시간, 현재온도, 비용
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, temp, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if(cur[1] < -10 ||  cur[1] > 40) { continue;}
            checkDown(queue, cur);
            checkUp(queue, cur);
            checkOff(queue, cur);
            checkKeep(queue, cur);
        }

        int min = Integer.MAX_VALUE;
        for (Integer value : map[onboard.length - 1].values()) {
            min = Math.min(min, value);
        }
        return min;
    }

    private void checkDown(Queue<int[]> queue, int[] cur) {
        if(cur[0] == length - 1) { return; }

        if (cur[1] > temp) return;

        //시간, 현재온도, 비용
        int nextTemp = cur[1] - 1;
        // 손님이 있으면 범위 안에 있어야함
        // 다음 시간에 온도가 같은데 비용이 더 낮으면 굳이 탐색할 필요 없음
        int nextCost = cur[2] + a;
        Integer existCost = map[cur[0] + 1].get(nextTemp);
        if ((nextTemp < t1 || nextTemp > t2) && onboard[cur[0] + 1] == 1) {
            return;
        }
        if (existCost != null && nextCost >= existCost) {
            return;
        }
        map[cur[0] + 1].put(nextTemp, nextCost);
        queue.add(new int[]{cur[0] + 1, nextTemp, nextCost});
    }

    private void checkUp(Queue<int[]> queue, int[] cur) {
        if(cur[0] == length - 1) { return; }
        if (cur[1] < temp) return;

        int nextTemp = cur[1] + 1;
        int nextCost = cur[2] + a;
        Integer existCost = map[cur[0] + 1].get(nextTemp);
        if ((nextTemp < t1 || nextTemp > t2) && onboard[cur[0] + 1] == 1) {
            return;
        }
        if (existCost != null && nextCost >= existCost) {
            return;
        }
        map[cur[0] + 1].put(nextTemp, nextCost);
        queue.add(new int[]{cur[0] + 1, nextTemp, nextCost});
    }

    private void checkOff(Queue<int[]> queue, int[] cur) {
        if(cur[0] == length - 1) { return; }

        int dt = Integer.compare(temp, cur[1]);

        int nextTemp = cur[1] + dt;
        int nextCost = cur[2];
        Integer existCost = map[cur[0] + 1].get(nextTemp);

        if ((nextTemp < t1 || nextTemp > t2) && onboard[cur[0] + 1] == 1) {
            return;
        }

        if (existCost != null && nextCost >= existCost) {
            return;
        }

        map[cur[0] + 1].put(nextTemp, nextCost);
        queue.add(new int[]{cur[0] + 1, nextTemp, nextCost});
    }

    private void checkKeep(Queue<int[]> queue, int[] cur) {
        if(cur[0] == length - 1) { return; }

        if (cur[1] == temp) return;

        int nextTemp = cur[1];
        int nextCost = cur[2] + b;
        Integer existCost = map[cur[0] + 1].get(nextTemp);

        if ((nextTemp < t1 || nextTemp > t2) && onboard[cur[0] + 1] == 1) {
            return;
        }

        if (existCost != null && nextCost >= existCost) {
            return;
        }

        map[cur[0] + 1].put(nextTemp, nextCost);
        queue.add(new int[]{cur[0] + 1, nextTemp, nextCost});
    }
}