package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Session {
    private static final String COMMAND_GIVE_UP = "/gg";
    private final Scanner scanner;
    private final String realWord;
    private final int maxAttempts;
    private String playerAnswer;
    private String answerStatus;
    private int attempts;

    public Session(Scanner scanner, Random wordIndex) {
        this.scanner = scanner;
        realWord = Dictionary.getRandomWord(wordIndex);
        maxAttempts = getUniqueCharCount(realWord);
        attempts = 0;
        answerStatus = "*".repeat(realWord.length());
    }

    private int getUniqueCharCount(String realWord) {
        char[] realWordCharArray = realWord.toCharArray();
        List<Character> uniqueCharList = new ArrayList<>();
        for (char letter : realWordCharArray) {
            if (!uniqueCharList.contains(letter)) {
                uniqueCharList.add(letter);
            }
        }
        return uniqueCharList.size();
    }

    private void updateAnswerStatus() {
        StringBuilder sb = new StringBuilder(answerStatus);
        char[] realWordArray = realWord.toCharArray();
        char playerAnswerChar = playerAnswer.toCharArray()[0];
        for (int i = 0; i < realWordArray.length; i++) {
            if (realWordArray[i] == playerAnswerChar) {
                sb.replace(i, i + 1, playerAnswer);
            }
        }
        answerStatus = sb.toString();
    }

    public void makeMove() {
        GameMessage.guessLetter();
        playerAnswer = Player.getAnswer(scanner);
        if (playerAnswer.length() == 1) {
            if (realWord.contains(playerAnswer)) {
                GameMessage.hit();
                updateAnswerStatus();
            } else {
                attempts++;
                GameMessage.mistake(attempts, maxAttempts);
            }
            GameMessage.wordStatus(answerStatus);
        } else if (!playerAnswer.equals(COMMAND_GIVE_UP)) {
            GameMessage.incorrectAnswerFormat();
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
