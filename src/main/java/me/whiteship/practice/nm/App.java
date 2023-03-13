package me.whiteship.practice.nm;

public class App {
    public static void main(String[] args) {
        Foo foo = new DefaultFoo("min");
        foo.printName();
        foo.printNameUpperClass();
        Foo.printAnything();
    }
}
