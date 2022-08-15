package _Test;

public class Anonymous_class {

    static class TestClass{
        int n;
    }

    static TestClass tc = new TestClass(){
        int n = 2;
        int m = 3;
        String s = "안녕";
    };


    interface TestInterface{
        public abstract int max(int a, int b);
    }


    TestInterface ti1 = new TestInterface() {
        @Override
        public int max(int a, int b) {
            return a > b ? a : b;
        }
    };

    TestInterface ti2 = (a, b) -> a > b ? a : b;

    public static void main(String[] args) {
//        System.out.println(tc.s);

        TestClass tc = new TestClass(){
            int n = 2;
            String s = "안녕";
        };

    }

}
