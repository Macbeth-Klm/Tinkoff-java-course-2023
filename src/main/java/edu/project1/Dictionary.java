package edu.project1;

import java.util.Random;

public final class Dictionary {
    private static final String[] WORDS = new String[] {
        "hello",
        "house",
        "indivisibility",
        "agree",
        "trust",
        "index",
        "I"
    };

    private Dictionary() {
    }

    public static String getRandomWord(Random wordIndex) {
        return WORDS[wordIndex.nextInt(WORDS.length)];
    }
}
