class Solution {
private static final int[] result = new int[2];
    public int[] solution(int[][] users, int[] emoticons) {
        int[][] emotionPrices = new int[5][emoticons.length];
        for (int i = 0; i < emoticons.length; i++) {
            for (int j = 1; j < 5; j++) {
                emotionPrices[j][i] = emoticons[i] * (10 - j) / 10;
                // System.out.println(emotionPrices[j][i]);
            }
        }

        backTracking(0, users, emotionPrices, new int[emoticons.length][2]);

        return result;
    }

    private void backTracking(int depth, int[][] users, int[][] emoticonPrices, int[][] selectedEmoticonPrices) {
        if(depth == emoticonPrices[0].length) {
            calculateResult(users, selectedEmoticonPrices);
            return;
        }

        for (int i = 1; i < 5; i++) {
            selectedEmoticonPrices[depth][0] = i * 10;
            selectedEmoticonPrices[depth][1] =  emoticonPrices[i][depth];
            backTracking(depth + 1, users, emoticonPrices, selectedEmoticonPrices);
        }
    }

    private void calculateResult(int[][] users, int[][] selectedEmoticonPrices) {
        int[] localResult = new int[2];

        for (int[] user : users) {
            int totalPrice = 0;
            for (int[] selectedEmoticonPrice : selectedEmoticonPrices) {
                if(user[0] <= selectedEmoticonPrice[0]) {
                    totalPrice += selectedEmoticonPrice[1];
                }
            }
            if(user[1] <= totalPrice) { 
                localResult[0]++;
            }
            else {
                localResult[1] += totalPrice;
            }
        }
        
        
        if(result[0] < localResult[0]) {
            result[0] = localResult[0];
            result[1] = localResult[1];
        }
        
        if(result[0] == localResult[0] && result[1] < localResult[1]) {
            result[1] = localResult[1];
        }
    }
}