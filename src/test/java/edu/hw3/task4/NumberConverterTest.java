package edu.hw3.task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NumberConverterTest {
    @Test
    void convertToRomanTestFirst() {
        // given
        int number = 1948;

        // when
        String result = NumberConverter.convertToRoman(number);

        // then
        assertThat(result)
            .isEqualTo("MCMXLVIII");
    }

    @Test
    void convertToRomanTestSecond() {
        // given
        int number = 4;

        // when
        String result = NumberConverter.convertToRoman(number);

        // then
        assertThat(result)
            .isEqualTo("IV");
    }

    @Test
    void convertToRomanTestThird() {
        // given
        int number = 12;

        // when
        String result = NumberConverter.convertToRoman(number);

        // then
        assertThat(result)
            .isEqualTo("XII");
    }

    @Test
    void convertZeroToRomanTest() {
        // given
        int number = 0;

        // when
        String result = NumberConverter.convertToRoman(number);

        // then
        assertThat(result)
            .isEmpty();
    }

    @Test
    void convertMaxValueToRomanTest() {
        // given
        int number = 4000;

        // when
        IllegalArgumentException resultException =
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                String result = NumberConverter.convertToRoman(number);
            });

        // then
        Assertions.assertEquals("Число больше максимального значения!", resultException.getMessage());
    }
}
