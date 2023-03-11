package me.whiteship.functionalinterface;

public class Foo {
    public static void main(String[] args) {
        // 익명 내부 클래스
//        RunSomething = new RunSomething() {
//            @Override
//            public void doIt() {
//                System.out.println("Hello");
//            }
//        };

        // 람다 표현식
        // 자바는 객체지향언어이므로 람다 표현식을 특수한 형태의 오브젝트로 다룬다
        // 변수에 할당하고 메소드의 파라미터로 전달하거나 리턴할 수 있다 : 고차 함수
        // 동일한 파라미터에 대해 동일한 값을 반환한다. 상태와 사이드 이펙트가 없다. 함수 밖의 값을 사용거나 변경하지 않는다. : 순수 함수
        RunSomething runSomething = (number) -> {
            return number + 10;
        };
        System.out.println(runSomething.doIt(5)); // 15
        System.out.println(runSomething.doIt(5)); // 15
        System.out.println(runSomething.doIt(5)); // 15

    }
}
