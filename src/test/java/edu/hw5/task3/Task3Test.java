package edu.hw5.task3;

import org.junit.jupiter.api.Test;

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
            .isEqualTo("Optional[2023-11-14]");
    }

    @Test
    void shouldReturnCorrectDateSixth() {
        var date = "today";

        var result = Task3.parseDate(date);

        assertThat(result.toString())
            .isEqualTo("Optional[2023-11-13]");
    }

    @Test
    void shouldReturnCorrectDateSeventh() {
        var date = "yesterday";

        var result = Task3.parseDate(date);

        assertThat(result.toString())
            .isEqualTo("Optional[2023-11-12]");
    }

    @Test
    void shouldReturnCorrectDateEighth() {
        var date = "1 day ago";

        var result = Task3.parseDate(date);

        assertThat(result.toString())
            .isEqualTo("Optional[2023-11-12]");
    }

    @Test
    void shouldReturnCorrectDateNinth() {
        var date = "2234 days ago";

        var result = Task3.parseDate(date);

        assertThat(result.toString())
            .isEqualTo("Optional[2017-10-01]");
    }

    @Test
    void shouldReturnEmptyOptionalBecauseOfIncorrectFormat() {
        var date = "The day before yesterday";

        var result = Task3.parseDate(date);

        assertThat(result)
            .isEmpty();
    }
}
