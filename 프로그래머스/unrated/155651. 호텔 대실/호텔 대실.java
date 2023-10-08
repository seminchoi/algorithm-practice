import java.util.*;

class Solution {
    public static class Reservation implements Comparable<Reservation> {
        String start;
        String end;
        //시작시간이 빠른순으로 정렬
        //끝시간이 느린순으로 정렬
        
        public Reservation(String start, String end) {
            this.start = start;
            this.end = end;
        }
        
        public int compareTo(Reservation r) {
            if(end.equals(r.end)) {
                return start.compareTo(r.start);
            } else {
                return end.compareTo(r.end);
            }
        }
    }
    
    public int solution(String[][] book_time) {
        int answer = 0;
        
        List<Reservation> list = new ArrayList<>();
        for(String[] time : book_time) {
            list.add(new Reservation(time[0], time[1]));
        }
        list.sort(new Comparator<Reservation>() {
            @Override
            public int compare(Reservation r1, Reservation r2) {
                if(r1.equals(r2.start)) {
                    return r1.end.compareTo(r2.end);
                } else {
                    return r1.start.compareTo(r2.start);
                }
            }
        });
        
        PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String r1, String r2) {
                return r2.compareTo(r1);
            }
        });
        
        for(Reservation r : list) {
            boolean isComplete = false;
            Iterator<String> iterator = queue.iterator();
            while (iterator.hasNext()) {
                String s = iterator.next();
                if (parseTime(r.start) >= parseTime(s) + 10) {
                    iterator.remove();
                    queue.add(r.end);
                    isComplete = true;
                    break;
                }
            }
            if(!isComplete) {
                queue.offer(r.end);
            }
        }
        return queue.size();
    }
    
    public int parseTime(String time) {
        String[] splitTime = time.split(":");
        return Integer.parseInt(splitTime[0]) * 60 + Integer.parseInt(splitTime[1]);
    }
}