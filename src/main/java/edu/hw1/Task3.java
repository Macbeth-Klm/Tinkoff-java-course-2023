package edu.hw1;

public final class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] a1, int[] a2) {
        int min1 = a1[0];
        int max1 = a1[0];
        int min2 = a2[0];
        int max2 = a2[0];
        for (int el : a1) {
            if (el < min1) {
                min1 = el;
            }
            if (el > max1) {
                max1 = el;
            }
        }
        for (int el : a2) {
            if (el < min2) {
                min2 = el;
            }
            if (el > max2) {
                max2 = el;
            }
        }
        return (min1 > min2 || max1 < max2);
    }
}
