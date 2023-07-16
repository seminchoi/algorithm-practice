import java.util.Scanner;

public class Main {
	public static int modinv (int r) {
		int x1 = 1, x2 = 0, y1 = 0, y2 = 1, tempx, tempy, quo, rem = 0;
		int p = 10007;
		while (rem != 1) {
			quo = (x1 * r + y1 *p) / (x2 * r + y2 * p);
			tempx = x2; tempy = y2;
			x2 = x1 - x2*quo; y2 = y1 - y2 * quo;
			x1= tempx; y1 = tempy;
			rem = x2 * r + y2 * p;
		}
		if(x2<0)
			x2 += p;
		return x2;
	}

	public static int com(int width, int tile) {
		int n = 1, r = 1, res;
		for(int i = 0; i < tile; i++) {
			n = n * (width - tile - i);
			if(n > 10007)
				n = n % 10007;			
		}
		for(int i = 1; i <= tile; i++) {
			r *= i;
			if(r > 10007)
				r = r % 10007;	
		}
		res = (n * modinv(r)) % 10007; 

		return res;
	}

	public static int tiling (int width) {
		int hori = width/2, res = 0;
		for (int i = 0; i <= hori; i++) {
			res += com(width,i);
			if(res > 10007)
				res %= 10007;
		}
		return res;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int width, res;
		width = sc.nextInt();
		
		res = tiling(width);
		System.out.println(res);
	}

}
