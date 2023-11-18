package edu.project3;

import java.time.OffsetDateTime;

public record Configuration(String stringOfLogs, OffsetDateTime from, OffsetDateTime to, String format) {
    @Override
    public String toString() {
        return "Logs = " + stringOfLogs + "\nFrom date = " + ((from == null) ? "" : from.toString())
            + "\nTo date = " + ((to == null) ? "" : to.toString()) + "\nFormat = " + format + " ";
    }
}
