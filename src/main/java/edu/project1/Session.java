package edu.project1;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Session {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String realWord;
    private final int maxAttempts;
    private String playerAnswer;
    private String answerStatus;
    private int attempts;

//    public Session(int maxAttempts) {
//        this.maxAttempts = maxAttempts;
//        wordIndex = new Random();
//        realWord = Dictionary.getRandomWord(wordIndex);
//        if (realWord.length() > this.maxAttempts) {
//            throw new IllegalArgumentException();
//        }
//        attempts = 0;
//        answerStatus = "*".repeat(realWord.length());
//    }

    public Session(int maxAttempts, Random wordIndex) {
        this.maxAttempts = maxAttempts;
        realWord = Dictionary.getRandomWord(wordIndex);
        if (realWord.length() > this.maxAttempts) {
            throw new IllegalArgumentException();
        }
        attempts = 0;
        answerStatus = "*".repeat(realWord.length());
    }

    private void updateAnswerStatus() {
        StringBuilder sb = new StringBuilder(answerStatus);
        char[] realWordArray = realWord.toCharArray();
        char playerAnswerChar = playerAnswer.toCharArray()[0];
        for (int i = 0; i < realWordArray.length; i++) {
            if (realWordArray[i] == playerAnswerChar) {
                sb.deleteCharAt(i);
                sb.insert(i, playerAnswerChar);
            }
        }
        answerStatus = sb.toString();
    }

//    public void makeMove() {
//        LOGGER.info("Guess a letter:");
//        playerAnswer = Player.guessLetter();
//        if (playerAnswer.length() == 1) {
//            if (realWord.contains(playerAnswer)) {
//                LOGGER.info("Hit!");
//                updateAnswerStatus();
//            } else {
//                attempts++;
//                LOGGER.info("Missed, mistake " + attempts + " out of " + maxAttempts + ".");
//            }
//            LOGGER.info("The word: " + answerStatus);
//        }
//    }

    public void makeMove(String answer) {
        LOGGER.info("Guess a letter:");
        playerAnswer = Player.guessLetter(answer);
        if (playerAnswer.length() == 1) {
            if (realWord.contains(playerAnswer)) {
                LOGGER.info("Hit!");
                updateAnswerStatus();
            } else {
                attempts++;
                LOGGER.info("Missed, mistake " + attempts + " out of " + maxAttempts + ".");
            }
            LOGGER.info("The word: " + answerStatus);
        }
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getAttempts() {
        return attempts;
    }

    public String getRealWord() {
        return realWord;
    }

    public String getAnswerStatus() {
        return answerStatus;
    }

    public String getPlayerAnswer() {
        return playerAnswer;
    }
}
