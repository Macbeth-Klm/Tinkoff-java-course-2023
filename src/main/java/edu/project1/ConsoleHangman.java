package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConsoleHangman {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String COMMAND_GIVE_UP = "/gg";
    private static final String LOSE = "You lost!";
    private static final String WIN = "You won!";
    private static final String GIVE_UP = "You gave up!";

    private ConsoleHangman() {
    }

//    public static void run(Session session) {
//        while (session.getAttempts() < session.getMaxAttempts()) {
//            session.makeMove();
//            if (session.getPlayerAnswer().equals(COMMAND_GIVE_UP)) {
//                LOGGER.info(GIVE_UP);
//                break;
//            }
//            if (session.getRealWord().equals(session.getAnswerStatus())) {
//                LOGGER.info(WIN);
//                break;
//            }
//            if (session.getAttempts() == session.getMaxAttempts()) {
//                LOGGER.info(LOSE);
//            }
//        }
//    }

    public static void run(Session session, String playerAnswer) {
        while (session.getAttempts() < session.getMaxAttempts()) {
            session.makeMove(playerAnswer);
            if (session.getPlayerAnswer().equals(COMMAND_GIVE_UP)) {
                LOGGER.info(GIVE_UP);
                break;
            }
            if (session.getRealWord().equals(session.getAnswerStatus())) {
                LOGGER.info(WIN);
                break;
            }
            if (session.getAttempts() == session.getMaxAttempts()) {
                LOGGER.info(LOSE);
            }
        }
    }
}
