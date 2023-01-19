import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Member implements Comparable<Member>{
        static int NOW_SEQ = 1;
        int seq;
        int age;
        String name;

        public Member(int age, String name) {
            this.seq = NOW_SEQ++;
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Member m) {
            if(age != m.age){
                return age-m.age;
            }
            else{
                return seq - m.seq;
            }
        }

        @Override
        public String toString(){
            return age + " " +name;
        }
    }
    public static void main(String[] args) throws IOException {
        List<Member> members = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Member member = new Member(Integer.parseInt(st.nextToken()), st.nextToken());
            members.add(member);
        }

        Collections.sort(members);

        for (Member member : members) {
            System.out.println(member);
        }
    }
}
