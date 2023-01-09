package _Test;

public class SwapReference {

    static void swap (int t1, int t2){
        int tmp = t1;
        t1 = t2;
        t2 = tmp;
    }

    public static void main(String[] args) {

        int n1 = 3;
        int  n2 = 4;

        swap(n1,n2);

        System.out.println(n2);
    }
}
