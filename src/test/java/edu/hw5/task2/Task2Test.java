package edu.hw5.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {

    @Test
    void shouldReturnAllFridayThirteenthOfYear() {
        var year = "1925";

        List<LocalDate> result = Task2.getAllFridayThirteenth(year);

        assertThat(result.toString())
            .isEqualTo("[1925-02-13, 1925-03-13, 1925-11-13]");
    }

    @ParameterizedTest
    @ValueSource(strings = {"-2000000000", "1000000000", "twenty twenty four", ""})
    void shouldThrowExceptionBecauseOfIncorrectInputFormatForAllFridaysMethod(String invalidYear) {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            List<LocalDate> result = Task2.getAllFridayThirteenth(invalidYear);
        });
        Assertions.assertEquals(ex.getMessage(), "Входные данные неверного формата!");
    }

    @Test
    void shouldReturnNextFridayThirteenth() {
        var date = LocalDate.of(2026, 4, 1);

        var nextFridayThirteenth = Task2.getNextFridayThirteenth(date);

        assertThat(nextFridayThirteenth.toString())
            .isEqualTo("2026-11-13");
    }
}
