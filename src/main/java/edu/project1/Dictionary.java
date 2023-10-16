package edu.project1;

public final class Dictionary {
    private static final String[] WORDS = new String[] {
        "hello",
        "house",
        "indivisibility",
        "agree",
        "trust",
        "index"
    };

    private Dictionary() {
    }

    public static String getRandomWord() {
        int index = (int) (Math.random() * (WORDS.length - 1));
        return WORDS[index];
    }
}
