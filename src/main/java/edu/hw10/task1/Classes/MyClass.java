package edu.hw10.task1.Classes;

import edu.hw10.task1.Annotations.Max;
import edu.hw10.task1.Annotations.Min;
import edu.hw10.task1.Annotations.NotNull;

public class MyClass {
    private String name;
    private int age;

    public MyClass() {
    }

    public static MyClass createWithParams(@NotNull String name, @Min(0) @Max(100) Integer age) {
        MyClass instance = new MyClass();
        instance.name = name;
        instance.age = age;
        return instance;
    }

    @SuppressWarnings("MagicNumber")
    public static MyClass createWithoutParams() {
        MyClass instance = new MyClass();
        instance.name = "Ilia";
        instance.age = 21;
        return instance;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
