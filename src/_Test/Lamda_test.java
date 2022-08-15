package _Test;

public class Lamda_test {

    interface Lamda{
//        public abstract int max(int a, int b);
        public abstract int pow(int n);
    }
    public static void main(String[] args) {

        Lamda lamda = n -> n * n;

//        Lamda lamda = new Lamda() {
//            @Override
//            public int max(int a, int b) {
//                return 0;
//            }
//        }


//        아래와 같이 람다식을 짜면 int를 반환할 것이라고 생각했지만 그렇지 않다.
//        람다식은 하나의 객체를 반환한다고 볼 수 있다.
//        int a = 1, b = 2;
//        int c = (a,b) -> a > b ? a : b;
    }
}
