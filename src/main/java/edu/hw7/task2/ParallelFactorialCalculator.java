package edu.hw7.task2;

import java.math.BigInteger;
import java.util.stream.IntStream;

public final class ParallelFactorialCalculator {
    private ParallelFactorialCalculator() {
    }

    public static BigInteger calculateFactorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Невозможно найти факториал отрицательного числа!");
        }
        if (number < 2) {
            return BigInteger.ONE;
        } else {
            return IntStream.rangeClosed(2, number).parallel().mapToObj(BigInteger::valueOf)
                .reduce(BigInteger::multiply).orElse(BigInteger.ZERO);
        }
    }
}
