package edu.project3.parsers;

import edu.project3.Configuration;
import edu.project3.LogRecord;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class NginxLogParser {
    private NginxLogParser() {
    }

    @SuppressWarnings("MagicNumber")
    public static List<LogRecord> parse(Configuration configuration) {
        /*
        remoteAddr = group(1)
        remoteUser = group(3)
        timeLocal = group(4)
        request = group(6)
        status = group(10)
        bodyBytesSent = group(11)
        httpReferer = group(12)
        httpUserAgent = group(19)
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        return configuration.logs().lines()
            .filter(s -> Pattern.compile(LogAnalyserRegex.NGINX_LOG_REGEX).matcher(s).find())
            .map(s -> {
                Matcher m = Pattern.compile(LogAnalyserRegex.NGINX_LOG_REGEX).matcher(s);
                if (m.find()) {
                    var timeLocal = OffsetDateTime.parse(m.group(4), formatter);
                    var from = configuration.from();
                    var to = configuration.to();
                    if (from != null && timeLocal.isBefore(from)
                        || to != null && timeLocal.isAfter(to)
                        || from != null && to != null && (timeLocal.isBefore(from) || timeLocal.isAfter(to))) {
                        return null;
                    }
                    var remoteAddr = m.group(1);
                    var remoteUser = m.group(3);
                    var request = m.group(6);
                    var status = Integer.parseInt(m.group(10));
                    var bodyBytesSent = Long.parseLong(m.group(11));
                    var httpReferer = m.group(12);
                    var httpUserAgent = m.group(18);
                    return new LogRecord(remoteAddr, remoteUser, timeLocal, request, status,
                        bodyBytesSent, httpReferer, httpUserAgent
                    );
                }
                return null;
            }).filter(Objects::nonNull).toList();
    }
}
