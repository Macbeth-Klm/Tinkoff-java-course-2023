package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {
    @Test
    @DisplayName("Сломанная строка")
    void fixString() {
        // given the string of even length
        String inputString = "hTsii  s aimex dpus rtni.g";

        // when
        String resultString = Task4.fixString(inputString);

        // then
        assertThat(resultString)
            .isEqualTo("This is a mixed up string.");

        // given the string of odd length
        inputString = "eHll ooWlr!d: )";

        // when
        resultString = Task4.fixString(inputString);

        // then
        assertThat(resultString)
            .isEqualTo("Hello World! :)");
    }
}
