package edu.hw1;

public final class Task2 {
    private static final int DIVIDER = 10;

    private Task2() {
    }

    public static int countDigits(int inputNumber) {
        int res = 0;
        int remainingNumbers = inputNumber;
        do {
            remainingNumbers /= DIVIDER;
            res++;
        } while (remainingNumbers != 0);

        return res;
    }
}
