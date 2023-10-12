package edu.hw1;

import org.junit.jupiter.api.Assertions;
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
            .isTrue();
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
            .isFalse();
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
            .isFalse();
    }

    @Test
    void isNestableFourth() {
        // given empty arrays
        final int[] a1 = new int[0];
        final int[] a2 = new int[] {1, 2, 3, 4};

        // when
        IllegalArgumentException resultException = Assertions.assertThrows(IllegalArgumentException.class, () ->
            Task3.isNestable(a1, a2));

        // then
        Assertions.assertEquals("Один или оба массива пустые!", resultException.getMessage());
    }
}
