package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConsoleHangman {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String COMMAND_SURRENDER = "/gg";

    private ConsoleHangman() {
    }

    public static void run(Session session, String playerAnswer) {
        while (session.getAttempts() < session.getMaxAttempts()) {
            session.makeMove(playerAnswer);
            if (session.getPlayerAnswer().equals(COMMAND_SURRENDER)) {
                LOGGER.info("You lost!");
                break;
            }
            if (session.getRealWord().equals(session.getAnswerStatus())) {
                LOGGER.info("You won!");
                break;
            }
        }
        if (session.getAttempts() == session.getMaxAttempts()) {
            LOGGER.info("You lost!");
        }
    }
}
