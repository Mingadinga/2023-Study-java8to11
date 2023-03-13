package me.whiteship.practice.functionalinterface;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {
    public static void main(String[] args) {
        Supplier<Greeting> defaultConstructor = Greeting::new;
        Greeting greeting1 = defaultConstructor.get();

        Function<String, Greeting> nameConstructor = Greeting::new;
        Greeting greeting2 = nameConstructor.apply("minpearl");

        Function<String, String> staticHi = Greeting::hi;
        System.out.println(staticHi.apply("Min"));

        Greeting greeting3 = defaultConstructor.get();
        Function<String, String> hello = greeting3::hello;
        System.out.println(hello.apply("min"));

        String[] names = {"min", "hwi", "seeun"};
        Arrays.sort(names, (o1, o2) -> o1.compareToIgnoreCase(o2)); // 람다식
        Arrays.sort(names, String::compareToIgnoreCase); // 메소드 레퍼런스

    }

}
