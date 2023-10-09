package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {
    @Test
    @DisplayName("Вложенный массив")
    void isNestable() {
        // given a1 is nested in the a2
        int[] a1 = new int[] {1, 2, 3, 4};
        int[] a2 = new int[] {0, 6};

        // when
        boolean testResult = Task3.isNestable(a1, a2);

        // then
        assertThat(testResult)
            .isEqualTo(true);

        // given a1 is not nested in the a2
        a1 = new int[] {9, 9, 8};
        a2 = new int[] {8, 9};

        // when
        testResult = Task3.isNestable(a1, a2);

        // then
        assertThat(testResult)
            .isEqualTo(false);

        // given identical arrays
        a1 = new int[] {1, 2, 3, 4};
        a2 = new int[] {1, 2, 3, 4};

        // when
        testResult = Task3.isNestable(a1, a2);

        // then
        assertThat(testResult)
            .isEqualTo(false);
    }
}
