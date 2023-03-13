package me.whiteship.practice.functionalinterface;

import java.util.function.*;

public class FunctionInterfacPractice {
    public static void main(String[] args) {
        // Supplier : 매개변수 없이 값 반환
        Supplier<String> supplier = () -> "Hello!";
        System.out.println(supplier.get());
        System.out.println(((Supplier<String>) () -> "Hello").get());

        // Consumer : 매개변수 받아 로직 실행, 반환값 없음
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("Hello!");
        ((Consumer<String>) (s) -> System.out.println(s)).accept("Hello!");
        // Hello
        // [	Hello	]

        Consumer<String> printString = s -> System.out.println(s);
        Consumer<String> printStringPrettier = s -> System.out.println("[\t"+s+"\t]");
        printString.andThen(printStringPrettier).accept("Hello");

        // Predicate
        Predicate<Integer> isAdult = i -> Integer.compare(i, 19) > 0;
        System.out.println(isAdult.test(20)); // true
        System.out.println(isAdult.test(15)); // false

        Predicate<Integer> isNotAdult = isAdult.negate();
        System.out.println(isNotAdult.test(20)); // false
        System.out.println(isNotAdult.test(15)); // true

        Predicate<Integer> isNotKids = i -> Integer.compare(i, 9) > 0;
        Predicate<Integer> isTeen = isNotAdult.and(isNotKids);
        System.out.println(isTeen.test(25)); // false
        System.out.println(isTeen.test(15)); // true

        Predicate<Object> helloPredicate = Predicate.isEqual("Hello");
        System.out.println(helloPredicate.test("Hello")); // true
        System.out.println(helloPredicate.test("hello")); // hello

        // BiFunction
        BiFunction<Integer, Float, String> multiplyFloor =
                (integer, aFloat) -> String.valueOf(Math.floor(integer * aFloat));
        System.out.println(multiplyFloor.apply(3, 2.6F)); // 7.0

        // BiConsumer
        BiConsumer<String, String> printState = (s1, s2) -> System.out.println(String.format("[s1] : %s\t[s2] : %s", s1, s2));
        printState.accept("Hello!", "Good Morning"); // [s1] : Hello!	[s2] : Good Morning

        // BiPredicate
        BiPredicate<Integer, String> isOurStudent = (age, name) -> age == 19 && name == "Tom";
        System.out.println(isOurStudent.test(19, "Tom"));
        System.out.println(isOurStudent.test(23, "Bob"));

        // UnaryOperator
        UnaryOperator<Integer> plus10 = (i) -> i + 10;
        UnaryOperator<Integer> multiply2 = (i) -> i * 2;
        System.out.println(plus10.andThen(multiply2).apply(2));

        // BinaryOperator
        BinaryOperator<Integer> binaryAdder = (i1, i2) -> i1 + i2;
        System.out.println(binaryAdder.apply(1, 2));

    }
}
