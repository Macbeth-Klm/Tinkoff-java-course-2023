package edu.hw5.task4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task4 {
    private static final Pattern PATTERN = Pattern.compile("[~!@#$%^&*|]+");

    private Task4() {
    }

    public static boolean checkPassword(String password) {
        Matcher matcher = PATTERN.matcher(password);
        return matcher.find();
    }
}
