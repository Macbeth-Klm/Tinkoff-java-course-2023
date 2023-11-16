package edu.hw5.task5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class Task5Test {

    @ParameterizedTest
    @ValueSource(strings = {"А123ВЕ777", "О777ОО177", "У630КА161", "У630КА61", "У630КА01", "У630КА101"})
    void shouldReturnTrueBecauseOfCorrectNumberFormat(String number) {
        var result = Task5.checkCarNumber(number);

        assertThat(result)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"123АВЕ777", "А123ВЕ7777", "А123ВГ77", "А123ВЕ00", "А123ВЕ100", "А123ВЕ001"})
    void shouldReturnFalseBecauseOfIncorrectNumberFormat(String number) {
        var result = Task5.checkCarNumber(number);

        assertThat(result)
            .isFalse();
    }
}
