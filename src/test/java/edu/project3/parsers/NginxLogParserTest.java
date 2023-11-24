package edu.project3.parsers;

import edu.project3.Configuration;
import edu.project3.LogRecord;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class NginxLogParserTest {

    @Test
    void shouldCorrectParseLogs() {
        Configuration configuration = new Configuration(
            """
                93.180.71.3 - - [17/May/2015:08:05:23 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                80.91.33.133 - - [17/May/2015:08:05:24 +0300] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)"
                """,
            null,
            null,
            "",
            List.of("2.txt")
        );

        List<LogRecord> result = NginxLogParser.parse(configuration);

        Assertions.assertEquals(2, result.size());
        LogRecord first = result.getFirst();
        Assertions.assertEquals("93.180.71.3", first.remoteAddr());
        Assertions.assertEquals("-", first.remoteUser());
        Assertions.assertEquals("2015-05-17T08:05:23Z", first.timeLocal().toString());
        Assertions.assertEquals("GET /downloads/product_1 HTTP/1.1", first.request());
        Assertions.assertEquals(304, first.status());
        Assertions.assertEquals(0, first.bodyBytesSent());
        Assertions.assertEquals("-", first.httpReferer());
        Assertions.assertEquals("Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", first.httpUserAgent());

        LogRecord second = result.get(1);
        Assertions.assertEquals("80.91.33.133", second.remoteAddr());
        Assertions.assertEquals("-", second.remoteUser());
        Assertions.assertEquals("2015-05-17T08:05:24+03:00", second.timeLocal().toString());
        Assertions.assertEquals("GET /downloads/product_1 HTTP/1.1", second.request());
        Assertions.assertEquals(304, second.status());
        Assertions.assertEquals(0, second.bodyBytesSent());
        Assertions.assertEquals("-", second.httpReferer());
        Assertions.assertEquals("Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)", second.httpUserAgent());
    }

    @Test
    void shouldReturnEmptyList() {
        Configuration configuration = new Configuration(
            "",
            null,
            null,
            "markdown",
            List.of("2.txt")
        );

        List<LogRecord> result = NginxLogParser.parse(configuration);

        assertThat(result)
            .isEmpty();
    }
}
