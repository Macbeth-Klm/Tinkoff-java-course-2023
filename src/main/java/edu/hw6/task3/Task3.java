package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.hw6.task3.GlobMatchFilter.globMatches;
import static edu.hw6.task3.MagicNumberFilter.magicNumber;
import static edu.hw6.task3.RegexContainsFilter.regexContains;
import static edu.hw6.task3.SizeFilter.largerThan;

public class Task3 {
    public static void main(String[] args) throws IOException {
        final Path dir = Path.of("src/main/java/edu/hw6/task3");

        final AbstractFilter regularFile = Files::isRegularFile;
        final AbstractFilter readable = Files::isReadable;

        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable)
            .and(largerThan(100_000))
            .and(magicNumber((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G'))
            .and(globMatches("*.png"))
            .and(regexContains("[-]"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(System.out::println);
        }

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            System.out.println();
            System.out.println("Files:");
            entries.forEach(System.out::println);
        }
    }
}
