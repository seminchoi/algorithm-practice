package 회의실배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1931 {
    static int n, count;
    static Queue<Meeting> q = new PriorityQueue<>();

    static class Meeting implements Comparable<Meeting> {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting m) {
            if(end != m.end){
                return end - m.end;
            }
            else
                return start - m.start;

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            q.offer(new Meeting(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        int lastEnd = 0;
        while (!q.isEmpty()){
            Meeting m = q.poll();
            if(m.start < lastEnd){
                continue;
            }
            else{
                lastEnd = m.end;
                count++;
            }
        }

        System.out.println(count);
    }
}
