import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Jewel implements Comparable<Jewel> {
        int cost;
        List<Integer> weights;

        public Jewel(int cost, List<Integer> weights) {
            this.cost = cost;
            this.weights = weights;
            Collections.sort(weights);
        }

        @Override
        public int compareTo(Jewel j) {
            return j.cost - this.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        //[보석 개수] [가방 개수]
        String[] input1 = br.readLine().split(" ");
        int jewelCount = Integer.parseInt(input1[0]);
        int bagCount = Integer.parseInt(input1[1]);


        Map<Integer, List<Integer>> jewelMap = new HashMap<>();
        for (int i = 0; i < jewelCount; i++) {
            String[] jewelInfoInput = br.readLine().split(" ");
            int weight = Integer.parseInt(jewelInfoInput[0]);
            int cost = Integer.parseInt(jewelInfoInput[1]);
            List<Integer> weights = jewelMap.getOrDefault(cost, new ArrayList<>());
            weights.add(weight);
            jewelMap.put(cost, weights);
        }

        List<Jewel> jewels = new ArrayList<>();
        for (int jewelCost : jewelMap.keySet()) {
            Jewel jewel = new Jewel(jewelCost, jewelMap.get(jewelCost));
            jewels.add(jewel);
        }

        Collections.sort(jewels);

        TreeMap<Integer, Integer> bagCapacities = new TreeMap<>();
        for (int i = 0; i < bagCount; i++) {
            int bagCapacity = Integer.parseInt(br.readLine());
            int count = bagCapacities.getOrDefault(bagCapacity, 0);
            bagCapacities.put(bagCapacity, count + 1);
        }

        long stolenCost = 0;

        for (Jewel jewel : jewels) {
            List<Integer> weights = jewel.weights;
            for (Integer weight : weights) {
                Integer key = bagCapacities.ceilingKey(weight);
                if (key != null) {
                    stolenCost += jewel.cost;
                    int count = bagCapacities.get(key);
                    if(count - 1 == 0) {
                        bagCapacities.remove(key);
                    } else {
                        bagCapacities.put(key, count - 1);
                    }
                }
                if (bagCapacities.isEmpty()) {
                    break;
                }
            }
            if (bagCapacities.isEmpty()) {
                break;
            }
        }


        System.out.println(stolenCost);
    }
}
