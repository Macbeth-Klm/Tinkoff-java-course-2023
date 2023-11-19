package edu.project3;

import java.time.OffsetDateTime;

public record LogRecord(String remoteAddr, String remoteUser, OffsetDateTime timeLocal, String request, int status,
                        long bodyBytesSent, String httpReferer, String httpUserAgent) {
}
