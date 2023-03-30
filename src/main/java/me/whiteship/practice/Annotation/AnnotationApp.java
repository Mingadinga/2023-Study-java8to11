package me.whiteship.practice.Annotation;

import java.util.Arrays;
import java.util.List;

@Chicken("양념")
@Chicken("마늘간장")
public class AnnotationApp {

    public static void main(String[] args) {
        List<@Chicken("허니콤보") String> names = Arrays.asList("min");


        Chicken[] chickens = AnnotationApp.class.getAnnotationsByType(Chicken.class);
        Arrays.stream(chickens).forEach(c -> System.out.println(c.value()));

        ChickenContainer chickenContainer = AnnotationApp.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c -> System.out.println(c.value()));


    }


    static class FeelsLikeChicken<@Chicken("생닭") T> {
        public static <@Chicken("후라이드") C> void print (@Chicken("후라이드") C c) {
            System.out.println(c);
        }
    }

}
