package edu.hw5.task8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task8Test {
    @ParameterizedTest
    @ValueSource(strings = {"1", "101", "11010", "0010111"})
    void shouldReturnTrueFromOddLengthMethod(String input) {
        var result = Task8.oddLength(input);

        assertThat(result)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"01", "1101", "101010", "00101101"})
    void shouldReturnFalseFromOddLengthMethod(String input) {
        var result = Task8.oddLength(input);

        assertThat(result)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "10", "010", "1001"})
    void shouldReturnTrueFromFirstZeroAndOddLengthOrFirstOneAndEvenLengthMethod(String input) {
        var result = Task8.firstZeroAndOddLengthOrFirstOneAndEvenLength(input);

        assertThat(result)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "01", "101", "0111", "", "0111abc"})
    void shouldReturnFalseFromFirstZeroAndOddLengthOrFirstOneAndEvenLengthMethod(String input) {
        var result = Task8.firstZeroAndOddLengthOrFirstOneAndEvenLength(input);

        assertThat(result)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"01010", "000", "001110", "101010", "1011001101101110", "1001011001010011110"})
    void shouldReturnTrueFromZerosCountIsMultipleOfThreeMethod(String input) {
        var result = Task8.zerosCountIsMultipleOfThree(input);

        assertThat(result)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "011110", "0101010", "01111000001110101100"})
    void shouldReturnFalseFromZerosCountIsMultipleOfThreeMethod(String input) {
        var result = Task8.zerosCountIsMultipleOfThree(input);

        assertThat(result)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"11010101110101", "11101010010011000", "10101"})
    void shouldReturnTrueFromAnyStringOtherThanThreeOrTwoOnesMethod(String input) {
        var result = Task8.anyStringOtherThanThreeOrTwoOnes(input);

        assertThat(result)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "111"})
    void shouldReturnFalseFromAnyStringOtherThanThreeOrTwoOnesMethod(String input) {
        var result = Task8.anyStringOtherThanThreeOrTwoOnes(input);

        assertThat(result)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1011101", "111010111", "1110", "10", "11"})
    void shouldReturnTrueFromEveryOddSymbolIsOneMethod(String input) {
        var result = Task8.everyOddSymbolIsOne(input);

        assertThat(result)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1001101", "011010111", "0100", "01", "a0"})
    void shouldReturnFalseFromEveryOddSymbolIsOneMethod(String input) {
        var result = Task8.everyOddSymbolIsOne(input);

        assertThat(result)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"001", "100", "010", "0000010", "00", "000000"})
    void shouldReturnTrueFromAtLeastTwoZerosAndNoMoreThanOneOneMethod(String input) {
        var result = Task8.atLeastTwoZerosAndNoMoreThanOneOne(input);

        assertThat(result)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "111000", "01", "110"})
    void shouldReturnFalseFromAtLeastTwoZerosAndNoMoreThanOneOneMethod(String input) {
        var result = Task8.atLeastTwoZerosAndNoMoreThanOneOne(input);

        assertThat(result)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1010000001", "1", "0", "00000100010101"})
    void shouldReturnTrueFromNoSequentialValueOfOne(String input) {
        var result = Task8.noSequentialValueOfOne(input);

        assertThat(result)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "11", "111", "000001100010101"})
    void shouldReturnFalseFromNoSequentialValueOfOne(String input) {
        var result = Task8.noSequentialValueOfOne(input);

        assertThat(result)
            .isFalse();
    }
}
