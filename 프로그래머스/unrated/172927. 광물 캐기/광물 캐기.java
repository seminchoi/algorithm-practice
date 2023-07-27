import java.util.Arrays;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        int numberOfMining = (picks[0] + picks[1] + picks[2]) * 5;
        int maxIndex = Math.min(numberOfMining, minerals.length);
 
        int[][] mineralCountArray = new int[(maxIndex-1)/5 + 1][3];
        
        
        for(int i = 0; i < (maxIndex-1)/5 + 1; i++) {
            for(int j = 0; j < 5; j++) {
                if(i*5+j >= minerals.length)
                    break;
                if(minerals[i*5+j].equals("diamond")) {
                    mineralCountArray[i][0]++;
                }
                else if(minerals[i*5+j].equals("iron")) {
                      mineralCountArray[i][1]++;
                }
                else {
                     mineralCountArray[i][2]++;
                }
            }
        }
        
        Arrays.sort(mineralCountArray, (data1, data2) -> {
            if(data1[0] != data2[0]) {
                return data2[0] - data1[0];
            } else if (data1[1] != data2[1]) {
                return data2[1] - data1[1];
            } else {
                return data2[2] - data1[2];
            }
        });
        
        for(int[] data : mineralCountArray) {
            int pick = getPick(picks);
            for(int i = 0; i < 3; i++) {
                int mult = 1;
                if(pick > i) {
                    mult = (int)Math.pow(5, pick - i);
                }
                answer += data[i] * mult;
            }
        }
        
        return answer;
    }
    
    public int getPick(int[] picks) {
        if(picks[0] > 0) {
            picks[0]--;
            return 0;
        }
        else if(picks[1] > 0) {
            picks[1]--;
            return 1;
        }
        else {
            picks[2]--;
            return 2;
        }
    }
}