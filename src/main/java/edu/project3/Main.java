package edu.project3;

import java.util.Scanner;

public final class Main {
    private static final Scanner DEFAULT_SCANNER = new Scanner(System.in);

    private Main() {
    }

    public static void main(String[] args) {
        LogAnalyser analyser = new LogAnalyser();
        analyser.analiseLogs(DEFAULT_SCANNER);
    }
}
