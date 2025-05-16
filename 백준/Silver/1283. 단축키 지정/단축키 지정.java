import java.util.*;
import java.io.*;

public class Main {
    static Set<Character> shortcuts = new HashSet<>();
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            var input = br.readLine();
            var lower = input.toLowerCase();

            int idx = -1;

            int length = input.length();
            for(int j = 0; j < length; j++) {
                if(j == 0 || input.charAt(j-1) == ' ') {
                    if(!shortcuts.contains(lower.charAt(j))) {
                        shortcuts.add(lower.charAt(j));
                        idx = j;
                        break;
                    }
                }
            }

            if(idx != -1) {
                markSc(input, idx);
                continue;
            }

            for(int j = 0; j < length; j++) {
                if(input.charAt(j) == ' ') continue;
                if(!shortcuts.contains(lower.charAt(j))) {
                    shortcuts.add(lower.charAt(j));
                    idx = j;
                    break;
                }
            }

            markSc(input, idx);
        }
    }

    private static void markSc(String origin, int idx) {
        for(int i = 0; i < origin.length(); i++) {
            if(i == idx) {
                System.out.printf("[%c]", origin.charAt(i));
                continue;
            }
            System.out.printf("%c", origin.charAt(i));
        }
        System.out.println();
    }
}