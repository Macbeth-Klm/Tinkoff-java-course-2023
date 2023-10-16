package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Phrases {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String GUESS_THE_LETTER_PHRASE = "Guess a letter:";
    private static final String HIT = "Hit!";
    private static final String WIN = "You won!";
    private static final String LOSE = "You lost!";

    private Phrases() {
    }

    public static void printMistakeMessage(int attempts, int maxAttempts) {
        LOGGER.info("Missed, mistake " + attempts + " out of " + maxAttempts + ".");
    }

    public static void printTheWordMessage(String answerStatus) {
        LOGGER.info("The word: " + answerStatus);
    }

    public static void printGuessTheLetterPhrase() {
        LOGGER.info(GUESS_THE_LETTER_PHRASE);
    }

    public static void printHit() {
        LOGGER.info(HIT);
    }

    public static void printWin() {
        LOGGER.info(WIN);
    }

    public static void printLose() {

        LOGGER.info(LOSE);
    }
}
