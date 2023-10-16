package edu.project1;

public final class ConsoleHangman {
    private static final String COMMAND_SURRENDER = "/gg";
    private static final int MAX_ATTEMPTS = 5;

    private ConsoleHangman() {
    }

    public static void run() {
        Session session = new Session(MAX_ATTEMPTS);
        while (session.getAttempts() < session.getMaxAttempts()) {
            session.makeMove();
            if (session.getPlayerAnswer().equals(COMMAND_SURRENDER)) {
                Phrases.printLose();
                break;
            }
            if (session.getPlayerAnswer().length() != 1) {
                continue;
            }
            if (session.getRealWord().equals(session.getAnswerStatus())) {
                Phrases.printWin();
                break;
            }
        }
        if (session.getAttempts() == session.getMaxAttempts()) {
            Phrases.printLose();
        }
    }

    public static void run(Session session, String playerAnswer) {
        while (session.getAttempts() < session.getMaxAttempts()) {
            session.makeMove(playerAnswer);
            if (session.getPlayerAnswer().equals(COMMAND_SURRENDER)) {
                Phrases.printLose();
                break;
            }
            if (session.getPlayerAnswer().length() != 1) {
                continue;
            }
            if (session.getRealWord().equals(session.getAnswerStatus())) {
                Phrases.printWin();
                break;
            }
        }
        if (session.getAttempts() == session.getMaxAttempts()) {
            Phrases.printLose();
        }
    }
}
