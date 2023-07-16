package _test;

public class CallByValueWithStatic {
    static int a;

    public static void value(int a){
        //인자로 받은 a 값은 별도의 주소값을 가진다. (a++하는 순간)
        System.out.println("인자 a의 주소:" + System.identityHashCode(a));
        a++;
        System.out.println("인자 a의 주소 (a++ 연산 이후):" + System.identityHashCode(a));
        System.out.println("인자 a: "+a);
    }

    public static void main(String[] args) {
        a = 10;
        System.out.println("static a의 주소:" + System.identityHashCode(a));
        value(a);
        System.out.println("static a: "+a);

    }
}

