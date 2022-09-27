package ATM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ11399 {
    public static void main(String[] args) throws IOException {
        int n;
        List<Integer> intList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            intList.add(Integer.parseInt(st.nextToken()));
        }

        intList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        int res = 0, preTime = 0;
        for (int i = 0; i < n; i++) {
            res += preTime + intList.get(i);
            preTime += intList.get(i);
        }

        System.out.println(res);
    }
}
