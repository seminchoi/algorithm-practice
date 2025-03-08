import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static int[] dx = {-1, 0, 1, 0, 1, 1, -1, -1}, dy = {0, -1, 0, 1, 1, -1, 1, -1};
    public static int[] points = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    public static boolean[][] visit = new boolean[4][4];
    public static Trie trie = new Trie();
    public static List<String> answers = new ArrayList<>();

    public static class Trie {
        Map<Character, Trie> children = new HashMap<>();
        boolean isFinish = false;

        public Trie() {
        }

        public void insert(char[] word) {
            Trie curNode = this;
            for (char c : word) {
                if (!curNode.children.containsKey(c)) {
                    curNode.children.put(c, new Trie());
                }
                curNode = curNode.children.get(c);
            }
            curNode.isFinish = true;
        }

        public boolean search(String word) {
            Trie curNode = this;
            for (int i = 0; i < word.length(); i++) {
                curNode = curNode.children.get(word.charAt(i));
                if (curNode == null) {
                    return false;
                }
            }
            return curNode.isFinish;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input1 = Integer.parseInt(br.readLine());

        for (int i = 0; i < input1; i++) {
            String input2 = br.readLine();
            trie.insert(input2.toCharArray());
        }

        br.readLine();
        int dices = Integer.parseInt(br.readLine());

        for (int i = 0; i < dices; i++) {
            char[][] dice = new char[4][4];
            for (int j = 0; j < 4; j++) {
                String row = br.readLine();
                for (int k = 0; k < 4; k++) {
                    dice[j][k] = row.charAt(k);
                }
            }
            solution(dice);
            if (i < dices - 1) {
                br.readLine();
            }
        }
        for (String answer : answers) {
            System.out.println(answer);
        }
    }

    private static void solution(char[][] dice) {
        Set<String> findWords = new HashSet<>();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                dfs(findWords, "", dice, y, x);
            }
        }

        String longest = "";
        int point = 0;
        for (String word : findWords) {
            if (word.length() > longest.length()) {
                longest = word;
            } else if (word.length() == longest.length() && word.compareTo(longest) < 0) {
                longest = word;
            }
            point += points[word.length()];
        }

        String answer = String.format("%d %s %d", point, longest, findWords.size());
        answers.add(answer);
    }

    private static void dfs(Set<String> findWords, String word, char[][] dice, int y, int x) {
        if (word.length() > 8) {
            return;
        }

        visit[y][x] = true;
        word += dice[y][x];
        if (trie.search(word)) {
            findWords.add(word);
        }


        for (int i = 0; i < 8; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextX >= 4 || nextY < 0 || nextY >= 4) continue;
            if (visit[nextY][nextX]) continue;

            dfs(findWords, word, dice, nextY, nextX);
        }
        visit[y][x] = false;
    }
}