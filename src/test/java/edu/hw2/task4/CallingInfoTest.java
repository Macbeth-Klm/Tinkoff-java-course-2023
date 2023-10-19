package edu.hw2.task4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CallingInfoTest {

    @Test
    void callingInfoFirstTest() {
        CallingInfo result = Task4.callingInfo();

        assertThat(result.className())
            .isEqualTo("edu.hw2.task4.CallingInfoTest");
        assertThat(result.methodName())
            .isEqualTo("callingInfoFirstTest");
    }
}
