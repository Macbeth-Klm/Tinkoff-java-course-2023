package edu.hw7.task2;

import java.math.BigInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelFactorialCalculatorTest {

    @Test
    void shouldCalculateFactorialFirst() {
        int number = 8;

        BigInteger result = ParallelFactorialCalculator.calculateFactorial(number);

        assertEquals(0, result.compareTo(BigInteger.valueOf(40320)));
    }

    @Test
    void shouldCalculateFactorialSecond() {
        int number = 13;

        BigInteger result = ParallelFactorialCalculator.calculateFactorial(number);

        assertEquals(0, result.compareTo(BigInteger.valueOf(6227020800L)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void shouldReturnOne(int number) {
        BigInteger result = ParallelFactorialCalculator.calculateFactorial(number);

        assertEquals(0, result.compareTo(BigInteger.ONE));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -2, -1})
    void shouldThrowExceptionBecauseNumberIsNegative(int number) {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BigInteger result = ParallelFactorialCalculator.calculateFactorial(number);
        });

        Assertions.assertEquals("Невозможно найти факториал отрицательного числа!", ex.getMessage());
    }
}
