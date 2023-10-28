package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.jetbrains.annotations.NotNull;

public class ConsoleHangman {
    private static final String COMMAND_GIVE_UP = "/gg";
    private final Session session;

    public ConsoleHangman(Scanner scanner, Random wordIndex) {
        session = new Session(scanner, wordIndex);
    }

    public void run() {
        GameMessage.introduction(session.getMaxAttempts(), session.getAnswerStatus());
        List<String> answerHistory = new ArrayList<>();
        var realWord = session.getRealWord();
        while (session.getAttempts() < session.getMaxAttempts()) {
            session.makeMove(answerHistory);
            var giveUp = session.getPlayerAnswer().equals(COMMAND_GIVE_UP);
            if (giveUp || session.getAnswerStatus().equals(realWord)) {
                if (giveUp) {
                    GameMessage.giveUp(realWord);
                } else {
                    GameMessage.win();
                }
                return;
            }
        }
        GameMessage.lose(realWord);
    }

    @NotNull
    public Session getSession() {
        return session;
    }
}
