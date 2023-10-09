package edu.hw1;

public class Task4 {
    public static String fixString(String input) {
        StringBuilder sb = new StringBuilder();
        String[] stringSymbols = input.split("");

        for (int i = 1; i < stringSymbols.length; i += 2) {
            sb.append(stringSymbols[i]);
            sb.append(stringSymbols[i - 1]);
        }

        if (input.length() % 2 != 0) {
            sb.append(stringSymbols[stringSymbols.length - 1]);
        }

        return sb.toString();
    }
}
