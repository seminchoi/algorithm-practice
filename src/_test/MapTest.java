package _test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('C',3);
        map.put('C',4);
        System.out.println(map.get('d'));
        System.out.println(map.containsKey("d"));
    }
}
