package me.whiteship.practice.nm;

public class DefaultFoo implements Bar, Foo {

    private String name;
    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println("me.whiteship.practice.nm.DefaultFoo");
    }

    @Override
    public String getName() {
        return this.name;
    }

    // 충돌 났으니 오버라이딩해줘야함
    @Override
    public void printNameUpperClass() {
        Foo.super.printNameUpperClass();
    }

    // 기본 메소드의 동작이 문제가 된다면
    // 기본 메소드 오버라이딩
//    @Override
//    public void printNameUpperClass() {
//        System.out.println(getName().toUpperCase());
//    }
}
