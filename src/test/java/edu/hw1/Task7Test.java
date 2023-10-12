package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @Test
    void rotateLeftFirst() {
        // given
        int number = 16;
        int shift = 1;

        // when
        int resultNumber = Task7.rotateLeft(number, shift);

        // then
        assertThat(resultNumber)
            .isEqualTo(1);
    }

    @Test
    void rotateLeftSecond() {
        // given
        int number = 17;
        int shift = 2;

        // when
        int resultNumber = Task7.rotateLeft(number, shift);

        // then
        assertThat(resultNumber)
            .isEqualTo(6);
    }

    @Test
    void rotateRightFirst() {
        // given
        int number = 8;
        int shift = 1;

        // when
        int resultNumber = Task7.rotateRight(number, shift);

        // then
        assertThat(resultNumber)
            .isEqualTo(4);
    }

    @Test
    void rotateRightSecond() {
        // given
        int number = 4;
        int shift = 2;

        // when
        int resultNumber = Task7.rotateRight(number, shift);

        // then
        assertThat(resultNumber)
            .isEqualTo(1);
    }
}
