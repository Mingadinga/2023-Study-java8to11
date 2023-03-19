package me.whiteship.practice.optional;

import java.util.Optional;
import java.util.OptionalInt;

public class App {
    public static void main(String[] args) {
        OnlineClass springBoot = new OnlineClass(1, "spring boot", true);
        springBoot.setProgress(null); // null 자체를 넘기는 것을 막을 수 없음

        // Premitive Type Optional
        // Optional.of는 안에서 박싱 언박싱을 하기 때문에 성능 떨어짐
        Optional.of(10);
        OptionalInt.of(10);
    }
}
