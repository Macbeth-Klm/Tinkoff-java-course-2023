package edu.hw5.task4;

import edu.hw5.task4.Task4;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {

    @ParameterizedTest
    @ValueSource(strings = {
        "passw~rd",
        "password!!",
        "p@ssword",
        "passw#rd#",
        "pa$$word",
        "passw%rd",
        "password^^",
        "password&name",
        "passw***rd",
        "password|name",
        "|p@$$w*rd|% & %|n#m~|!"
    })
    void shouldReturnTrueBecausePasswordIsCorrect(String password) {
        boolean result = Task4.checkPassword(password);

        assertThat(result)
            .isTrue();
    }

    @Test
    void shouldReturnFalseBecauseOfInvalidPasswordFormat() {
        var password = "password";

        boolean result = Task4.checkPassword(password);

        assertThat(result)
            .isFalse();
    }
}
