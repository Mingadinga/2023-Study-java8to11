package me.whiteship.practice.Annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // 애노테이션 정보를 언제까지 유지할 것인지
//@Target(ElementType.TYPE_PARAMETER) // 애노테이션을 사용할 곳 - 제네릭 앞에 선언
@Target(ElementType.TYPE_USE) // 타입을 선언하는 모든 곳에 선언
@Repeatable(ChickenContainer.class)
public @interface Chicken {
    String value(); // 무슨 치킨인지 설명하는 문자열
}
