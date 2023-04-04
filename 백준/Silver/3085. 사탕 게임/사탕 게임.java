import java.util.*;

public class Main {
	
	
	public static int N, max = 1;
	
	public static char[][] board;
	
	public static void swap1(int i, int j){
		if(board[i][j] == board[i][j+1]) {
			return;
		}
		char tmp = board[i][j];		
		board[i][j] = board[i][j+1];
		board[i][j+1] = tmp;
	}
	
	public static void swap2(int i, int j){
		if(board[i][j] == board[i+1][j]) {
			return;
		}
		char tmp = board[i][j];		
		board[i][j] = board[i+1][j];
		board[i+1][j] = tmp;
	}
	public static void chk() {
		int cnt = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N-1; j++) {
				
				if(board[i][j] == board[i][j+1])
					cnt++;
				else {					
					max = Math.max(cnt,max);
					cnt = 1;
				}
			}

			max = Math.max(cnt,max);
			if(max == N)
				return;
			cnt = 1;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N-1; j++) {
				if(board[j][i] == board[j+1][i])
					cnt++;
				else {					
					max = Math.max(cnt,max);
					cnt = 1;
				}				
			}
			max = Math.max(cnt,max);
			if(max == N)
				return;				
			cnt = 1;
		}
		return;
	}

	public static void main(String[] args) {		
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		board = new char[N][N];
		String s;
		for(int i = 0; i < N; i++) {
			s = sc.next();
			for(int j = 0; j < N; j++)
				board[i][j] = s.charAt(j);
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N -1 ; j++) {
				swap1(i,j);
				chk();
				swap1(i,j);
				swap2(j,i);
				chk();
				swap2(j,i);
					
			}
		}
		
		System.out.println(max);

	}

}