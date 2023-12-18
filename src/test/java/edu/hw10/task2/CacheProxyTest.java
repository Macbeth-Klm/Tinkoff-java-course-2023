package edu.hw10.task2;

import edu.hw10.task2.concat.ConcatterClass;
import edu.hw10.task2.concat.StringConcatter;
import edu.hw10.task2.fib.FibCalculator;
import edu.hw10.task2.fib.FibClass;
import edu.hw10.task2.sum.SumIntegers;
import edu.hw10.task2.sum.SumIntegersClass;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CacheProxyTest {
    @TempDir
    private Path tempDir;

    @Test
    void shouldCorrectCalculateFibAndCreateCacheFile() throws IOException {
        long filesCount;
        try (var pathStream = Files.list(tempDir)) {
            filesCount = pathStream.count();
        }
        FibCalculator c = new FibClass();
        FibCalculator proxy = CacheProxy.create(c, FibCalculator.class, tempDir);

        long result = proxy.fib(10);
        long newFilesCount;
        try (var pathStream = Files.list(tempDir)) {
            newFilesCount = pathStream.count();
        }

        assertAll(
            () -> assertEquals(55, result),
            () -> assertEquals(newFilesCount, filesCount + 1)
        );
    }

    @Test
    void shouldGetFibFromCacheFileWithoutCalculating() throws IOException {
        long filesCount;
        File cacheFile = new File(tempDir.toFile(), "fib[4].txt");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(cacheFile))) {
            out.writeObject(3L);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (var pathStream = Files.list(tempDir)) {
            filesCount = pathStream.count();
        }
        FibCalculator c = new FibClass();
        FibCalculator proxy = CacheProxy.create(c, FibCalculator.class, tempDir);

        long result = proxy.fib(4);
        long newFilesCount;
        try (var pathStream = Files.list(tempDir)) {
            newFilesCount = pathStream.count();
        }

        assertAll(
            () -> assertEquals(3, result),
            () -> assertEquals(filesCount, newFilesCount)
        );
    }

    @Test
    void
    shouldCorrectConcatStringsAndCreateCacheFile() throws IOException {
        long filesCount;
        try (var pathStream = Files.list(tempDir)) {
            filesCount = pathStream.count();
        }
        StringConcatter concatter = new ConcatterClass();
        StringConcatter proxy = CacheProxy.create(concatter, StringConcatter.class, tempDir);
        String result = proxy.concat("Hello", " World!");
        String anotherResult = proxy.concat("Hello", " World!"); // Проверка, что не создаётся ещё один файл
        long newFilesCount;
        try (var pathStream = Files.list(tempDir)) {
            newFilesCount = pathStream.count();
        }

        assertAll(
            () -> assertEquals("Hello World!", result),
            () -> assertEquals(result, anotherResult),
            () -> assertEquals(filesCount + 1, newFilesCount)
        );
    }

    @Test
    void
    shouldReturnCorrectSumAndAddResultInMemoryCacheWithoutCreatingFileBecausePersistIsFalse()
        throws IOException {
        long filesCount;
        try (var pathStream = Files.list(tempDir)) {
            filesCount = pathStream.count();
        }
        SumIntegers sumIntegers = new SumIntegersClass();
        SumIntegers proxy = CacheProxy.create(sumIntegers, SumIntegers.class, tempDir);
        int result = proxy.sum(1, 2, 3, 4);
        long newFilesCount;
        try (var pathStream = Files.list(tempDir)) {
            newFilesCount = pathStream.count();
        }

        assertAll(
            () -> assertEquals(10, result),
            () -> assertEquals(filesCount, newFilesCount)
        );
    }
}
