package me.whiteship.practice.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<String> nameList = new ArrayList<>();
        nameList.add("min");
        nameList.add("hwi");
        nameList.add("pearl");

        // 중개 오퍼레이션의 지연로딩
        // 종료 오퍼레이션 정의안하면 실행안됨
        nameList.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        });

        // 스트림 파이프라인 완성
        List<String> collect = nameList.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);


        // 스트림 안쓰고 병렬처리하려면 어렵다
        for(String name:nameList) {
            if (name.startsWith("k")) {
                System.out.println(name.toUpperCase());
            }
        }

        // 스트림으로 병렬처리 (멀티쓰레드)
        // 병렬 처리한다고 무조건 빨라지지 않는다!
        // 쓰레드 생성, 수집, 컨텍스트 스위칭 비용이 발생하기 때문
        // 데이터가 정말 방대하게 큰 경우에 유용하다
        List<String> collect1 = nameList.parallelStream()
                .map((s) -> {
                    System.out.println(s + " " +Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .collect(Collectors.toList());
        collect1.forEach(System.out::println);

    }

}
