package edu.hw5.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    void shouldReturnCorrectAverageTimeFirst() {
        var date1 = "2022-03-12, 20:20 - 2022-03-12, 23:50";
        var date2 = "2022-04-01, 21:30 - 2022-04-02, 01:20";
        List<String> sessions = List.of(date1, date2);

        String result = Task1.averageTimeInComputerClub(sessions);

        assertThat(result)
            .isEqualTo("3ч 40м");
    }

    @Test
    void shouldReturnCorrectAverageTimeSecond() {
        var date1 = "2022-03-12, 20:20 - 2022-03-12, 23:50"; // 3:30
        var date2 = "2022-04-01, 21:30 - 2022-04-02, 01:20"; // 3:50
        var date3 = "2022-04-01, 21:30 - 2022-04-03, 01:20"; // 27:50
        List<String> sessions = List.of(date1, date2, date3);

        String result = Task1.averageTimeInComputerClub(sessions);

        assertThat(result)
            .isEqualTo("11ч 43м");
    }

    @Test
    void shouldThrowExceptionBecauseOfIncorrectInputFormat() {
        var date1 = "2022-03-12, 20:61 - 2022-03-12, 23:50";
        var date2 = "2022-04-01, 21:30 - 2022-04-02, 01:20";
        var date3 = "2022-04-01, 21:30 - 2022-04-03, 01:20";
        List<String> sessions = List.of(date1, date2, date3);

        IllegalArgumentException ex =
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                String result = Task1.averageTimeInComputerClub(sessions);
            });

        Assertions.assertEquals("Входные данные некорректного формата!", ex.getMessage());
    }
}
