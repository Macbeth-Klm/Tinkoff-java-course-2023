package edu.hw6.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Path PATH = Path.of("src\\test\\java\\edu\\hw6\\task4\\test.txt");

    @BeforeAll
    static void createFile() {
        try {
            Files.createFile(PATH);
        } catch (IOException io) {
            LOGGER.error("Не удалось создать файл!", io);
            throw new RuntimeException(io);
        }
    }

    @AfterAll
    static void deleteFile() {
        try {
            Files.delete(PATH);
        } catch (IOException io) {
            LOGGER.error("Не удалось удалить файл!", io);
            throw new RuntimeException(io);
        }
    }

    @Test
    void shouldWriteInTheFile() {
        String message = "Hello World!";
        Task4.write(PATH, message);
        try {
            assertThat(Files.readString(PATH))
                .isEqualTo(message);
        } catch (IOException io) {
            LOGGER.error("Ошибка чтения из файла!", io);
            throw new RuntimeException(io);
        }
    }
}
