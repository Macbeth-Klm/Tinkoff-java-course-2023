package edu.project1;

import java.util.Random;
import java.util.Scanner;

public final class Main {
    private static final Scanner DEFAULT_SCANNER = new Scanner(System.in);
    private static final Random DEFAULT_WORD_INDEX = new Random();

    private Main() {
    }

    public static void main(String[] args) {
        ConsoleHangman consoleHangman = new ConsoleHangman(DEFAULT_SCANNER, DEFAULT_WORD_INDEX);
        consoleHangman.run();
    }
}
