package edu.hw3.task4;

import java.util.LinkedHashMap;
import java.util.Map;

public final class NumberConverter {
    private static final Map<Integer, String> ARABIC_TO_ROMAN = new LinkedHashMap<>();
    private static final int MAX_VALUE = 3999;
    private static final int ONE_THOUSAND = 1000;
    private static final int FIVE_HUNDRED = 500;
    private static final int ONE_HUNDRED = 100;
    private static final int FIFTY = 50;
    private static final int TEN = 10;
    private static final int NINE = 9;
    private static final int FIVE = 5;
    private static final int FOUR = 4;
    private static final int ONE = 1;

    private NumberConverter() {
    }

    public static String convertToRoman(int inputNumber) {
        if (inputNumber > MAX_VALUE) {
            throw new IllegalArgumentException("Число больше максимального значения!");
        } else if (inputNumber == 0) {
            return "";
        }
        fillMap();
        Integer[] keys = ARABIC_TO_ROMAN.keySet().toArray(new Integer[0]);
        StringBuilder sb = new StringBuilder();
        int ratio = inputNumber;
        for (int i = 0; i < keys.length; i += 2) {
            int remainder = ratio % TEN;
            if (remainder > 0) {
                if (remainder == NINE) {
                    sb.append(ARABIC_TO_ROMAN.get(keys[i + 2]));
                    sb.append(ARABIC_TO_ROMAN.get(keys[i]));
                } else if (remainder < NINE && remainder > FOUR) {
                    for (int j = 0; j < (remainder - FIVE); j++) {
                        sb.append(ARABIC_TO_ROMAN.get(keys[i]));
                    }
                    sb.append(ARABIC_TO_ROMAN.get(keys[i + 1]));
                } else if (remainder == FOUR) {
                    sb.append(ARABIC_TO_ROMAN.get(keys[i + 1]));
                    sb.append(ARABIC_TO_ROMAN.get(keys[i]));
                } else {
                    for (int j = 0; j < remainder; j++) {
                        sb.append(ARABIC_TO_ROMAN.get(keys[i]));
                    }
                }
            }
            ratio /= TEN;
        }
        return sb.reverse().toString();
    }

    private static void fillMap() {
        ARABIC_TO_ROMAN.put(ONE, "I");
        ARABIC_TO_ROMAN.put(FIVE, "V");
        ARABIC_TO_ROMAN.put(TEN, "X");
        ARABIC_TO_ROMAN.put(FIFTY, "L");
        ARABIC_TO_ROMAN.put(ONE_HUNDRED, "C");
        ARABIC_TO_ROMAN.put(FIVE_HUNDRED, "D");
        ARABIC_TO_ROMAN.put(ONE_THOUSAND, "M");
    }
}
