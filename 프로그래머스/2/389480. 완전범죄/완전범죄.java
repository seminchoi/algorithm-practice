import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
    public int solution(int[][] info, int n, int m) {
        // 차가 큰 것이 앞으로 오게
        // 차가 같다면 A가 더 작은게 더 앞으로 오게

        // B가 A보다 더 큰게 먼저오게
        // 차가 같다면 B가 큰게 먼저오게

        List<int[]> list = Stream.of(info).sorted((a, b) -> {
            if (a[1] - a[0] == b[1] - b[0]) {
                return b[1] - a[1];
            } else {
                return (a[1] - a[0]) - (b[1] - b[0]);
            }
        }).collect(Collectors.toList());

        int nScore = 0;
        int mScore = 0;
        for (int i = 0; i < list.size(); i++) {
            if (mScore + list.get(i)[1] < m) {
                mScore += list.get(i)[1];
            } else {
                nScore += list.get(i)[0];
            }
        }

        int answer = nScore;
        if(nScore >= n) {
            answer = -1;
        }
        return answer;
    }
}