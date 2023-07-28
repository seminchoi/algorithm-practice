import java.util.*;

public class Solution {

    public String[] answer;
    public int index;

    public PriorityQueue<Plan> queue = new PriorityQueue<>();
    public Stack<Plan> stack = new Stack<>();

    public static class Plan implements Comparable<Plan> {
        String name;
        Time startTime;
        int playtime;

        public Plan(String[] plan) {
            this.name = plan[0];
            this.startTime = new Time(plan[1]);
            this.playtime = Integer.parseInt(plan[2]);
        }

        public int compareTo(Plan p) {
            return startTime.compareTo(p.startTime);
        }


    }

    public static class Time implements Comparable<Time> {
        int hour;
        int minute;

        public Time(int hour, int minute) {
            this.hour = hour;
            this.minute = minute;
        }

        public Time(String startTime) {
            int[] parsedTime = parseTime(startTime);
            this.hour = parsedTime[0];
            this.minute = parsedTime[1];
        }

        public static int[] parseTime(String time) {
            StringTokenizer st = new StringTokenizer(time, ":");
            return new int[]{Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())};
        }

        public boolean equals(Time time) {
            return this.compareTo(time) == 0;
        }

        public int compareTo(Time t) {
            return (hour * 60 + minute) - (t.hour * 60 + t.minute);
        }

        public void plusMinute(int playtime) {
            this.minute = this.minute + playtime;
            this.hour += this.minute / 60;
            this.minute = this.minute % 60;
        }
    }

    public String[] solution(String[][] plans) {

        for (String[] plan : plans) {
            queue.offer(new Plan(plan));
        }

        answer = new String[plans.length];
        doStudy();

        return answer;
    }


    public void doStudy() {
        Time curTime = new Time("00:00");

        while (!queue.isEmpty()) {
            //큐가 비었을 때
            //스택이 비었을 때
            //다음에 뭘 할지 결정함 -> 시간을 시작시간으로 옮김 -> 큐에서 빼서 스택에 넣음
            if (stack.isEmpty() || curTime.equals(queue.peek().startTime)) {
                startNewPlan();
                curTime = getStartTime();
        System.out.println(curTime.hour);
            
            }else {
                //계획 시작시간이 아직 남았으면 시작시간까지 남은 걸 하고 남은시간을 줄인 후 다음 작업을 스택에 넣음
                curTime = redoRemainPlanBeforeNextPlanStartTime(curTime);
            }
        }

        while (!stack.isEmpty()) {
            answer[index++] = stack.pop().name;
        }
    }


    public void startNewPlan() {
        Plan newPlan = queue.poll();
        stack.push(newPlan);
    }

    public Time getStartTime() {
        return stack.peek().startTime;
    }

    public Time redoRemainPlanBeforeNextPlanStartTime(Time curTime) {
        Time nextPlanStartTime = queue.peek().startTime;

        Plan remainPlan = stack.peek();
        int leftMinute = nextPlanStartTime.compareTo(curTime);
        if (remainPlan.playtime > leftMinute) {
            remainPlan.playtime -= leftMinute;
            curTime.hour = nextPlanStartTime.hour;
            curTime.minute = nextPlanStartTime.minute;
        } else {
            answer[index++] = remainPlan.name;
            stack.pop();
            curTime.plusMinute(remainPlan.playtime);
        }
        return curTime;
    }
}