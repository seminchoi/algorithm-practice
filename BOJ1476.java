import java.util.*;

public class BOJ1476 {
	
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		int E, S, M ;
		
		E = sc.nextInt();
		S = sc.nextInt();
		M = sc.nextInt();
				
		int e = 1, s = 1, m = 1, res = 1;
		
		while ( E != e || S != s || M != m) {
			e = e + 1 > 15 ? 1 : e + 1;
			s = s + 1 > 28 ? 1 : s + 1;
			m = m + 1 > 19 ? 1 : m + 1;
			res++;
		}
		
		System.out.println(res);
	}
}
