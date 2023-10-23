package edu.project1;

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
        GameMessage.introduction(session.getMaxAttempts());
        while (session.getAttempts() < session.getMaxAttempts()) {
            session.makeMove();
            if (session.getPlayerAnswer().equals(COMMAND_GIVE_UP)) {
                GameMessage.giveUp();
                break;
            }
            if (session.getRealWord().equals(session.getAnswerStatus())) {
                GameMessage.win();
                break;
            }
            if (session.getAttempts() == session.getMaxAttempts()) {
                GameMessage.lose();
            }
        }
    }

    @NotNull
    public Session getSession() {
        return session;
    }
}
