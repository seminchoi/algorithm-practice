import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);

        int nextRound = 6;
        int finalNum = 1;
        int result = 1;

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        while (finalNum < n){
            finalNum += nextRound;
            nextRound += 6;
            ++result;
        }

        System.out.println(result);
    }
}
