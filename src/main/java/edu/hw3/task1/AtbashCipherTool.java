package edu.hw3.task1;

import java.util.HashMap;
import java.util.Map;

public final class AtbashCipherTool {
    private static final Map<Character, Character> ALPHABET = new HashMap<>();
    private static final int BIG_SMALL_LETTERS_DISTANCE = 32;
    private static final int A_Z_DISTANCE = 25;
    private static final int BIG_A_CODE = 65;
    private static final int BIG_N_CODE = 78;

    private AtbashCipherTool() {
    }

    private static void fillMap() {
        int letterDistance = A_Z_DISTANCE;
        for (int i = BIG_A_CODE; i < BIG_N_CODE; i++) {
            char key = (char) i;
            char value = (char) (i + letterDistance);
            ALPHABET.put(key, value);
            ALPHABET.put(value, key);
            key += BIG_SMALL_LETTERS_DISTANCE;
            value = (char) (key + letterDistance);
            ALPHABET.put(key, value);
            ALPHABET.put(value, key);
            letterDistance -= 2;
        }
    }

    public static String atbash(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            throw new IllegalArgumentException("Строка некорректного формата!");
        }
        fillMap();
        char[] inputToChars = inputString.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char el : inputToChars) {
            if (ALPHABET.containsKey(el)) {
                sb.append(ALPHABET.get(el));
            } else {
                sb.append(el);
            }
        }
        return sb.toString();
    }
}
