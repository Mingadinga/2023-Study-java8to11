package me.whiteship.practice.functionalinterface;

import java.util.function.Function;

// T 타입을 받아 R 타입을 반환한다
// Function<T, R>
public class Plus10 implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }

    public static void main(String[] args) {
        // 함수형 인터페이스를 구현한 클래스 사용
        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(1));

        // 클래스 구현 없이 람다로 바로 사용
        Function<Integer, Integer> plus10lambda = integer -> integer + 10;
        System.out.println(plus10lambda.apply(1));

        // compose
        Function<Integer, Integer> multiply2 = integer -> integer * 2;
        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);// 적용 순서 : multiply -> plus
        System.out.println(multiply2AndPlus10.apply(2));

        // andThen
        System.out.println(plus10.andThen(multiply2).apply(2));
    }
}
