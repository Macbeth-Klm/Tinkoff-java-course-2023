package edu.hw5.task7;

import edu.hw5.task7.Task7;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class Task7Test {

    @ParameterizedTest
    @ValueSource(strings = {"010", "110", "0100101011100"})
    void shouldReturnTrueFromThirdSymbolIsZeroAndStringLengthIsNotLessThanThreeMethod(String input) {
        var result = Task7.thirdSymbolIsZeroAndStringLengthIsNotLessThanThree(input);

        assertThat(result)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"01", "101", "", "1231", "010acv", "01012334"})
    void shouldReturnFalseFromThirdSymbolIsZeroAndStringLengthIsNotLessThanThreeMethod(String input) {
        var result = Task7.thirdSymbolIsZeroAndStringLengthIsNotLessThanThree(input);

        assertThat(result)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"101", "00", "100011001011", "0000111001010"})
    void shouldReturnTrueFromFirstAndLastSymbolsAreTheSameMethod(String input) {
        var result = Task7.firstAndLastSymbolsAreTheSame(input);

        assertThat(result)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"100", "011", "100101010101010", ""})
    void shouldReturnFalseFromFirstAndLastSymbolsAreTheSameMethod(String input) {
        var result = Task7.firstAndLastSymbolsAreTheSame(input);

        assertThat(result)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "10", "010", "111"})
    void shouldReturnTrueFromStringLengthIsLessThanFourMethod(String input) {
        var result = Task7.stringLengthIsLessThanFour(input);

        assertThat(result)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "010101", "100010101010"})
    void shouldReturnFalseFromStringLengthIsLessThanFourMethod(String input) {
        var result = Task7.stringLengthIsLessThanFour(input);

        assertThat(result)
            .isFalse();
    }
}
