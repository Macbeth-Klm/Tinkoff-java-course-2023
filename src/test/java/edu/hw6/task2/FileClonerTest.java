package edu.hw6.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

class FileClonerTest {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Path SOURCE_FILE_PATH = Path.of("src\\test\\java\\edu\\hw6\\task2\\test.txt");
    private static final Path FIRST_COPY_PATH = Path.of("src\\test\\java\\edu\\hw6\\task2\\test - копия.txt");
    private static final Path SECOND_COPY_PATH = Path.of("src\\test\\java\\edu\\hw6\\task2\\test - копия (2).txt");

    @BeforeAll
    static void createSourceFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(SOURCE_FILE_PATH, StandardCharsets.UTF_8)) {
            writer.write("Hello world!");
        } catch (IOException io) {
            LOGGER.error("Не удалось создать исходный файл");
            throw new RuntimeException(io);
        }
    }

    @AfterAll
    static void deleteSourceFile() {
        try {
            Files.delete(SOURCE_FILE_PATH);
        } catch (IOException io) {
            LOGGER.error("Не удалось удалить исходный файл");
            throw new RuntimeException(io);
        }
    }

    @AfterEach
    void deleteCopies() {
        try {
            if (Files.exists(FIRST_COPY_PATH)) {
                Files.delete(FIRST_COPY_PATH);
            }
            if (Files.exists(SECOND_COPY_PATH)) {
                Files.delete(SECOND_COPY_PATH);
            }
        } catch (IOException io) {
            LOGGER.error("Не удалось удалить копии файлов!");
            throw new RuntimeException(io);
        }
    }

    @Test
    void shouldMakeFirstCopy() {
        try {
            FileCloner.cloneFile(SOURCE_FILE_PATH);

            assertThat(FIRST_COPY_PATH)
                .exists();
            assertThat(Files.readAllBytes(FIRST_COPY_PATH))
                .isEqualTo(Files.readAllBytes(SOURCE_FILE_PATH));
            Files.delete(FIRST_COPY_PATH);
        } catch (IOException io) {
            LOGGER.error("Не удалось прочитать файл!", io);
            throw new RuntimeException(io);
        }
    }

    @Test
    void shouldMakeTwoCopies() {
        try {
            FileCloner.cloneFile(SOURCE_FILE_PATH); // Создаём первую копию

            FileCloner.cloneFile(SOURCE_FILE_PATH);

            assertThat(SECOND_COPY_PATH)
                .exists();
            assertThat(Files.readAllBytes(SECOND_COPY_PATH))
                .isEqualTo(Files.readAllBytes(SOURCE_FILE_PATH));
        } catch (IOException io) {
            LOGGER.error("Не удалось прочитать файл!", io);
            throw new RuntimeException(io);
        }
    }
}
