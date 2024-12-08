import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String[] split = input.split(" ");
        long m = Integer.parseInt(split[0]);
        long n = Integer.parseInt(split[1]);

        long breakCount;
        if(n >= m) {
            breakCount = m * 2 - 2;
        } else {
            breakCount = n * 2 - 1;
        }

        long quotient = breakCount / 4;

        System.out.println(breakCount);
        switch((int) (breakCount % 4L)) {
            case 0: System.out.printf("%d %d\n", quotient + 1, n - quotient); break;
            case 1: System.out.printf("%d %d\n", m - quotient, n - quotient); break;
            case 2: System.out.printf("%d %d\n", m - quotient, quotient + 1); break;
            case 3: System.out.printf("%d %d\n", quotient + 2, quotient + 1); break;
        }
    }
}