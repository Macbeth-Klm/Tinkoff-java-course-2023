package edu.project3.parsers;

import edu.project3.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CommandParserTest {

    @Test
    void shouldParseCorrectString() {
        String command =
            "java -jar nginx-log-stats.jar --path src/test/java/**/project3/resources/?.txt --from 2023-08-15T12:51:10+03 --format markdown";

        Configuration configuration = CommandParser.parse(command);

        /* Файл log_report.txt в данном случае содержит 7913 записей, так как в regex'е
            я делал формат IP-адреса из 4 чисел, а файл 2.txt содержит 10 записей другого формата $remote_addr;
         */
        assertThat(configuration.logs().split("\n").length)
            .isEqualTo(2);
        assertThat(configuration.filesName().toString())
            .isEqualTo("[2.txt]");
        assertThat(configuration.format())
            .isEqualTo("markdown");
        assertThat(configuration.from().toString())
            .isEqualTo("2023-08-15T12:51:10+03:00");
        assertThat(configuration.to())
            .isNull();
    }

    @Test
    void shouldThrowExceptionBecauseOfIncorretFormat() {
        String command =
            "java -jar nginx-log-stats.jar -path src/main/java/**/resources/?.txt";

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> {
            Configuration configuration = CommandParser.parse(command);
        });

        Assertions.assertEquals("Строка неправильного формата, попробуйте снова!", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionBecauseOfIncorretDate() {
        String command =
            "java -jar nginx-log-stats.jar --path src/main/java/**/resources/?.txt --from 2023-15-13T12:51:10+03";

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> {
            Configuration configuration = CommandParser.parse(command);
        });

        Assertions.assertEquals(
            "Дата неправильного формата! Корректный формат yyyy-mm-ddThh:mm:ss±hh",
            ex.getMessage()
        );
    }
}

