package me.whiteship.functionalinterface;

import java.util.function.*;

public class Lambda {

    public static void main(String[] args) {
        Lambda lambda = new Lambda();
        lambda.run();
    }

    private void run() {

        // 인자 표현
        IntConsumer printInt = (i) -> {
            System.out.println(i);
        };

        Supplier<Integer> getBase = () -> 10;

        BiConsumer<String, String> stringBiConsumer = (s1, s2) -> {
            System.out.println(s1+"\t"+s2);
        };

        IntConsumer printIntPrettier = (int i) -> {
            System.out.println("======="+i+"=======");
        };

        // 함수 본문 표현
        BiFunction<Integer, Float, String> multiplyFloor = (integer, aFloat) -> {
            double floor = Math.floor(integer * aFloat);
            return String.valueOf(floor);
        };

        // 변수 캡쳐
        int baseNumber = 10;

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11;
                System.out.println(baseNumber);
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            int baseNumber = 11;
            @Override
            public void accept(Integer integer) {
                System.out.println(baseNumber);
            }
        };

        // 람다
//        IntConsumer intConsumer = (baseNumber) -> System.out.println(baseNumber);
        // Variable 'baseNumber' is already defined in the scope
    }

}
