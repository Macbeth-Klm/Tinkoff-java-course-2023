package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    @Test
    void isPalindromeDescendantFirst() {
        // given number, whose descendant is a palindrome
        int number = 112112301;

        // when
        boolean resultTest = Task5.isPalindromeDescendant(number);

        // then
        assertThat(resultTest)
            .isEqualTo(true);
    }

    @Test
    void isPalindromeDescendantSecond() {
        // given a palindrome
        int number = 363;

        // when
        boolean resultTest = Task5.isPalindromeDescendant(number);

        // then
        assertThat(resultTest)
            .isEqualTo(true);
    }

    @Test
    void isPalindromeDescendantThird() {
        // given a number < 10
        int number = 6;

        // when
        boolean resultTest = Task5.isPalindromeDescendant(number);

        // then
        assertThat(resultTest)
            .isEqualTo(false);
    }

    @Test
    void isPalindromeDescendantFourth() {
        // given number, whose descendants are not a palindrome
        int number = 54442;

        // when
        boolean resultTest = Task5.isPalindromeDescendant(number);

        // then
        assertThat(resultTest)
            .isEqualTo(false);
    }
}
