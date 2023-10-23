package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class GameMessage {
    private static final Logger LOGGER = LogManager.getLogger();

    private GameMessage() {
    }

    public static void introduction(int maxAttempts) {
        LOGGER.info("Welcome to the Hangman! You have " + maxAttempts + " attempts to guess the word. Let's begin!");
    }

    public static void lose() {
        LOGGER.info("You lost!");
    }

    public static void win() {
        LOGGER.info("You won!");
    }

    public static void giveUp() {
        LOGGER.info("You gave up!");
    }

    public static void guessLetter() {
        LOGGER.info("Guess a letter:");
    }

    public static void hit() {
        LOGGER.info("Hit!");
    }

    public static void incorrectAnswerFormat() {
        LOGGER.info("The answer is not in the correct format! Enter only one letter.");
    }

    public static void mistake(int attempts, int maxAttempts) {
        LOGGER.info("Missed, mistake " + attempts + " out of " + maxAttempts + ".");
    }

    public static void wordStatus(String answerStatus) {
        LOGGER.info("The word: " + answerStatus);
    }
}
