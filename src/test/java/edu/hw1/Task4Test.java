package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @Test
    void fixStringFirst() {
        // given the string of even length
        String inputString = "hTsii  s aimex dpus rtni.g";

        // when
        String resultString = Task4.fixString(inputString);

        // then
        assertThat(resultString)
            .isEqualTo("This is a mixed up string.");
    }

    @Test
    void fixStringSecond() {
        // given the string of odd length
        String inputString = "eHll ooWlr!d: )";

        // when
        String resultString = Task4.fixString(inputString);

        // then
        assertThat(resultString)
            .isEqualTo("Hello World! :)");
    }
}
