package edu.project1.hangman;

import java.util.Scanner;

public final class Player {

    private Player() {
    }

    public static String getAnswer(Scanner scanner) {
        return scanner.nextLine();
    }
}
