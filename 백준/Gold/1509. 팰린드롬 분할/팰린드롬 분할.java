import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        System.out.println(calculateDivisions(input));
    }

    private static int calculateDivisions(String input) {
        boolean[][] palindrome = findPalindrome(input);
        int[] divisionsDp = new int[input.length()];

        divisionsDp[0] = 1;
        for (int i = 1; i < input.length(); i++) {
            if(palindrome[0][i]) {
                divisionsDp[i] = 1;
                continue;
            }

            divisionsDp[i] = divisionsDp[i - 1] + 1;
            for (int j = 1; j <= i; j++) {
                if (palindrome[j][i]) {
                    divisionsDp[i] = Math.min(divisionsDp[i], divisionsDp[j - 1] + 1);
                }
            }
        }

        return divisionsDp[divisionsDp.length - 1];
    }


    private static boolean[][] findPalindrome(String input) {
        int length = input.length();
        boolean[][] palindrome = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            palindrome[i][i] = true;
        }
        for (int i = 0; i < length - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                palindrome[i][i + 1] = true;
            }
        }
        for (int range = 2; range < length; range++) {
            for (int i = 0; i + range < length; i++) {
                if (input.charAt(i) == input.charAt(i + range) && palindrome[i + 1][i + range - 1]) {
                    palindrome[i][i + range] = true;
                }
            }
        }

        return palindrome;
    }

}
