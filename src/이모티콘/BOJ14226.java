package 이모티콘;
//저장 , 붙여넣기, 삭제
//클립보드 저장개수, 현재 개수, 계층 횟수

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ14226 {

    static int S;
    static boolean[][] vis = new boolean[2000][2000];
    static Queue<Integer>[] q = new Queue[3];
    public static int bfs(){
        while(!q[0].isEmpty()){
            int n = q[0].poll();
            int clip = q[1].poll();
            int min = q[2].poll();
//            System.out.println(n +", " + clip+ ", " + min);
            if(vis[n][clip])
                continue;
            vis[n][clip] = true;

            if(n > S){
                q[0].offer(n-1);
                q[1].offer(clip);
                q[2].offer(min+1);
                if(n-1 == S) {
                    return min+1;
                }
            }
            else{
//              목적지보다 숫자가 적은데 지울 일이 있는가?
                if(n > 1){
                    q[0].offer(n-1);
                    q[1].offer(clip);
                    q[2].offer(min+1);
                }
                if(n != clip){
                    q[0].offer(n);
                    q[1].offer(n);
                    q[2].offer(min+1);
                }
                if(clip != 0) {
                    q[0].offer(n + clip);
                    q[1].offer(clip);
                    q[2].offer(min + 1);
                    if(n+clip == S)
                        return min +1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        S = sc.nextInt();

        q[0] = new LinkedList<>(); //현재 이모티콘 입력 개수
        q[1] = new LinkedList<>(); //현재 클립보드에 저장된 이모티콘 개수
        q[2] = new LinkedList<>(); //현재 경과 시간

        q[0].offer(1);
        q[1].offer(0);
        q[2].offer(0);

        System.out.println(bfs());
    }
}
