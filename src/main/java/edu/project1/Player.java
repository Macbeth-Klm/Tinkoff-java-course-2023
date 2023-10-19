package edu.project1;

import java.util.Scanner;

public final class Player {
    private static final Scanner SCANNER = new Scanner(System.in);

    private Player() {
    }

    public static String guessLetter() {
        return SCANNER.nextLine();
    }

    public static String guessLetter(String answer) {
        return answer;
    }
}
