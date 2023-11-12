package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public final class Task3 {
    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        // DashDateParse >> SlashDateParser >> NearestDateParser >> PastDateParser
        DateParser parser1 = new PastDateParser(null);
        DateParser parser2 = new NearestDateParser(parser1);
        DateParser parser3 = new SlashDateParser(parser2);
        DateParser parser4 = new DashDateParser(parser3);
        return parser4.parseDate(string);
    }
}
