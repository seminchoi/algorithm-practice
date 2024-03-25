import java.util.*;
import java.util.stream.*;
import java.util.Arrays;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        sortData(data, col);
        
        int answer = 0;
        for(int i = row_begin - 1; i < row_end; i++) {
            int moduler = i+1;
            int si = IntStream.of(data[i])
                .map(value -> value % moduler)
                .sum();
            
            answer ^= si;
        }

        return answer;
    }
    
    public void sortData(int[][] data, int col) {
        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] row1, int[] row2) {
                int colIndex = col - 1;
                if(row1[colIndex] == row2[colIndex]) {
                    return row2[0] - row1[0];
                }
                return row1[colIndex] - row2[colIndex];
            }
        });
    }
}