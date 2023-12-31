package edu.hw1;

public final class Task5 {
    private static final int MIN_VALUE = 10;

    private Task5() {
    }

    public static boolean isPalindromeDescendant(int input) {
        if (input < MIN_VALUE) {
            return false;
        }
        StringBuilder sb = new StringBuilder(Integer.toString(input));
        if (sb.toString().contentEquals(sb.reverse())) {
            return true;
        }
        sb = new StringBuilder();
        String stringNumber = String.valueOf(input);
        String[] digits = stringNumber.split("");
        for (int i = 1; i < digits.length; i += 2) {
            int palindrome = Integer.parseInt(digits[i]) + Integer.parseInt(digits[i - 1]);
            sb.append(palindrome);
        }
        if (stringNumber.length() % 2 != 0) {
            sb.append(digits[digits.length - 1]);
        }
        return isPalindromeDescendant(Integer.parseInt(sb.toString()));
    }
}
