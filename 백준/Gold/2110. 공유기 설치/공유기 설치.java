import java.util.*;
import java.io.*;

public class Main {
    static int min = Integer.MAX_VALUE, max = 0;
    public static void main(String[] args) throws IOException {
        // 최소거리와 최대거리를 이용해서 가장 먼 공유기 거리를 구합니다.
        // 거리를 정하고 거리보다 먼 집이 등장하기 전에 공유기를 설치해야 합니다.
        // 거리가 작으면 끝까지 설치할 수 없습니다. 중간에 못넘어가는 구간이 있는지 확인하고 예외처리 해야합니다.
        // 거리가 크면 공유기를 다 설치할 수 없습니다.
        var br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int c = Integer.parseInt(input1[1]);

        int[] dis = new int[n];
        for(int i = 0; i < n; i++) {
            dis[i] = Integer.parseInt(br.readLine());

        }
        Arrays.sort(dis);
        min = 0;
        max = dis[n-1] + 1;
        System.out.println(bs(dis,c));
    }

    private static int bs(int[] dis, int c) {
        while(min < max) {
            int mid = (min + max) / 2;
            int count = count(dis, mid);
            if(count < c) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        return min - 1;
    }

    private static int count(int[] dis, int distance) {
        int posIdx = 0;
        int count = 1;
        while(true) {
            int pos = dis[posIdx];
            int nextPos = Arrays.binarySearch(dis, pos + distance);
            if(nextPos < 0) {
                nextPos = -nextPos - 1;
            }
            if(nextPos == dis.length) {
                break;
            }
            posIdx = nextPos;
            count++;
        }
        return count;
    }
}