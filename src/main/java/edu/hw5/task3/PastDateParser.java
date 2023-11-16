package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PastDateParser extends DateParser {
    private final Pattern pattern = Pattern.compile("^(\\d{1,9})\\s(day|days)\\sago$");

    public PastDateParser(DateParser nextParser) {
        super(nextParser);
    }

    @Override
    public Optional<LocalDate> parseDate(String stringDate) {
        Matcher matcher = pattern.matcher(stringDate);
        if (matcher.find()) {
            int days = Integer.parseInt(matcher.group(1));
            return Optional.of(LocalDate.now().minusDays(days));
        }
        return nextIfExist(nextParser, stringDate);
    }
}
