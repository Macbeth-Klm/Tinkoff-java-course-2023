package edu.project3.parsers;

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
import edu.project3.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.net.http.HttpClient.newHttpClient;

public final class CommandParser {
    private static final Logger LOGGER = LogManager.getLogger();
    /*
    URI - group №3
    LOCAL LOG - group №9
    FROM DATE - group №16
    TO DATE - group №18
    FORMAT - group №20
     */
    private static final String URI_REGEX =
        "(--path (https?:/+([0-9A-z-_%]+(\\.[0-9a-z]+)?)+\\.[0-9a-z]+/([0-9A-z-_%]+/*)+(\\.[0-9a-z]+)?))";
    private static final String LOCAL_REGEX =
        "(--path (([^<>:\"/\\\\|]+[/\\\\])+([^<>:\"/\\\\|])+((\\.[0-9A-z]+)|(\\*))))";
    private static final String ISO_8601_REGEX =
        "(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}[+-]\\d{2})";
    private static final String NGINX_REGEX =
        "^java -jar nginx-log-stats\\.jar (" + URI_REGEX + "|" + LOCAL_REGEX
            + ")( --from " + ISO_8601_REGEX + ")?"
            + "( --to " + ISO_8601_REGEX + ")?"
            + "( --format (adoc|markdown))?$";

    private static final Pattern NGINX_PATTERN = Pattern.compile(NGINX_REGEX);

    private CommandParser(String command) {
    }

    @SuppressWarnings("MagicNumber")
    public static Configuration parse(String command) {
        Matcher matcher = NGINX_PATTERN.matcher(command);
        if (matcher.find()) {
            String logs;
            String uriString = matcher.group(3);
            OffsetDateTime from;
            OffsetDateTime to;
            if (uriString != null) {
                logs = getBodyOfResponse(uriString);
            } else {
                String localString = matcher.group(9);
                try {
                    logs = getBodyFromLocalPath(localString);
                } catch (IOException e) {
                    LOGGER.error("Не удалось получить данные с файла!", e);
                    throw new RuntimeException(e);
                }
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
                from =
                    (matcher.group(16) == null) ? null : OffsetDateTime.parse(matcher.group(16), formatter);
                to =
                    (matcher.group(18) == null) ? null : OffsetDateTime.parse(matcher.group(18), formatter);
            } catch (DateTimeParseException e) {
                LOGGER.error("Дата неправильного формата! Корректный формат yyyy-mm-ddThh:mm:ss±hh", e);
                throw new RuntimeException(e);
            }
            String format = (matcher.group(20) == null) ? "" : matcher.group(20);
            return new Configuration(logs, from, to, format);
        }
        LOGGER.error("Строка неправильного формата, попробуйте снова!");
        return null;
    }

    @SuppressWarnings("MagicNubmer")
    private static String getBodyOfResponse(String uriString) {
        var httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(uriString))
            .GET()
            .timeout(Duration.ofSeconds(10L))
            .build();
        try (var response = newHttpClient()) {
            return response.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            LOGGER.error("Не удалось получить ответ от сервера!", e);
            throw new RuntimeException(e);
        }
    }

    private static String getBodyFromLocalPath(String localString) throws IOException {
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
