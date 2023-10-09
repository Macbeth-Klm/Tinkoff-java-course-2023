package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task5Test {
    @Test
    @DisplayName("Особый палиндром")
    void isPalindromeDescendant() {
        // given number, whose descendant is a palindrome
        int number = 112112301;

        // when
        boolean resultTest = Task5.isPalindromeDescendant(number);

        // then
        assertThat(resultTest)
            .isEqualTo(true);

        // given a palindrome
        number = 363;

        // when
        resultTest = Task5.isPalindromeDescendant(number);

        // then
        assertThat(resultTest)
            .isEqualTo(true);

        // given a number < 10
        number = 6;

        // when
        resultTest = Task5.isPalindromeDescendant(number);

        // then
        assertThat(resultTest)
            .isEqualTo(false);

        // given number, whose descendants are not a palindrome
        number = 54442;

        // when
        resultTest = Task5.isPalindromeDescendant(number);

        // then
        assertThat(resultTest)
            .isEqualTo(false);
    }
}
