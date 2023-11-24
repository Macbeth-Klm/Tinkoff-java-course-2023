package edu.hw6.task3;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static edu.hw6.task3.GlobMatchFilter.globMatches;
import static edu.hw6.task3.MagicNumberFilter.magicNumber;
import static edu.hw6.task3.RegexContainsFilter.regexContains;
import static edu.hw6.task3.SizeFilter.largerThan;
import static edu.hw6.task3.SizeFilter.lowerThan;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void shouldReturnPathToPngImageInThePackage() {
        ClassLoader classLoader = getClass().getClassLoader();
        Path dir = new File(Objects.requireNonNull(
            classLoader.getResource("test/hw6/task3/")
        ).getFile()).toPath();
        Path itachiPath = new File(Objects.requireNonNull(
            classLoader.getResource("test/hw6/task3/itachi-uchiha.png")
        ).getFile()).toPath();

        DirectoryStream.Filter<Path> filter = AttributeFilter.regular()
            .and(AttributeFilter.readable())
            .and(largerThan(100_000))
            .and(magicNumber((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G'))
            .and(globMatches("*.png"))
            .and(regexContains("[-]"));
        List<String> paths = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(path -> paths.add(path.toString()));
        } catch (IOException io) {
            LOGGER.error("Не удалось найти файл!", io);
            throw new RuntimeException(io);
        }

        assertThat(paths)
            .containsExactly(itachiPath.toString());
    }

    @Test
    void shouldReturnMyFamilyTxtFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        Path dir = new File(Objects.requireNonNull(
            classLoader.getResource("test/hw6/task3/")
        ).getFile()).toPath();
        Path familyPath =
            new File(Objects.requireNonNull(classLoader.getResource(
                "test/hw6/task3/MyFamily.txt")
            ).getFile()).toPath();

        DirectoryStream.Filter<Path> filter = AttributeFilter.regular()
            .and(AttributeFilter.writable())
            .and(largerThan(15))
            .and(lowerThan(1000));
        List<String> paths = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(path -> paths.add(path.toString()));
        } catch (IOException io) {
            LOGGER.error("Не удалось найти файл!", io);
            throw new RuntimeException(io);
        }

        assertThat(paths)
            .containsExactly(familyPath.toString());
    }

    @Test
    void shouldReturnAllTxtFiles() {
        ClassLoader classLoader = getClass().getClassLoader();
        Path dir = new File(Objects.requireNonNull(
            classLoader.getResource("test/hw6/task3/")
        ).getFile()).toPath();
        Path familyPath = new File(Objects.requireNonNull(
            classLoader.getResource("test/hw6/task3/MyFamily.txt")
        ).getFile()).toPath();
        Path helloWorldPath = new File(Objects.requireNonNull(
            classLoader.getResource("test/hw6/task3/HelloWorld.txt")
        ).getFile()).toPath();

        DirectoryStream.Filter<Path> filter = AttributeFilter.regular()
            .and(globMatches("*.txt"));
        List<String> paths = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(path -> paths.add(path.toString()));
        } catch (IOException io) {
            LOGGER.error("Не удалось найти файл!", io);
            throw new RuntimeException(io);
        }

        assertThat(paths)
            .containsExactlyInAnyOrder(familyPath.toString(), helloWorldPath.toString());
    }
}
