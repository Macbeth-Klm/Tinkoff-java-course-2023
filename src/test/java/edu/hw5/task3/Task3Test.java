package edu.hw5.task3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {

    @Test
    void shouldReturnCorrectDateFirst() {
        var date = "2020-10-10";

        var result = Task3.parseDate(date);

        assertThat(result.toString())
            .isEqualTo("Optional[2020-10-10]");
    }

    @Test
    void shouldReturnCorrectDateSecond() {
        var date = "2020-12-2";

        var result = Task3.parseDate(date);

        assertThat(result.toString())
            .isEqualTo("Optional[2020-12-02]");
    }

    @Test
    void shouldReturnCorrectDateThird() {
        var date = "1/3/1976";

        var result = Task3.parseDate(date);

        assertThat(result.toString())
            .isEqualTo("Optional[1976-03-01]");
    }

    @Test
    void shouldReturnCorrectDateFourth() {
        var date = "1/3/20";

        var result = Task3.parseDate(date);

        assertThat(result.toString())
            .isEqualTo("Optional[2020-03-01]");
    }

    @Test
    void shouldReturnCorrectDateFifth() {
        var date = "tomorrow";

        var result = Task3.parseDate(date);

        assertThat(result.toString())
            .isEqualTo("Optional[" + LocalDate.now().plusDays(1) + "]");
    }

    @Test
    void shouldReturnCorrectDateSixth() {
        var date = "today";

        var result = Task3.parseDate(date);

        assertThat(result.toString())
            .isEqualTo("Optional[" + LocalDate.now() + "]");
    }

    @Test
    void shouldReturnCorrectDateSeventh() {
        var date = "yesterday";

        var result = Task3.parseDate(date);

        assertThat(result.toString())
            .isEqualTo("Optional[" + LocalDate.now().minusDays(1) + "]");
    }

    @Test
    void shouldReturnCorrectDateEighth() {
        var date = "1 day ago";

        var result = Task3.parseDate(date);

        assertThat(result.toString())
            .isEqualTo("Optional[" + LocalDate.now().minusDays(1) + "]");
    }

    @Test
    void shouldReturnCorrectDateNinth() {
        var date = "2234 days ago";

        var result = Task3.parseDate(date);

        assertThat(result.toString())
            .isEqualTo("Optional[2017-10-01]");
    }

    @ParameterizedTest
    @ValueSource(strings = {"20 November 2020", "", "The day before yesterday", "The day after today", "111//3/20"})
    void shouldReturnEmptyOptionalBecauseOfIncorrectFormat(String date) {
        var result = Task3.parseDate(date);

        assertThat(result)
            .isEmpty();
    }
}
