package edu.project1;

import java.util.Random;

public class Session {
    private final static String EXCEPTION_MESSAGE = "Слово некорректной длины!";
    private final String realWord;
    private final int maxAttempts;
    private String playerAnswer;
    private String answerStatus;
    private int attempts;

    public Session(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        realWord = Dictionary.getRandomWord(new Random());
        if (realWord.length() > this.maxAttempts) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
        attempts = 0;
        answerStatus = "*".repeat(realWord.length());
    }

    public Session(int maxAttempts, Random wordIndex) {
        this.maxAttempts = maxAttempts;
        realWord = Dictionary.getRandomWord(wordIndex);
        if (realWord.length() > this.maxAttempts) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
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

    public void makeMove() {
        GameMessage.guessLetter();
        playerAnswer = Player.getAnswer();
        if (playerAnswer.length() == 1) {
            if (realWord.contains(playerAnswer)) {
                GameMessage.hit();
                updateAnswerStatus();
            } else {
                attempts++;
                GameMessage.mistake(attempts, maxAttempts);
            }
            GameMessage.wordStatus(answerStatus);
        }
    }

    public void makeMove(String answer) {
        GameMessage.guessLetter();
        playerAnswer = Player.getAnswer(answer);
        if (playerAnswer.length() == 1) {
            if (realWord.contains(playerAnswer)) {
                GameMessage.hit();
                updateAnswerStatus();
            } else {
                attempts++;
                GameMessage.mistake(attempts, maxAttempts);
            }
            GameMessage.wordStatus(answerStatus);
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
