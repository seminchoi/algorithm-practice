import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(int n, int k) {
        List<Integer> kInt = new ArrayList<>();
        List<Long> noZero = new ArrayList<>();

        while(n != 0){
            kInt.add(n % k);
            n /= k;
        }

        System.out.println();
        int size = kInt.size();
        long num = 0;
        for(int i = size-1; i >= 0; i--){
            if(kInt.get(i) != 0){
                num = num * 10 + kInt.get(i);
            }
            else{
                if(num == 0)
                    continue;
                else if(num == 1){
                    num = 0;
                    continue;
                }
                else{
                    noZero.add(num);
                    System.out.println(num);
                    num = 0;
                }
            }   
        }
        
        if(num > 1){
            noZero.add(num);
        }
        
        int answer = 0;

        for(long i : noZero){
            long sqrt = (long)Math.sqrt(i);
            boolean isPrime = true;
            for(long j = 2; j < sqrt+1; j++){
                if(i % j == 0){
                    isPrime = false;
                    break;
                }
            }
            if(isPrime)
                answer++;
        }

        
        return answer;
    }
}