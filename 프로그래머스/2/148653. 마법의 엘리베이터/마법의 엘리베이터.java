class Solution {
    public int solution(int storey) {
        int answer = 0;
        int cur = 0, next = 0;
        while(storey >= 10) {
            cur = storey % 10;
            storey /= 10;
            next = storey % 10;
            if(cur == 5 && next >= 5) {
                answer += 5;
                storey++;
            } else if(cur <= 5) {
                answer += cur;
            } else {
                answer += 10 - cur;
                storey ++;
            }
        }
        
        if(storey <= 5) {
            answer += storey;
        }
        else {
            answer += 10 - storey + 1;
        }
        return answer;
    }
}