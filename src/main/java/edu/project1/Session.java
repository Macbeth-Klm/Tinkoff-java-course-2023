package edu.project1;

public class Session {
    private final String realWord;
    private final int maxAttempts;
    private String playerAnswer;
    private String answerStatus;
    private int attempts;

    public Session(int maxAttempts, String realString) {
        this.maxAttempts = maxAttempts;
        realWord = realString; // Для теста с некорректной длиной строки

        if (realWord.length() > this.maxAttempts) {
            throw new IllegalArgumentException("Заданное слово некорректной длины!");
        }
        attempts = 0;
        answerStatus = "*".repeat(realWord.length());
    }

    private void updateAnswerStatus(String playerAnswer) {
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

//    public void makeMove() {
//        edu.project1.Phrases.printGuessTheLetterPhrase();
//        playerAnswer = edu.project1.Player.guessLetter();
//        if (playerAnswer.length() == 1) {
//            if (realWord.contains(playerAnswer)) {
//                edu.project1.Phrases.printHit();
//                updateAnswerStatus(playerAnswer);
//            } else {
//                attempts++;
//                edu.project1.Phrases.printMistakeMessage(attempts, maxAttempts);
//            }
//            edu.project1.Phrases.printTheWordMessage(answerStatus);
//        }
//    }

    public void makeMove(String playerAnswer) {
        edu.project1.Phrases.printGuessTheLetterPhrase();
        this.playerAnswer = playerAnswer;
        if (this.playerAnswer.length() == 1) {
            if (realWord.contains(this.playerAnswer)) {
                edu.project1.Phrases.printHit();
                updateAnswerStatus(this.playerAnswer);
            } else {
                attempts++;
                edu.project1.Phrases.printMistakeMessage(attempts, maxAttempts);
            }
            edu.project1.Phrases.printTheWordMessage(answerStatus);
        }
    }
}
