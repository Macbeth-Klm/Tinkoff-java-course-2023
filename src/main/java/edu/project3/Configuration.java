package edu.project3;

import java.time.OffsetDateTime;
import java.util.List;

public record Configuration(String logs, OffsetDateTime from, OffsetDateTime to, String format,
                            List<String> filesName) {
}
