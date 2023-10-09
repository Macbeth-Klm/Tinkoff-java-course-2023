package edu.hw1;

public class Task6 {
    public static int countK(int input, int count) {
        if (input < 1001 || input > 9999) {
            return -1;
        } else if (input == 6174) {
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
        sb = new StringBuilder();
        for (int i = digits.length - 1; i > -1; i--) {
            sb.append(digits[i]);
        }

        int minValue = Integer.parseInt(sb.toString());
        int res = maxValue - minValue;
        count += 1 + countK(res, count);

        return count;
    }
}
