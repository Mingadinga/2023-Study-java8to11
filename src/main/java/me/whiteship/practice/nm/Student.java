package me.whiteship.practice.nm;

public class Student {
    int age;
    String name;
    ClassName className;

    public enum ClassName {
        A, B, C
    }

    Student(int age, String name, ClassName className) {
        this.age = age;
        this.name = name;
        this.className = className;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public ClassName getClassName() {
        return className;
    }
}
