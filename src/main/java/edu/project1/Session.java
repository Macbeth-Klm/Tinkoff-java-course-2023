package edu.project1;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

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
        maxAttempts = getUniqueCharCount();
        attempts = 0;
        answerStatus = "*".repeat(realWord.length());
    }

    private int getUniqueCharCount() {
        Set<Character> uniqueCharSet = new HashSet<>();
        for (char letter : realWord.toCharArray()) {
            uniqueCharSet.add(letter);
        }
        return uniqueCharSet.size();
    }

    private void updateAnswerStatus(char playerAnswerLetter) {
        StringBuilder sb = new StringBuilder(answerStatus);
        char[] realWordArray = realWord.toCharArray();
        for (int i = 0; i < realWordArray.length; i++) {
            if (realWordArray[i] == playerAnswerLetter) {
                sb.setCharAt(i, playerAnswerLetter);
            }
        }
        answerStatus = sb.toString();
    }

    public void makeMove(List<String> answerHistory) {
        GameMessage.guessLetter();
        playerAnswer = Player.getAnswer(scanner);
        if (playerAnswer.length() == 1) {
            if (answerHistory.contains(playerAnswer)) {
                GameMessage.sameAnswer();
            } else {
                answerHistory.add(playerAnswer);
                if (realWord.contains(playerAnswer)) {
                    GameMessage.hit();
                    updateAnswerStatus(playerAnswer.toCharArray()[0]);
                } else {
                    attempts++;
                    GameMessage.mistake(attempts, maxAttempts);
                }
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
