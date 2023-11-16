package edu.hw6.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FileCloner {
    private static final Logger LOGGER = LogManager.getLogger();

    FileCloner() {
    }

    public static void cloneFile(Path path) {
        if (!Files.exists(path)) {
            LOGGER.info("Данного файла не существует");
            return;
        }
        Pattern pattern = Pattern.compile("\\.\\w+$");
        Matcher matcher = pattern.matcher(path.getFileName().toString());
        String extension = (matcher.find()) ? matcher.group(0) : "";
        if (extension.isEmpty()) {
            throw new IllegalArgumentException("Невозможно скопировать файл без расширения!");
        }
        Path copyingFilePath = Path.of(path.toString().replaceAll(extension, " - копия" + extension));
        if (Files.exists(copyingFilePath)) {
            int i = 2;
            String fileName = copyingFilePath.toString().replaceAll(extension, " (2)" + extension);
            copyingFilePath = Path.of(fileName);
            StringBuilder sb = new StringBuilder(fileName);
            while (Files.exists(copyingFilePath)) {
                sb.replace(sb.length() - extension.length() - 2, sb.length() - extension.length(), i + ")");
                copyingFilePath = Path.of(sb.toString());
                i++;
            }
            try {
                Files.createFile(copyingFilePath);
                Files.copy(path, copyingFilePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException io) {
                LOGGER.error("Не удалось скопировать файл", io);
                throw new RuntimeException(io);
            }
        }
    }
}
