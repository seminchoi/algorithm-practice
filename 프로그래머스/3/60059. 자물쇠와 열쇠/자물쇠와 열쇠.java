import java.util.*;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int count = 0;
        for(int i = 0; i < lock.length; i++) {
            for(int j = 0; j < lock[0].length; j++) {
                if(lock[i][j] == 0) {
                    count++;
                } 
            }
        }
        
    
        int[][] rotateKey = key;
        for(int i = 0; i < 4; i++) {
            if(match(rotateKey, lock, count)) {
                return true;
            }
            if(i < 3) {
                rotateKey = rotate(rotateKey);
            }
        }
        
        return false;
    }
    
    private int[][] rotate(int[][] origin) {
        int[][] result = new int[origin[0].length][origin.length];
        
        for(int i = 0; i < origin[0].length; i++) {
            for(int j = 0; j < origin.length; j++) {
                result[i][j] = origin[origin.length - j - 1][i];
            }
            System.out.println(Arrays.toString(result[i]));
        }
        
        
        return result;
    }
    
    private boolean match(int[][] key, int[][] lock, int count) {
        int size = Math.max(lock.length, lock[0].length);
        for(int i = -size; i < lock.length; i++) {
            for(int j = -size; j < lock[0].length; j++) {
                int matchCount = 0;
                boolean stop = false;
                for(int ky = 0; ky < lock.length - i && ky < key.length; ky++) {
                    for(int kx = 0; kx < lock[0].length -j && kx < key[0].length; kx++) {
                        if(ky + i < 0 || kx + j < 0) {
                            continue;
                        }
                        if(lock[ky + i][kx + j] == 0) {
                            if(key[ky][kx] == 1) {
                                matchCount++;
                            } else {
                                stop = true;
                                break;
                            }
                        } else {
                            if(key[ky][kx] == 1) {
                                stop = true;
                                break;
                            }
                        }
                    }
                    if(stop) {
                        break;
                    }
                }
                System.out.println(stop);
                if(matchCount == count && !stop) {
                    return true;
                }
            }
        }
        return false;
    }
}