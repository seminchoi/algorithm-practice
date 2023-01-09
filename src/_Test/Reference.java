package _Test;

public class Reference {
    static class A{
        int a;
    }

    public static void main(String[] args) {
        A a = new A();
        A b = a;

        System.out.println(a.a);
        System.out.println(b.a);

        a.a = 3;
        System.out.println(a.a);
        System.out.println(b.a);

    }
}
