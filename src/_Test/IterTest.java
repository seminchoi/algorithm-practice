package _Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IterTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        Iterator i = list.iterator();
        i.next();
    }
}
