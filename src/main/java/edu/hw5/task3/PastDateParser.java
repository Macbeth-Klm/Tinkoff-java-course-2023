package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PastDateParser extends DateParser {
    private final Pattern pattern = Pattern.compile("^\\d{1,9}\\s(day|days)\\sago$");

    public PastDateParser(DateParser nextParser) {
        super(nextParser);
    }

    @Override
    public Optional<LocalDate> parseDate(String stringDate) {
        Matcher matcher = pattern.matcher(stringDate);
        if (matcher.find()) {
            String[] stringDateArray = stringDate.split(" ");
            int days = Integer.parseInt(stringDateArray[0]);
            return Optional.of(LocalDate.now().minusDays(days));
        } else if (nextParser != null) {
            return nextParser.parseDate(stringDate);
        } else {
            return Optional.empty();
        }
    }
}
