package edu.hw1;

public final class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] a1, int[] a2) {
        if (a1.length == 0 || a2.length == 0) {
            throw new IllegalArgumentException("Один или оба массива пустые!");
        }
        int min = a1[0];
        int max = a1[0];
        for (int el : a1) {
            if (el < min) {
                min = el;
            }
            if (el > max) {
                max = el;
            }
        }
        for (int el : a2) {
            if (min > el || max < el) {
                return true;
            }
        }
        return false;
    }
}
