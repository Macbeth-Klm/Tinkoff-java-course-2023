package edu.hw5.task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task6 {
    private Task6() {
    }

    public static boolean isSubstringWithRegex(String s, String t) {
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(t);
        return matcher.find();
    }
}
