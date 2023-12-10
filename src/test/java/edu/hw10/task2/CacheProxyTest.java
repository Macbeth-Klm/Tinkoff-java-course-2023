package edu.hw10.task2;

import edu.hw10.task2.concat.ConcatterClass;
import edu.hw10.task2.concat.StringConcatter;
import edu.hw10.task2.fib.FibCalculator;
import edu.hw10.task2.fib.FibClass;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CacheProxyTest {
    private static final Path DIRECTORY = Path.of("src/main/java/edu/hw10/task2/cache");

    @BeforeAll
    static void createDirectoryAndAddFile() throws IOException {
        Files.createDirectory(DIRECTORY);
        File cacheFile = new File("src/main/java/edu/hw10/task2/cache/fib[4].txt");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(cacheFile))) {
            out.writeObject(3L);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    static void deleteDirectory() throws IOException {
        try (var files = Files.list(DIRECTORY)) {
            files.forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        Files.deleteIfExists(DIRECTORY);
    }

    @Test
    void shouldCorrectCalculateFibAndCreateCacheFile() throws IOException {
        long filesCount;
        try (var pathStream = Files.list(DIRECTORY)) {
            filesCount = pathStream.count();
        }
        FibCalculator c = new FibClass();
        FibCalculator proxy = CacheProxy.create(c, FibCalculator.class);

        long result = proxy.fib(10);
        long newFilesCount;
        try (var pathStream = Files.list(DIRECTORY)) {
            newFilesCount = pathStream.count();
        }

        assertTrue(result == 55 && newFilesCount == filesCount + 1);
    }

    @Test
    void shouldGetFibFromCacheFileWithoutCalculating() throws IOException {
        long filesCount;
        try (var pathStream = Files.list(DIRECTORY)) {
            filesCount = pathStream.count();
        }
        FibCalculator c = new FibClass();
        FibCalculator proxy = CacheProxy.create(c, FibCalculator.class);

        long result = proxy.fib(4);
        long newFilesCount;
        try (var pathStream = Files.list(DIRECTORY)) {
            newFilesCount = pathStream.count();
        }

        assertTrue(result == 3 && newFilesCount == filesCount);
    }

    @Test
    void
    shouldCorrectConcatStringsAndCreateCacheFile() throws IOException {
        long filesCount;
        try (var pathStream = Files.list(DIRECTORY)) {
            filesCount = pathStream.count();
        }
        StringConcatter concatter = new ConcatterClass();
        StringConcatter proxy = CacheProxy.create(concatter, StringConcatter.class);

        String result = proxy.concat("My name is", " Ilia");
        long newFilesCount;
        try (var pathStream = Files.list(DIRECTORY)) {
            newFilesCount = pathStream.count();
        }

        assertTrue(result.equals("My name is Ilia") && newFilesCount == filesCount + 1);
    }
}
