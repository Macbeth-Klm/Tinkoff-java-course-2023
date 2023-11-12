package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParser {
    public DateParser nextParser;

    DateParser(DateParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract Optional<LocalDate> parseDate(String stringDate);
}
