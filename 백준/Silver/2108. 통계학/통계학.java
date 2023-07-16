import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> arr = new ArrayList<>();
        int[] freq = new int[10000];

        int sum = 0, mid, max, min;

        long aver;
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine());
            arr.add(input);
            freq[input+4000]++;
            sum += input;
        }

        int[] freqNumArr = new int[8001];
        int maxFreqCnt = -1, maxFreqNum = 0;
        int seq = 1;
        for (int i = 0; i < 8001; i++) {
            if(freq[i] > maxFreqCnt){
                maxFreqCnt = freq[i];
                maxFreqNum = i - 4000;
                seq = 1;
            }
            else if(freq[i] == maxFreqCnt && seq == 1){
                maxFreqNum = i - 4000;
                seq++;
            }
        }
        Collections.sort(arr);
        min = arr.get(0);
        max = arr.get(n-1);
        mid = arr.get(n/2);
        aver = Math.round((double) sum/n);


        System.out.println(aver + "\n" + mid + "\n" + maxFreqNum + "\n" + (max-min));
    }
}
