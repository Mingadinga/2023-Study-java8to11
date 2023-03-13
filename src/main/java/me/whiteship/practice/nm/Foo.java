package me.whiteship.practice.nm;

public interface Foo {
    void printName(); // public

    String getName();

    // 리스크 있으므로 문서화 잘 해야함

    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한
     */
    default public void printNameUpperClass() {
        System.out.println(getName().toUpperCase());
    }

    // Object가 제공하는 기능 (equals, hasCode)는 기본 메소드로 제공할 수 없다
//    default String toString() {}

    // 추상 메소드
    // Object가 제공하는 기본 기능과 의미가 좀 다를때 추가한다
//    String toString();

    static void printAnything() {
        System.out.println("me.whiteship.practice.nm.Foo");
    }

}
