package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    @Test
    void isNestableFirst() {
        // given a1 is nested in the a2
        int[] a1 = new int[] {1, 2, 3, 4};
        int[] a2 = new int[] {0, 6};

        // when
        boolean testResult = Task3.isNestable(a1, a2);

        // then
        assertThat(testResult)
            .isEqualTo(true);
    }

    @Test
    void isNestableSecond() {
        // given a1 is not nested in the a2
        int[] a1 = new int[] {9, 9, 8};
        int[] a2 = new int[] {8, 9};

        // when
        boolean testResult = Task3.isNestable(a1, a2);

        // then
        assertThat(testResult)
            .isEqualTo(false);
    }

    @Test
    void isNestableThird() {
        // given identical arrays
        int[] a1 = new int[] {1, 2, 3, 4};
        int[] a2 = new int[] {1, 2, 3, 4};

        // when
        boolean testResult = Task3.isNestable(a1, a2);

        // then
        assertThat(testResult)
            .isEqualTo(false);
    }
}
