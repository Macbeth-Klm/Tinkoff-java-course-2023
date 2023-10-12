package edu.hw1;

public final class Task7 {
    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        char[] binCode = Integer.toBinaryString(n).toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shift; i++) {
            sb.delete(0, sb.length());
            for (int j = 0; j < binCode.length; j++) {
                char temp = (j == binCode.length - 1) ? binCode[0] : binCode[j + 1];
                sb.append(temp);
            }

            binCode = sb.toString().toCharArray();
        }
        return Integer.parseInt(sb.toString(), 2);
    }

    public static int rotateRight(int n, int shift) {
        char[] binCode = Integer.toBinaryString(n).toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shift; i++) {
            sb.delete(0, sb.length());
            for (int j = 0; j < binCode.length; j++) {
                char temp = (j == 0) ? binCode[binCode.length - 1] : binCode[j - 1];
                sb.append(temp);
            }
            binCode = sb.toString().toCharArray();
        }
        return Integer.parseInt(sb.toString(), 2);
    }
}
