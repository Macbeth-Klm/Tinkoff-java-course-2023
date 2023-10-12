package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {
    @Test
    void countKFirst() {
        // given correct number != RESULT_VALUE
        int number = 6621;

        // when
        int count = Task6.countK(number);

        // then
        assertThat(count)
            .isEqualTo(5);
    }

    @Test
    void countKSecond() {
        // given number = RESULT_VALUE
        int number = 6174;

        // when
        int count = Task6.countK(number);

        // then
        assertThat(count)
            .isZero();
    }

    @Test
    void countKThird() {
        // given number < MIN_VALUE
        int number = 483;

        // when
        int count = Task6.countK(number);

        // then
        assertThat(count)
            .isEqualTo(-1);
    }

    @Test
    void countKFourth() {
        // given number > MAX_VALUE
        int number = 10000;

        // when
        int count = Task6.countK(number);

        // then
        assertThat(count)
            .isEqualTo(-1);
    }
}
