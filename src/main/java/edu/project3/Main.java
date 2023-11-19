package edu.project3;

import java.io.IOException;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) throws IOException {
        String input = String.join(" ", args);
        LogAnalyser.analiseLogs(input);
    }
}
