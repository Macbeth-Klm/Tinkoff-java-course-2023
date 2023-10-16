package edu.project1;

public class Session {
    private final String realWord;
    private final int maxAttempts;
    private String playerAnswer;
    private String answerStatus;
    private int attempts;

    public Session(int maxAttempts, int wordIndex) {
        this.maxAttempts = maxAttempts;
        realWord = Dictionary.getRandomWord(wordIndex);
        if (realWord.length() > this.maxAttempts) {
            throw new IllegalArgumentException("Загаданное слово некорректной длины!");
        }
        attempts = 0;
        answerStatus = "*".repeat(realWord.length());
    }

    private void updateAnswerStatus() {
        StringBuilder sb = new StringBuilder(answerStatus);
        char[] realWordArray = realWord.toCharArray();
        char playerAnswerChar = playerAnswer.toCharArray()[0]; // Массив состоит из 1 символа
        for (int i = 0; i < realWordArray.length; i++) {
            if (realWordArray[i] == playerAnswerChar) {
                sb.deleteCharAt(i);
                sb.insert(i, playerAnswerChar);
            }
        }
        answerStatus = sb.toString();
    }

    public void makeMove(String answer) {
        Phrases.printGuessTheLetterPhrase();
        playerAnswer = Player.guessLetter(answer);
        if (playerAnswer.length() == 1) {
            if (realWord.contains(playerAnswer)) {
                Phrases.printHit();
                updateAnswerStatus();
            } else {
                attempts++;
                Phrases.printMistakeMessage(attempts, maxAttempts);
            }
            Phrases.printTheWordMessage(answerStatus);
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
