package me.whiteship.practice.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPIPractice {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> keesunEvents = new ArrayList<>();
        keesunEvents.add(springClasses);
        keesunEvents.add(javaClasses);

        System.out.println("spring 으로 시작하는 수업");
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("close 되지 않은 수업");
        springClasses.stream()
//                .filter(oc -> !oc.isClosed())
                .filter(Predicate.not(OnlineClass::isClosed))
                .forEach(oc -> System.out.println(oc.getId()));


        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(oc -> oc.getTitle()) // OnlineClass -> String
//                .forEach(title -> System.out.println(title))
                .forEach(System.out::println);


        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        // flatMap -> List
        // Collection:stream : Stream
        keesunEvents.stream().flatMap(list -> list.stream())
                .forEach(oc -> oc.getId());

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println("test = " + test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        List<String> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().contains("spring"))
                .map(oc -> oc.getTitle())
                .collect(Collectors.toList());
        spring.forEach(System.out::println);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 수업만 모아서 List로 만들기");
        List<OnlineClass> onlineClassList = springClasses.stream().filter(oc -> oc.getTitle().contains("spring"))
                .collect(Collectors.toList());
        onlineClassList.forEach(oc -> System.out.println(oc.getTitle()));

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 수업의 개수 세기");
        long count = onlineClassList.stream()
                .filter(oc -> oc.getTitle().contains("spring"))
                .count();
        System.out.println("count = " + count);

    }
}
