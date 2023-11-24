package edu.project3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LogAnalyserTest {

    @Test
    void analiseLogsWithAdoc() {
        String command =
            "java -jar nginx-log-stats.jar --path src/main/java/**/project3/resources/*.txt --format adoc";

        LogAnalyser.analiseLogs(command);
        Path path = Path.of("src/main/java/edu/project3/results/log_report.adoc");

        try {
            assertThat(Files.exists(path))
                .isTrue();
            assertThat(Files.size(path))
                .isEqualTo(6532);
        } catch (IOException ex) {
            throw new RuntimeException("Ошибка чтения adoc-файла в тесте!");
        }
    }

    @Test
    void analiseLogsWithMarkDown() {
        String command = "java -jar nginx-log-stats.jar --path " +
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs" +
            " --format markdown";

        LogAnalyser.analiseLogs(command);
        Path path = Path.of("src/main/java/edu/project3/results/log_report.md");

        try {
            assertThat(Files.exists(path))
                .isTrue();
            assertThat(Files.size(path))
                .isEqualTo(8251);
        } catch (IOException ex) {
            throw new RuntimeException("Ошибка чтения md-файла в тесте!");
        }
    }

    @Test
    void analiseLogsWithDefaultFormat() {
        String command =
            "java -jar nginx-log-stats.jar --path src/main/java/**/project3/resources/?.txt";

        LogAnalyser.analiseLogs(command);
        Path path = Path.of("src/main/java/edu/project3/results/log_report.txt");

        try {
            assertThat(Files.exists(path))
                .isTrue();
            assertThat(Files.size(path))
                .isEqualTo(13670);
        } catch (IOException ex) {
            throw new RuntimeException("Ошибка чтения md-файла в тесте!");
        }
    }
}
