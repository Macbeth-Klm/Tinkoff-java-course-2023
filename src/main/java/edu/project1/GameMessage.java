package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class GameMessage {
    private static final Logger LOGGER = LogManager.getLogger();

    private GameMessage() {
    }

    public static void introduction(int maxAttempts) {
        LOGGER.info("Welcome to the Hangman!\nYou have " + maxAttempts + " attempts to guess the word. "
            + "If you wish to surrender, you can enter the command \"/gg\". Let's begin!");
    }

    public static void lose(String realWord) {
        LOGGER.info("You lost! The real word: " + realWord);
    }

    public static void win() {
        LOGGER.info("You won!");
    }

    public static void giveUp(String realWord) {
        LOGGER.info("You gave up! The real word: " + realWord);
    }

    public static void guessLetter() {
        LOGGER.info("Guess a letter:");
    }

    public static void hit() {
        LOGGER.info("Hit!");
    }

    public static void sameAnswer() {
        LOGGER.info("You've entered this answer before. Enter another letter.");
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
