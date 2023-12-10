package edu.hw10.task2.fib;

public class FibClass implements FibCalculator {
    @Override
    public long fib(int number) {
        if (number <= 0) {
            return 0;
        }
        if (number == 1) {
            return 1;
        }
        return fib(number - 2) + fib(number - 1);
    }
}
