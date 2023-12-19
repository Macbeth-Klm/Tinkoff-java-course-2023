package edu.hw10.task2.concat;

public class ConcatterClass implements StringConcatter {
    @Override
    public String concat(String... strings) {
        return String.join("", strings);
    }
}
