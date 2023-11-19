package edu.project3.parsers;

import edu.project3.Configuration;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public final class CommandParser {
    /*
    URI - group №3
    LOCAL LOG - group №9
    FROM DATE - group №16
    TO DATE - group №18
    FORMAT - group №20
     */

    private static final Pattern NGINX_PATTERN = Pattern.compile(LogAnalyserRegex.COMMAND_REGEX);

    private CommandParser() {
    }

    @SuppressWarnings("MagicNumber")
    public static Configuration parse(String command) {
        List<String> filesName = new ArrayList<>();
        Matcher matcher = NGINX_PATTERN.matcher(command);
        if (matcher.find()) {
            String logs;
            String uriString = matcher.group(3);
            OffsetDateTime from;
            OffsetDateTime to;
            if (uriString != null) {
                logs = getBodyOfResponse(uriString);
                filesName.add(uriString);
            } else {
                String localString = matcher.group(9);
                try {
                    logs = getBodyFromLocalPath(localString, filesName);
                } catch (IOException e) {
                    throw new RuntimeException("Не удалось получить данные с файла!");
                }
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
                from =
                    (matcher.group(16) == null) ? null : OffsetDateTime.parse(matcher.group(16), formatter);
                to =
                    (matcher.group(18) == null) ? null : OffsetDateTime.parse(matcher.group(18), formatter);
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Дата неправильного формата! Корректный формат yyyy-mm-ddThh:mm:ss±hh");
            }
            String format = (matcher.group(20) == null) ? "" : matcher.group(20);
            return new Configuration(logs, from, to, format, filesName);
        }
        throw new RuntimeException("Строка неправильного формата, попробуйте снова!");
    }

    @SuppressWarnings("MagicNumber")
    private static String getBodyOfResponse(String uriString) {
        var httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(uriString))
            .GET()
            .timeout(Duration.ofSeconds(10L))
            .build();
        try (var response = newHttpClient()) {
            return response.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось получить ответ от сервера!");
        }
    }

    private static String getBodyFromLocalPath(String localString, List<String> filesName) throws IOException {
        var containsAsterisk = localString.contains("*");
        if (containsAsterisk || localString.contains("?")) {
            String symbol = (containsAsterisk) ? "*" : "?";
            int firstAsteriskIndex = localString.indexOf(symbol);
            int lastSlashIndex = localString.lastIndexOf("/", firstAsteriskIndex);
            String dirString = localString.substring(0, lastSlashIndex);
            String glob = localString.substring(lastSlashIndex + 1);
            Path dir = Path.of(dirString);
            StringBuilder sb = new StringBuilder();
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
            Files.walkFileTree(dir, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (pathMatcher.matches(file)) {
                        filesName.add(file.getFileName().toString());
                        sb.append(Files.readString(file));
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
            return sb.toString();
        } else {
            Path path = Path.of(localString);
            return Files.readString(path);
        }
    }
}
