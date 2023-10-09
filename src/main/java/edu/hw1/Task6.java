package edu.hw1;

public final class Task6 {
    private final static int MIN_VALUE = 1001;
    private final static int MAX_VALUE = 9999;
    private final static int RESULT_VALUE = 6174;

    private Task6() {
    }

    public static int countK(int input) {
        if (input < MIN_VALUE || input > MAX_VALUE) {
            return -1;
        } else if (input == RESULT_VALUE) {
            return 0;
        }
        String inputString = Integer.toString(input);
        char[] digits = inputString.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits.length; i++) {
            for (int j = i + 1; j < digits.length; j++) {
                if (digits[i] < digits[j]) {
                    char temp = digits[i];
                    digits[i] = digits[j];
                    digits[j] = temp;
                }
            }
            sb.append(digits[i]);
        }
        int maxValue = Integer.parseInt(sb.toString());
        sb.setLength(0);
        for (int i = digits.length - 1; i > -1; i--) {
            sb.append(digits[i]);
        }
        int minValue = Integer.parseInt(sb.toString());
        int res = maxValue - minValue;
        return 1 + countK(res);
    }
}
