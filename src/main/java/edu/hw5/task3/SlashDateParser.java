package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlashDateParser extends DateParser {
    private final Pattern pattern = Pattern.compile("^(\\d{1,2})/(\\d{1,2})/(\\d{2}|\\d{4})$");

    public SlashDateParser(DateParser nextParser) {
        super(nextParser);
    }

    @SuppressWarnings("MagicNumber")
    @Override
    public Optional<LocalDate> parseDate(String stringDate) {
        Matcher matcher = pattern.matcher(stringDate);
        if (matcher.find()) {
            int day = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int year = Integer.parseInt(matcher.group(3));
            if (year < 100) {
                year = (year >= 70) ? 1900 + year : 2000 + year;
            }
            LocalDate date = LocalDate.of(year, month, day);
            return Optional.of(date);
        }
        return nextIfExist(nextParser, stringDate);
    }
}
