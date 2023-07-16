package _test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Compare {

    public static class Pos implements Comparable<Pos>{
        public int x;
        public int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pos p) {
            //음수인지 양수인지 비교하면 되기 때문에 - 연산을 통해 코드를 간결하게 만듭니다.
            if (x != p.x)
                return x - p.x;
            else
                return p.y - y;
        }
    }

    public static void main(String[] args) {
        Pos pos1 = new Pos(1,1);
        Pos pos2 = new Pos(2,1);
        Pos pos3 = new Pos(3,2);
        Pos pos4 = new Pos(1,4);
        Pos pos5 = new Pos(1,5);

        List<Pos> pos = new ArrayList<>();
        pos.add(pos1);
        pos.add(pos2);
        pos.add(pos3);
        pos.add(pos4);
        pos.add(pos5);

        Collections.sort(pos);

        for(Pos post : pos){
            System.out.printf("x: %d, y: %d \n",post.x,post.y);
        }
    }
}
