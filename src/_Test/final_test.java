package _Test;

public class final_test {

    static class Hello{
        static int x;

        static void mymethod(){
            System.out.println(x);
        }

    }
    public static void main(String[] args) {
        System.out.println("oo");
        Hello.mymethod();
        Hello.x = 5;
        Hello.mymethod();

    }
}
