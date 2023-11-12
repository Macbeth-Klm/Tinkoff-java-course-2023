package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public final class Task1 {
    private Task1() {
    }

    @SuppressWarnings("MagicNumber")
    public static String averageTimeInComputerClub(List<String> sessions) {
        List<Duration> durations = new ArrayList<>();
        for (String session : sessions) {
            durations.add(getSessionDuration(session));
        }
        long averageInSeconds = (long) durations.stream()
            .mapToLong(Duration::getSeconds).average().orElse(0);
        long hours = averageInSeconds / 3600;
        long minutes = averageInSeconds % 3600 / 60;
        return hours + "ч " + minutes + "м";
    }

    private static Duration getSessionDuration(String session) {
        var dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        try {
            String[] startAndEnd = session.split(" - ");
            LocalDateTime start = LocalDateTime.parse(startAndEnd[0], dtf);
            LocalDateTime end = LocalDateTime.parse(startAndEnd[1], dtf);
            return Duration.between(start, end);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Входные данные некорректного формата!");
        }
    }
}
