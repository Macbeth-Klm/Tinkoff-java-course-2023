package edu.hw3.task1;

import java.util.HashMap;
import java.util.Map;

public final class AtbashCipherTool {
    private static final Map<Character, Character> ATBASH_CORRESPONDENCE = new HashMap<>();
    private static final int BIG_SMALL_LETTERS_DISTANCE = 32;
    private static final int FIRST_LAST_LETTERS_DISTANCE = 25;
    private static final int BIG_A_CODE = 65;
    private static final int BIG_N_CODE = 78;

    private AtbashCipherTool() {
    }

    private static void fillMap() {
        int firstLastLettersDistance = FIRST_LAST_LETTERS_DISTANCE;
        for (int i = BIG_A_CODE; i < BIG_N_CODE; i++) {
            char key = (char) i;
            char value = (char) (i + firstLastLettersDistance);
            ATBASH_CORRESPONDENCE.put(key, value);
            ATBASH_CORRESPONDENCE.put(value, key);
            key += BIG_SMALL_LETTERS_DISTANCE;
            value = (char) (key + firstLastLettersDistance);
            ATBASH_CORRESPONDENCE.put(key, value);
            ATBASH_CORRESPONDENCE.put(value, key);
            firstLastLettersDistance -= 2;
        }
    }

    public static String atbash(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            throw new IllegalArgumentException("Строка пустая!");
        }
        fillMap();
        char[] inputToChars = inputString.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char el : inputToChars) {
            if (ATBASH_CORRESPONDENCE.containsKey(el)) {
                sb.append(ATBASH_CORRESPONDENCE.get(el));
            } else {
                sb.append(el);
            }
        }
        return sb.toString();
    }
}
