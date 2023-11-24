package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FileCloner {
    private static final Logger LOGGER = LogManager.getLogger();

    private FileCloner() {
    }

    public static void cloneFile(Path path) {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Исходного файла не существует!");
        }
        Pattern pattern = Pattern.compile("\\.\\w+$");
        Matcher matcher = pattern.matcher(path.getFileName().toString());
        String extension = (matcher.find()) ? matcher.group(0) : "";
        if (extension.isEmpty()) {
            throw new IllegalArgumentException("Невозможно скопировать файл без расширения!");
        }
        Path copyingFilePath = Path.of(path.toString().replaceAll(extension, " - копия" + extension));
        String copyingFileName = copyingFilePath.toString().replaceAll(extension, "");
        if (Files.exists(copyingFilePath)) {
            int i = 2;
            do {
                copyingFilePath = Path.of(copyingFileName + " (" + i + ")" + extension);
                i++;
            } while (Files.exists(copyingFilePath));
        }
        try {
            Files.copy(path, copyingFilePath);
        } catch (IOException e) {
            LOGGER.error("Не удалось скопировать файл", e);
            throw new RuntimeException(e);
        }
    }
}
