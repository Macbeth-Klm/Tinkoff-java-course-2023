package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public final class Task3 {
    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        // DashDateParse >> SlashDateParser >> NearestDateParser >> PastDateParser
        DateParser parser = new DashDateParser();
        Optional<LocalDate> result = parser.parseDate(string);
        if (result.isEmpty()) {
            parser = new SlashDateParser();
            result = parser.parseDate(string);
            if (result.isEmpty()) {
                parser = new NearestDateParser();
                result = parser.parseDate(string);
                if (result.isEmpty()) {
                    parser = new PastDateParser();
                    result = parser.parseDate(string);
                }
            }
        }
        return result;
    }
}
