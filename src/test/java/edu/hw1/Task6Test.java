package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {
    @Test
    @DisplayName("Постоянная Капрекара")
    void countK() {
        // given correct number != 6174
        int number = 6621;

        // when
        int count = Task6.countK(number, 0);

        // then
        assertThat(count)
            .isEqualTo(5);

        // given number = 6174
        number = 6174;

        // when
        count = Task6.countK(number, 0);

        // then
        assertThat(count)
            .isEqualTo(0);

        // given number < 1001
        number = 483;

        // when
        count = Task6.countK(number, 0);

        // then
        assertThat(count)
            .isEqualTo(-1);

        // given number > 9999
        number = 10000;

        // when
        count = Task6.countK(number, 0);

        // then
        assertThat(count)
            .isEqualTo(-1);
    }
}
