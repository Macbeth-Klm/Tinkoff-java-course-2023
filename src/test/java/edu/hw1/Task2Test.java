package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {
    @Test
    @DisplayName("Количество цифр")
    void countDigits() {
        // given zero
        int number = 0;

        // when
        int digitsCount = Task2.countDigits(number);

        // then
        assertThat(digitsCount)
            .isEqualTo(1);

        // given a positive number < 10
        number = 5;

        // when
        digitsCount = Task2.countDigits(number);

        // then
        assertThat(digitsCount)
            .isEqualTo(1);

        // given a positive number > 10
        number = 4666;

        // when
        digitsCount = Task2.countDigits(number);

        // then
        assertThat(digitsCount)
            .isEqualTo(4);

        // given a negative number < 10
        number = -9;

        // when
        digitsCount = Task2.countDigits(number);

        // then
        assertThat(digitsCount)
            .isEqualTo(1);

        // given a negative number < -10
        number = -13894;

        // when
        digitsCount = Task2.countDigits(number);

        // then
        assertThat(digitsCount)
            .isEqualTo(5);
    }
}
