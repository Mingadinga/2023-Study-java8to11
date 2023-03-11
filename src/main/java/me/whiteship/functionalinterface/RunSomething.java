package me.whiteship.functionalinterface;

// 함수형 인터페이스
// 추상 메소드가 딱 하나 존재한다
// 추상 메소드 외에 다른 형태의 메소드를 정의할 수 있다
// 자바가 제공하는 애노테이션을 추가하면 추상 메소드를 두개 이상 선언했을 때 컴파일 오류가 난다
@FunctionalInterface
public interface RunSomething {
    // 추상 메소드가 하나 있는게 함수형 인터페이스
    int doIt(int number);

    // 함수형 인터페이스 안에 static 메소드를 정의할 수 있다
    static void printName(){
        System.out.println("Hwi");
    }

    // 함수형 인터페이스 안에 default 메소드를 정의할 수 있다
    default void printAge() {
        System.out.println("24");
    }
}
