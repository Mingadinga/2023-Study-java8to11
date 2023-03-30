package me.whiteship.practice.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 포함할 애노테이션보다 범위가 같거나 넓어야함
@Target(ElementType.TYPE_USE) // 포함할 애노테이션보다 범위가 같거나 넓어야함
public @interface ChickenContainer {
    Chicken[] value(); // 자신이 감쌀 애노테이션을 배열로 가짐
}
