import java.util.*;

class Solution {
    public static class Divisor implements Comparable<Divisor> {
        int seq;
        int count;
        
        public Divisor(int seq, int count) {
            this.seq = seq;
            this.count = count;
        }
        
        public int compareTo(Divisor d) {
            if(count == d.count) {
                return seq - d.seq;
            }
            return d.count - count;
        }
    }
    public int[] solution(int e, int[] starts) {
        
        int[] mult = new int[e + 1];
        
        for(int i = 2; i <= e; i++) {
            for(int j = 2; j*i <= e; j++) {
                mult[i*j]++;
            }
        }
        
        List<Divisor> divisorList = new ArrayList<>();
        
        for(int i = 1; i <= e; i++) {
            divisorList.add(new Divisor(i, mult[i] + 2));
        }

        
        Collections.sort(divisorList);
        
        int[] answer = new int[starts.length];
        
        int answerIndex = 0;
        for(int start : starts) {
            for(Divisor d : divisorList) {
                if(d.seq >= start) {
                    answer[answerIndex++] = d.seq; 
                    break;
                }
            }
        }
        
        return answer;
    }
}