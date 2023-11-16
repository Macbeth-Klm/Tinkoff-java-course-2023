package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashDateParser extends DateParser {
    private final Pattern pattern = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{1,2})$");

    public DashDateParser(DateParser nextParser) {
        super(nextParser);
    }

    @SuppressWarnings("MagicNumber")
    @Override
    public Optional<LocalDate> parseDate(String stringDate) {
        Matcher matcher = pattern.matcher(stringDate);
        if (matcher.find()) {
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int day = Integer.parseInt(matcher.group(3));
            LocalDate date = LocalDate.of(year, month, day);
            return Optional.of(date);
        }
        return nextIfExist(nextParser, stringDate);
    }
}
