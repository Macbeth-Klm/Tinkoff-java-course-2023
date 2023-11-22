package edu.project3;

import java.time.OffsetDateTime;

public record LogRecord(String remoteAddr, String remoteUser, OffsetDateTime timeLocal, String request, int status,
                        long bodyBytesSent, String httpReferer, String httpUserAgent) {

    public boolean checkDate(OffsetDateTime from, OffsetDateTime to) {
        return (from == null || !timeLocal.isBefore(from))
            && (to == null || !timeLocal.isAfter(to))
            && (from == null || to == null || (!timeLocal.isBefore(from) && !timeLocal.isAfter(to)));
    }
}
