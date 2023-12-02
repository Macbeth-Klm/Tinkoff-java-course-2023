package edu.hw8.task2;

public final class FibonacciNumbers {

    private FibonacciNumbers() {
    }

    public static int calculate(int number) {
        if (number <= 0) {
            return 0;
        }
        if (number == 1) {
            return 1;
        }
        return calculate(number - 2) + calculate(number - 1);
    }
}
