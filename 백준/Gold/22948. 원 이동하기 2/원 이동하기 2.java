import java.util.*;
import java.io.*;

public class Main {
    public static Map<Integer, Circle> circleMap = new HashMap<>();

    public static class Circle {
        int id;
        int point;
        int radius;

        public Circle(int id, int point, int radius) {
            this.id = id;
            this.point = point;
            this.radius = radius;
        }

        public boolean isParent(Circle circle) {
            return Math.abs(radius - circle.radius) > Math.abs(point - circle.point) &&
                    radius < circle.radius;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input1 = br.readLine();
        int n = Integer.parseInt(input1);

        for (int i = 0; i < n; i++) {
            String[] input2 = br.readLine().split(" ");
            int id = Integer.parseInt(input2[0]);
            int x = Integer.parseInt(input2[1]);
            int r = Integer.parseInt(input2[2]);
            circleMap.put(id, new Circle(id, x, r));
        }

        circleMap.put(0, new Circle(0, 0, Integer.MAX_VALUE));

        String[] input3 = br.readLine().split(" ");
        Circle a = circleMap.get(Integer.parseInt(input3[0]));
        Circle b = circleMap.get(Integer.parseInt(input3[1]));

        List<Circle> aParents = findParents(a);
        List<Circle> bParents = findParents(b);

        aParents.sort(Comparator.comparingInt(c -> c.radius));
        bParents.sort((c1, c2) -> c2.radius - c1.radius);

        int aIdx = aParents.size() - 1;
        int loop = Math.min(aParents.size(), bParents.size());
        List<Circle> removeCircles = new ArrayList<>();
        for (int i = 0; i < loop; i++) {
            if (aParents.get(aIdx - i) == bParents.get(i)) {
                removeCircles.add(aParents.get(aIdx - i));
            }
        }

        Circle lastCircle = removeCircles.get(removeCircles.size() - 1);

        aParents.removeAll(removeCircles);
        bParents.removeAll(removeCircles);
        aParents.add(lastCircle);

        aParents.addAll(bParents);

        StringJoiner s = new StringJoiner(" ");
        s.add(String.valueOf(a.id));
        aParents.forEach(c -> s.add(String.valueOf(c.id)));
        s.add(String.valueOf(b.id));

        System.out.println(aParents.size() + 2);
        System.out.println(s);
    }

    private static List<Circle> findParents(Circle target) {
        List<Circle> circles = new ArrayList<>();
        for (Circle circle : circleMap.values()) {
            if (target.isParent(circle)) {
                circles.add(circle);
            }
        }

        return circles;
    }
}