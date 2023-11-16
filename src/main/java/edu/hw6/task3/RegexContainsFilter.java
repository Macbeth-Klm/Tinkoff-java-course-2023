package edu.hw6.task3;

import java.util.regex.Pattern;

public interface RegexContainsFilter extends AbstractFilter {
    static AbstractFilter regexContains(String regex) {
        return path -> {
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(path.toString()).find();
        };
    }
}
