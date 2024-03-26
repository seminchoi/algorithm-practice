import java.util.*;
import java.util.stream.*;


class Solution {
    public static final int INFINITE = -1;

    public int solution(String numbers) {
        int[] numberList = parseNumbers(numbers);
        int length = numberList.length;
        int[][][] dp = new int[length + 1][10][10];
       
        initDp(dp);
        
        for(int i = 1; i < length + 1; i++) {
            calculateDp(dp, i, numberList[i-1]);
        }
        
        int answer = INFINITE;
        
        for(int left = 0; left < 10; left++) {
            for(int right = 0; right < 10; right++) {
                answer = caculateOptimal(answer, dp[length][left][right]);
            }
        }
        
        return answer;
    }
    
    private int[] parseNumbers(String numbers) {
        return Arrays.stream(numbers.split(""))
            .mapToInt(Integer::parseInt)
            .toArray();
    }

    private void initDp(int[][][] dp) {
        for(int[][] layer: dp) {
            for(int[] row : layer) {
                Arrays.fill(row, INFINITE);
            }
        }
        
        dp[0][4][6] = 0;
    }
    
    private void calculateDp(int[][][] dp, int depth, int targetNumber) {
        int[][] layer = dp[depth - 1];
        
        for(int left = 0; left < 10; left++) {
            for(int right = 0; right < 10; right++) {
                int weight = layer[left][right];
                if(weight != INFINITE) {
                    if(right != targetNumber) {
                        dp[depth][targetNumber][right] = caculateOptimal(dp[depth][targetNumber][right], weight + getDistance(left, targetNumber));
                    }
                    if(left != targetNumber) {
                        dp[depth][left][targetNumber] = caculateOptimal(dp[depth][left][targetNumber], weight + getDistance(right, targetNumber));
                    }
                }
            }
        }
    }

    private int getDistance(int curNumber, int targetNumber) {
        int[] curPosition = getPosition(curNumber);
        int[] targetPosition = getPosition(targetNumber);
        int dx = Math.abs(curPosition[0] - targetPosition[0]);
        int dy = Math.abs(curPosition[1] - targetPosition[1]);


        if(dx + dy == 0) {
            return 1;
        }

        return (dx * 2) + (dy * 2) - Math.min(dx,dy);
    }

    private int[] getPosition(int number) {
        int[] pos = new int[2];

        number = number == 0 ? 11 : number;
        pos[0] = (number - 1) / 3;
        pos[1] = (number - 1) % 3;

        return pos;
    }
    
    private int caculateOptimal(int curWeight, int newWeight) {
        if(curWeight == INFINITE) {
            return newWeight;
        }
        if(newWeight == INFINITE) {
            return curWeight;
        }
        if(curWeight > newWeight) {
            return newWeight;
        }
        return curWeight;
    }

}