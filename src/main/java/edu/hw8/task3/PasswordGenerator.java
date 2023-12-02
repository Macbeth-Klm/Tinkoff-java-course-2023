package edu.hw8.task3;

import java.util.NoSuchElementException;

public class PasswordGenerator {
    private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private final int[] lettersPositions;
    private long passwordsCount;
    private final int passwordLength;

    public PasswordGenerator(int passwordLength) {
        this.passwordLength = passwordLength;
        lettersPositions = new int[passwordLength];
        passwordsCount = (long) Math.pow(ALPHABET.length(), passwordLength);
    }

    public PasswordGenerator(int passwordLength, int fromIndex, long passwordsCount) {
        this.passwordLength = passwordLength;
        lettersPositions = new int[passwordLength];
        int remainder = fromIndex;
        for (int i = 0; i < passwordLength; i++) {
            int divider = (int) Math.pow(ALPHABET.length(), passwordLength - i - 1);
            lettersPositions[i] = remainder / divider;
            remainder = remainder % divider;
        }
        this.passwordsCount = passwordsCount;
    }

    public String generate() {
        if (!hasNextPassword()) {
            throw new NoSuchElementException("No passwords");
        }
        var password = new StringBuilder(passwordLength);
        for (int position : lettersPositions) {
            password.append(ALPHABET.charAt(position));
        }

        passwordsCount--;
        for (int i = lettersPositions.length - 1; i >= 0; --i) {
            lettersPositions[i]++;

            if (lettersPositions[i] == ALPHABET.length()) {
                lettersPositions[i] = 0;
            } else {
                break;
            }
        }
        return password.toString();
    }

    public boolean hasNextPassword() {
        return passwordsCount > 0;
    }
}
