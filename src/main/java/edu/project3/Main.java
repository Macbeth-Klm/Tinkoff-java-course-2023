package edu.project3;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        String input = String.join(" ", args);
        LogAnalyser.analiseLogs(input);
    }
}
