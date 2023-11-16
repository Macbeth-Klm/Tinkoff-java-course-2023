package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class NearestDateParser extends DateParser {
    public NearestDateParser(DateParser nextParser) {
        super(nextParser);
    }

    @Override
    public Optional<LocalDate> parseDate(String stringDate) {
        return switch (stringDate) {
            case "yesterday" -> Optional.of(LocalDate.now().minusDays(1));
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            case "today" -> Optional.of(LocalDate.now());
            default -> nextIfExist(nextParser, stringDate);
        };
    }
}
