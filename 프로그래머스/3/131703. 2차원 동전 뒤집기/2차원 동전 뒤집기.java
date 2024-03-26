class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int[] compareBit = createCompareBit(beginning, target);
        return calculateAnswer(compareBit, beginning[0].length);
    }
    
    private int[] createCompareBit(int[][] beginning, int[][] target) {
        int[] compareBit = new int[beginning.length];
        for(int i = 0; i < beginning.length; i++) {
            for(int j = 0; j < beginning[0].length; j++) {
                compareBit[i] = compareBit[i] << 1;
                compareBit[i] = beginning[i][j] != target[i][j] ? compareBit[i] + 1: compareBit[i];
            }
        }
        return compareBit;
    }
    
    private int calculateAnswer(int[] compareBit, int length) {
        int standard = compareBit[0];
        int answer = 0;
        int fullBit = 0;
        
        for(int i = 0; i < length; i++) {
            fullBit = fullBit << 1;
            fullBit++;
        }

        
        for(int i = 1; i < compareBit.length; i++) {
            int bitOr = standard ^ compareBit[i];
            if(bitOr == 0 || standard == compareBit[i]) {
                continue;
            } 
            else if(bitOr == fullBit) {
                answer++;
                continue;
            }
            else { 
                return -1;
            }
        }
        
        int row = 0;
        int standardTmp = standard;
        for(int i = 0; i < length; i++) {
            row = standardTmp % 2 == 1 ? row+1 : row;
            standardTmp /= 2;
        }
        
        if(standard == fullBit) {
            return compareBit.length - answer;
        }
        
        int rowConvert = row + answer;
        int colConvert = (length - row) + (compareBit.length - answer);
        
        return Math.min(rowConvert, colConvert);
    }
}