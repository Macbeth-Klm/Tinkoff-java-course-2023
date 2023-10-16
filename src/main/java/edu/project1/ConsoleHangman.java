package edu.project1;

public final class ConsoleHangman {
    private static final String COMMAND_SURRENDER = "/gg";

    private ConsoleHangman() {
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
