package edu.hw5.task7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task7 {
    private Task7() {
    }

    public static boolean thirdSymbolIsZeroAndStringLengthIsNotLessThanThree(String input) {
        Pattern pattern = Pattern.compile("^[01]{2}0[01]*$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean firstAndLastSymbolsAreTheSame(String input) {
        Pattern pattern = Pattern.compile("^([01])[01]*\\1$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean stringLengthIsLessThanFour(String input) {
        Pattern pattern = Pattern.compile("^[01]{1,3}$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
