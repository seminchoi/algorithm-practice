package _test;

import java.util.function.*;

public class LamdaFunction {
    public static void main(String[] args) {


        Runnable runnable = () -> System.out.println("Runnable 인터페이스");
        runnable.run();


        Supplier<Integer> supplier = () -> {
            int s = 3;
            return s;
        };
        int x = supplier.get();
        System.out.println("Supplier 인터페이스" + x);


        Consumer<Integer> consumer = (i) -> System.out.println("Consumer 인터페이스:" + i);
        consumer.accept(x);


        Function<String, Integer> function1 = (s) -> Integer.parseInt(s);
        int y = function1.apply("35");
        System.out.println("Function 인터페이스1: " + y);


        Predicate<Integer> predicate = i -> i % 2 == 0;
        boolean isEven = predicate.test(24);
        System.out.println("24가 짝수인지 확인하는 Predicate: " + isEven);



        Function<String, Integer> stringToInt = Integer::parseInt;
        Function<Integer,Double> circleArea = i -> i * i * 3.14D;
        Function<String,Double> andThen = stringToInt.andThen(circleArea);
        System.out.println("반지름이 3인 원의 넓이: " + andThen.apply("3"));


        Function<Integer, String> intToBinString = Integer::toBinaryString;
        Function<String,String> compose = intToBinString.compose(stringToInt);
        Function<Integer,Integer> andthen = intToBinString.andThen(stringToInt);

        System.out.println("compose 를 이용해 합성 (숫자 8을 2진수 문자열로 출력): " + compose.apply("8"));
        System.out.println("andThen 을 이용해 합성 (숫자 8을 2진수로 만든 문자열을 다시 그대로 숫자로 출력): " + andthen.apply(8));

        Function.identity().apply(3);

        Predicate<Integer> p = Predicate.not((Integer i) -> i > 30);

    }
}
