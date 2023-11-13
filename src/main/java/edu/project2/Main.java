package edu.project2;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner DEFAULT_SCANNER = new Scanner(System.in);
    private static final Random DEFAULT_RANDOM = new Random();

    private Main() {
    }

    public static void main(String[] args) {
        MazeExplorer mazeExplorer = new MazeExplorer(DEFAULT_SCANNER, DEFAULT_RANDOM);
        mazeExplorer.run();
    }
}
