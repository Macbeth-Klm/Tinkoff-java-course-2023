package edu.hw3.task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class NumberConverterTest {
    @Test
    void shouldConvertArabicNumberToRoman() {
        int number = 1948;

        String result = NumberConverter.convertToRoman(number);

        assertThat(result)
            .isEqualTo("MCMXLVIII");
    }

    @Test
    void shouldConvertArabicFiveToRoman() {
        int number = 5;

        String result = NumberConverter.convertToRoman(number);

        assertThat(result)
            .isEqualTo("V");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 4000})
    void shouldThrowBecauseOfMissingNumberInRange(int number) {
        IllegalArgumentException resultException =
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                String result = NumberConverter.convertToRoman(number);
            });

        Assertions.assertEquals("Неконвертируемое число!", resultException.getMessage());
    }
}
