package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParser {
    protected DateParser nextParser;

    DateParser(DateParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract Optional<LocalDate> parseDate(String stringDate);

    protected Optional<LocalDate> nextIfExist(DateParser parser, String date) {
        if (parser != null) {
            return parser.parseDate(date);
        } else {
            return Optional.empty();
        }
    }
}
