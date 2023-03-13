package me.whiteship.practice.nm;

public interface Bar {

    // Foo의 default method 무효화
//    void printNameUpperClass();

    default public void printNameUpperClass() {
        System.out.println("me.whiteship.practice.nm.Bar");
    }

}
