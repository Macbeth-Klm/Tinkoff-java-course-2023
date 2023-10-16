package edu.project1;

public final class ConsoleHangman {
    private static final String COMMAND_SURRENDER = "/gg";
    private static final int MAX_ATTEMPTS = 5;

    private ConsoleHangman() {
    }

    public static void run(edu.project1.Session session, String playerAnswer) {
        while (session.getAttempts() < session.getMaxAttempts()) {
            session.makeMove(playerAnswer);
            if (session.getPlayerAnswer().equals(COMMAND_SURRENDER)) {
                edu.project1.Phrases.printLose();
                break;
            }
            if (session.getPlayerAnswer().length() != 1) {
                continue;
            }
            if (session.getRealWord().equals(session.getAnswerStatus())) {
                edu.project1.Phrases.printWin();
                break;
            }
        }
        if (session.getAttempts() == session.getMaxAttempts()) {
            edu.project1.Phrases.printLose();
        }
    }
}
