package edu.hw6.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static edu.hw6.task3.GlobMatchFilter.globMatches;
import static edu.hw6.task3.MagicNumberFilter.magicNumber;
import static edu.hw6.task3.RegexContainsFilter.regexContains;
import static edu.hw6.task3.SizeFilter.largerThan;
import static edu.hw6.task3.SizeFilter.lowerThan;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Path DIRECTORY = Path.of("src/test/java/edu/hw6/task3/");
    private static final Path ITACHI_PATH = Path.of("src/test/java/edu/hw6/task3/itachi-uchiha.png");
    private static final Path FAMILY_PATH = Path.of("src/test/java/edu/hw6/task3/MyFamily.txt");
    private static final Path HELLO_PATH = Path.of("src/test/java/edu/hw6/task3/HelloWorld.txt");

    @Test
    void shouldReturnPathToPngImageInThePackage() {
        DirectoryStream.Filter<Path> filter = AttributeFilter.regular()
            .and(AttributeFilter.readable())
            .and(largerThan(100_000))
            .and(magicNumber((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G'))
            .and(globMatches("*.png"))
            .and(regexContains("[-]"));

        List<String> paths = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(DIRECTORY, filter)) {
            entries.forEach(path -> paths.add(path.toString()));
            assertThat(paths)
                .containsExactly(ITACHI_PATH.toString());
        } catch (IOException io) {
            LOGGER.error("Не удалось найти файл!", io);
            throw new RuntimeException(io);
        }
    }

    @Test
    void shouldReturnMyFamilyTxtFile() {
        DirectoryStream.Filter<Path> filter = AttributeFilter.regular()
            .and(AttributeFilter.writable())
            .and(largerThan(15))
            .and(lowerThan(1000));
        List<String> paths = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(DIRECTORY, filter)) {
            entries.forEach(path -> paths.add(path.toString()));
            assertThat(paths)
                .containsExactly(FAMILY_PATH.toString());
        } catch (IOException io) {
            LOGGER.error("Не удалось найти файл!", io);
            throw new RuntimeException(io);
        }
    }

    @Test
    void shouldReturnAllTxtFiles() {
        DirectoryStream.Filter<Path> filter = AttributeFilter.regular()
            .and(globMatches("*.txt"));
        List<String> paths = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(DIRECTORY, filter)) {
            entries.forEach(path -> paths.add(path.toString()));
            assertThat(paths)
                .containsExactlyInAnyOrder(FAMILY_PATH.toString(), HELLO_PATH.toString());
        } catch (IOException io) {
            LOGGER.error("Не удалось найти файл!", io);
            throw new RuntimeException(io);
        }
    }
}
