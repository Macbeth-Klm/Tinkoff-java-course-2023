package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    void countDigitsFirst() {
        // given zero
        int number = 0;

        // when
        int digitsCount = Task2.countDigits(number);

        // then
        assertThat(digitsCount)
            .isEqualTo(1);
    }

    @Test
    void countDigitsSecond() {
        // given a positive number < 10
        int number = 5;

        // when
        int digitsCount = Task2.countDigits(number);

        // then
        assertThat(digitsCount)
            .isEqualTo(1);
    }

    @Test
    void countDigitsThird() {
        // given a positive number > 10
        int number = 4666;

        // when
        int digitsCount = Task2.countDigits(number);

        // then
        assertThat(digitsCount)
            .isEqualTo(4);
    }

    @Test
    void countDigitsFourth() {
        // given a negative number > -10
        int number = -9;

        // when
        int digitsCount = Task2.countDigits(number);

        // then
        assertThat(digitsCount)
            .isEqualTo(1);
    }

    @Test
    void countDigitsFifth() {
        // given a negative number < -10
        int number = -13894;

        // when
        int digitsCount = Task2.countDigits(number);

        // then
        assertThat(digitsCount)
            .isEqualTo(5);
    }
}
