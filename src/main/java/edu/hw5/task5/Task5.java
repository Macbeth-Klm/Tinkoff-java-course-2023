package edu.hw5.task5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task5 {
    private static final Pattern PATTERN = Pattern.compile("^[А-Я]\\d{3}[А-Я]{2}\\d{2,3}$");

    private Task5() {
    }

    public static boolean checkCarNumber(String number) {
        Matcher matcher = PATTERN.matcher(number);
        return matcher.find();
    }
}
