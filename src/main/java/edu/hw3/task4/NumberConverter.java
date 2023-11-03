package edu.hw3.task4;

import java.util.LinkedHashMap;
import java.util.Map;

public final class NumberConverter {
    private static final Map<Integer, String> ARABIC_TO_ROMAN_CORRESPONDENCE = new LinkedHashMap<>();
    private static final int MAX_VALUE = 3999;

    private NumberConverter() {
    }

    @SuppressWarnings("MagicNumber")
    public static String convertToRoman(int inputNumber) {
        if (inputNumber > MAX_VALUE || inputNumber < 1) {
            throw new IllegalArgumentException("Неконвертируемое число!");
        }
        fillMap();
        Integer[] keys = ARABIC_TO_ROMAN_CORRESPONDENCE.keySet().toArray(new Integer[0]);
        StringBuilder sb = new StringBuilder();
        int ratio = inputNumber;
        for (int i = 0; i < keys.length; i += 2) {
            int remainder = ratio % 10;
            if (remainder > 0) {
                if (remainder == 9) {
                    sb.append(ARABIC_TO_ROMAN_CORRESPONDENCE.get(keys[i + 2]));
                    sb.append(ARABIC_TO_ROMAN_CORRESPONDENCE.get(keys[i]));
                } else if (remainder > 4) {
                    for (int j = 0; j < (remainder - 5); j++) {
                        sb.append(ARABIC_TO_ROMAN_CORRESPONDENCE.get(keys[i]));
                    }
                    sb.append(ARABIC_TO_ROMAN_CORRESPONDENCE.get(keys[i + 1]));
                } else if (remainder == 4) {
                    sb.append(ARABIC_TO_ROMAN_CORRESPONDENCE.get(keys[i + 1]));
                    sb.append(ARABIC_TO_ROMAN_CORRESPONDENCE.get(keys[i]));
                } else {
                    for (int j = 0; j < remainder; j++) {
                        sb.append(ARABIC_TO_ROMAN_CORRESPONDENCE.get(keys[i]));
                    }
                }
            }
            ratio /= 10;
        }
        return sb.reverse().toString();
    }

    @SuppressWarnings("MagicNumber")
    private static void fillMap() {
        ARABIC_TO_ROMAN_CORRESPONDENCE.put(1, "I");
        ARABIC_TO_ROMAN_CORRESPONDENCE.put(5, "V");
        ARABIC_TO_ROMAN_CORRESPONDENCE.put(10, "X");
        ARABIC_TO_ROMAN_CORRESPONDENCE.put(50, "L");
        ARABIC_TO_ROMAN_CORRESPONDENCE.put(100, "C");
        ARABIC_TO_ROMAN_CORRESPONDENCE.put(500, "D");
        ARABIC_TO_ROMAN_CORRESPONDENCE.put(1000, "M");
    }
}
