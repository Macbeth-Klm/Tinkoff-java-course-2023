package edu.project1;

public final class ConsoleHangman {
    private static final String COMMAND_GIVE_UP = "/gg";

    private ConsoleHangman() {
    }

    public static void run(int maxAttempts) {
        Session session = new Session(maxAttempts);
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

    public static void run(Session session, String playerAnswer) {
        while (session.getAttempts() < session.getMaxAttempts()) {
            session.makeMove(playerAnswer);
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
}
