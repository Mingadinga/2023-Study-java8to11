package me.whiteship.practice.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalAPIPractice {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));

        // Optional 생성
        Optional<String> e1 = Optional.of(null); // 이렇게 쓰면 안됩니다
        Optional<String> s1 = Optional.of("string");

        Optional<String> s2 = Optional.ofNullable("string");
        Optional<String> e2 = Optional.ofNullable(null);

        Optional<String> e3 = Optional.empty();

        // Optional을 반환하는 종결 스트림 연산
        // 당연함. spring으로 시작하는 class가 없을수도 있고 있을수도 있음
        Optional<OnlineClass> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        // 빈값인지 확인하기
        boolean present1 = spring.isPresent();
        boolean present2 = spring.isEmpty();

        // 값 꺼내기 - 빈값이 아닌게 확실한 경우
        // Optional은 빈값을 포함할 수 있음을 나타내는 타입이므로 이렇게 쓰면 안된다
        OnlineClass onlineClass = spring.get();
        System.out.println(onlineClass.getTitle());

        // 값 꺼내기 - 빈값을 포함할 수 있고, Optional을 직접 참조하는 경우
        // Optional이 빈값이 아니라면 람다식을 실행해줘!
        Optional<OnlineClass> empty = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("empty"))
                .findFirst();
        empty.ifPresent(oc -> System.out.println(oc.getTitle()));

        // 값 꺼내기 - 빈값을 포함할 수 있고, Optional을 변수로 받아서 처리해야 하는 경우
        // 값이 있으면 꺼내오고 없으면 새로운 클래스를 만들어줘 -> 항상 OnlineClass가 들어있음
        // 하지만 이 방법은 값이 있는 경우에도 orElse 안의 메소드가 무조건 실행됨
        OnlineClass onlineClass1 = empty.orElse(createNewClass());
        System.out.println(onlineClass1.getTitle());

        // orElseGet을 쓰면 값이 없는 경우에만 Supplier 실행
        OnlineClass onlineClass2 = empty.orElseGet(OptionalAPIPractice::createNewClass);
        System.out.println(onlineClass2.getTitle());

        // 빈값이면 에러를 던진다
        // 빈값이라면 정상적인 상황이 아니라고 정한 경우 예외를 던지기 위해 사용함
        // 얘가 빈값이면 대안이 없다. 복구해줄 방법이 없다.
//        empty.orElseThrow(IllegalStateException::new);

        // Optional에 들어있는 값 걸러내기
        // 값이 있다는 가정 하에 동작한다
        // 값이 비어있으면 아무 동작도 하지 않는다
        Optional<OnlineClass> onlineClass3 = spring.filter(OnlineClass::isClosed);
        System.out.println(onlineClass3.isEmpty());

        Optional<Integer> integer = spring.map(OnlineClass::getId);
        System.out.println(integer.isPresent());

        // map으로 변환하는 타입이 Optional이라면
        // 하나씩 까서 null 검사하면 번거로움
        Optional<Optional<Progress>> progress = spring.map(OnlineClass::getProgress);
        Optional<Progress> progress1 = progress.orElseThrow();
        Progress progress2 = progress1.orElseThrow();
        System.out.println(progress2.getStudyDuration());

        System.out.println("======");

        // Optional의 flatMap 사용
        // Optional의 flatMap은 변환 타겟 타입이 Optional인 경우 껍질을 한번 까서 준다

        Optional<Progress> progress3 = spring.flatMap(OnlineClass::getProgress);
        progress3.ifPresent(p -> System.out.println(p.getStudyDuration()));

        Optional<Progress> progress4 = empty.flatMap(OnlineClass::getProgress);
        progress4.ifPresent(p -> System.out.println(p.getStudyDuration()));

    }

    private static OnlineClass createNewClass() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "New Class", false);
    }
}
