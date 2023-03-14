package me.whiteship.practice.nm;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        Foo foo = new DefaultFoo("min");
        foo.printName();
        foo.printNameUpperClass();
        Foo.printAnything();

        System.out.println("==============");

        // Iterable
        List<String> nameList = new ArrayList<>();
        nameList.add("min");
        nameList.add("hwi");
        nameList.add("pearl");

        // forEach - 반복
        nameList.forEach((s) -> System.out.println(s)); // Consumer 인터페이스 받음
        nameList.forEach(System.out::println); // 메소드 레퍼런스로도 가능
        System.out.println("==============");

        // spliterator - 병렬 처리에 유용
        Spliterator<String> spliterator = nameList.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();
        // spliterator는 뒤에 절반,  // spliterator1는 앞에 절반
        while(spliterator.tryAdvance(System.out::println)); // Consumer을 받음
        System.out.println("==============");
        while(spliterator1.tryAdvance(System.out::println)); // Consumer을 받음

        // Collection

        // stream, parallelStream
        // spliterator을 사용함
        // element를 스트림으로 만들어 함수 처리를 할 수 있다
        // map(Functional), filter(Predicate)
        nameList.stream().map(String::toUpperCase)
                .filter(s -> s.startsWith("K")).collect(Collectors.toList());

        // removeIf(Predicate)
        nameList.removeIf(s -> s.startsWith("m"));
        nameList.forEach(System.out::println);


        // Comparator

        // reversed()
        // Comparator의 기본 메소드
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        nameList.sort(compareToIgnoreCase.reversed());

        // thenComparing()
        // 두개의 Comparator 실행에 순서 지정
        // 대소문자 구분 없이 알파벳 오름차순 정렬 후 길이대로 정렬
        nameList.sort(compareToIgnoreCase.thenComparing(String::length));

        // static reverseOrder() , naturalOrder()
        List<Integer> integerList = Arrays.asList(5, 2, 3, 5, 1);

        integerList.sort(Comparator.naturalOrder()); // 오름차순
        integerList.forEach(System.out::println);

        integerList.sort(Comparator.reverseOrder()); // 내림차순
        integerList.forEach(System.out::println);


        // static comparing(Function, Comparator)
        // 객체 비교
        // stream의 sort()에 넘겨줄 수 있다
        List<Student> studentList = Arrays.asList(
                new Student(2, "Kim", Student.ClassName.A),
                new Student(3, "Shin", Student.ClassName.B),
                new Student(3, "Lee", Student.ClassName.C),
                new Student(2, "Kang", Student.ClassName.C),
                new Student(1, "Chul", Student.ClassName.A)
        );

        Comparator<Student> comparingAge = Comparator.comparing(Student::getAge, Comparator.naturalOrder());
        studentList.stream()
                .sorted(comparingAge)
                .forEach(o -> {
                    System.out.println(o.getAge() + " : " + o.getName() + " : " + o.getClassName());
                });

        // static nullsFirst() , nullsLast()
        // 정렬할 때 null 앞쪽으로, 뒤쪽으로 밀어줌
        studentList = Arrays.asList(
                new Student(2, "Kim", Student.ClassName.A),
                new Student(3, "Shin", Student.ClassName.B),
                new Student(3, "Lee", null),
                new Student(2, "Kang", null),
                new Student(1, "Chul", Student.ClassName.A),
                new Student(1, "Jang", Student.ClassName.B),
                new Student(3, "Ahn", Student.ClassName.A),
                new Student(1, "Hawng", null),
                new Student(2, "Lim", Student.ClassName.B)
        );

        // 반 이름 정렬(오름차순) + NULL First
        Comparator<Student> nullFirst = Comparator
                .comparing(Student::getClassName, Comparator.nullsFirst(Comparator.naturalOrder()));

        studentList.stream()
                .sorted(nullFirst)
                .forEach(o -> {
                    System.out.println(o.getAge() + " : " + o.getName() + " : " + o.getClassName());
                });

        // 반 이름 정렬(오름차순) + NULL Last
        Comparator<Student> nullLast = Comparator
                .comparing(Student::getClassName, Comparator.nullsLast(Comparator.naturalOrder()));

        studentList.stream()
                .sorted(nullLast)
                .forEach(o -> {
                    System.out.println(o.getAge() + " : " + o.getName() + " : " + o.getClassName());
                });

    }
}
