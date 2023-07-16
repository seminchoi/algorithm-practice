package _test;

public class Algo {
    public static int t(int n){
        System.out.println("n = " + n);
        if(n != 1) {
            return t(n - 1) + n;
        }
        else
            return 1;
    }

    public static void main(String[] args) {
        System.out.println(t(4));
    }
}
