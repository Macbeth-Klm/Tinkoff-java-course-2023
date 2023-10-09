package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task7Test {
    @Test
    @DisplayName("Циклический битовый сдвиг влево")
    void rotateLeft() {
        // given
        int number = 16;
        int shift = 1;

        // when
        int resultNumber = Task7.rotateLeft(number, shift);

        // then
        assertThat(resultNumber)
            .isEqualTo(1);

        // given
        number = 17;
        shift = 2;

        // when
        resultNumber = Task7.rotateLeft(number, shift);

        // then
        assertThat(resultNumber)
            .isEqualTo(6);
    }

    @Test
    @DisplayName("Циклический битовый сдвиг вправо")
    void rotateRight() {
        // given
        int number = 8;
        int shift = 1;

        // when
        int resultNumber = Task7.rotateRight(number, shift);

        // then
        assertThat(resultNumber)
            .isEqualTo(4);

        // given
        number = 4;
        shift = 2;

        // when
        resultNumber = Task7.rotateRight(number, shift);

        // then
        assertThat(resultNumber)
            .isEqualTo(1);
    }
}
