package edu.hw9.task2;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TreeAnalizerTest {
    private final ClassLoader classLoader = getClass().getClassLoader();

    @Test
    void shouldReturnDirectoriesWhichContainsRequiredFilesCount() {
        Path root = new File(Objects.requireNonNull(
            classLoader.getResource("test/hw9/")
        ).getFile()).toPath();

        List<Path> result = TreeAnalizer.findDirectoriesWhichContainsRequiredFilesCount(root, 2);
        List<String> directoriesName =
            result.stream().map(p -> p.getFileName().toString()).toList();

        assertThat(directoriesName)
            .hasSize(4)
            .containsExactlyInAnyOrder("hw9", "directory1", "directory2", "directory4");
    }

    @Test
    void shouldReturnFilesWithRequiredExtension() {
        Path root = new File(Objects.requireNonNull(
            classLoader.getResource("test/hw9/")
        ).getFile()).toPath();

        List<Path> result = TreeAnalizer.findFilesWithRequiredExtension(root, "txt");
        List<String> directoriesName =
            result.stream().map(p -> p.getFileName().toString()).toList();

        assertThat(directoriesName)
            .hasSize(5)
            .containsExactlyInAnyOrder(
                "Hello_World.txt",
                "Mothers_Day.txt",
                "Pushkin.txt",
                "Oleg_Tinkov_quote.txt",
                "file6.txt"
            );
    }

    @Test
    void shouldReturnFilesWithRequiredSize() {
        /*
        Hello_World.txt - 14
        Mothers_Day.txt - 1764
        Pushkin.txt - 497
        Oleg_Tinkov_quote.txt - 367
        file6.txt - 7
        log_report_from_project3.md - 558
        another_log_report_from_project3.adoc - 337
         */
        Path root = new File(Objects.requireNonNull(
            classLoader.getResource("test/hw9/")
        ).getFile()).toPath();

        List<Path> result = TreeAnalizer.findFilesWithRequiredSize(root, 300, 500);
        List<String> directoriesName =
            result.stream().map(p -> p.getFileName().toString()).toList();

        assertThat(directoriesName)
            .hasSize(3)
            .containsExactlyInAnyOrder(
                "Pushkin.txt",
                "Oleg_Tinkov_quote.txt",
                "another_log_report_from_project3.adoc"
            );
    }
}
