package edu.hw1;

public class Task2 {
    public static int countDigits(int inputNumber) {
        int res = 0;
        int remainingNumbers = inputNumber;

        do {
            remainingNumbers /= 10;
            res++;
        } while (remainingNumbers != 0);

        return res;
    }
}
