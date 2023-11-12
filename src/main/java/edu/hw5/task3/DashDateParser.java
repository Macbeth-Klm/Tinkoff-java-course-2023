package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashDateParser extends DateParser {
    private final Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{1,2}$");

    @Override
    public Optional<LocalDate> parseDate(String stringDate) {
        Matcher matcher = pattern.matcher(stringDate);
        if (matcher.find()) {
            String[] yearMonthDays = stringDate.split("-");
            int year = Integer.parseInt(yearMonthDays[0]);
            int month = Integer.parseInt(yearMonthDays[1]);
            int day = Integer.parseInt(yearMonthDays[2]);
            LocalDate date = LocalDate.of(year, month, day);
            return Optional.of(date);
        } else {
            return Optional.empty();
        }
    }
}
