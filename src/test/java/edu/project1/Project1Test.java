package edu.project1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;

public class Project1Test {

    @Test
    void invalidStringLength() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Random randomWord = new Random(-3); // randomWord.nextInt(-3) = 2 ("indivisibility")
            Session session = new Session(5, randomWord);
        });
    }

    @Test
    void winCheckFirst() {
        // given
        String[] playerAnswers = new String[] {
            "a",
            "b",
            "e",
            "o",
            "l",
            "h"
        };
        Random randomWord = new Random(-10); // randomWord.nextInt(-10) = 0 ("hello")
        Session session = new Session(5, randomWord);
        for (String answer : playerAnswers) {
            session.makeMove(answer);
        }

        // when
        String answerStatus = session.getAnswerStatus();
        int attempts = session.getAttempts();

        // then
        assertThat(answerStatus.equals(session.getRealWord()))
            .isTrue();
        assertThat(attempts)
            .isEqualTo(2);
    }

    @Test
    void winCheckSecond() {
        //given
        Random randomWord = new Random(18); // randomWord.nextInt(18) = 6 ("indivisibility")
        Session session = new Session(5, randomWord);
        ConsoleHangman.run(session, "I");

        // when
        String answerStatus = session.getAnswerStatus();
        int attempts = session.getAttempts();

        // then
        assertThat(answerStatus.equals(session.getRealWord()))
            .isTrue();
        assertThat(attempts)
            .isEqualTo(0);
    }

    @Test
    void loseBecauseOfMistakes() {
        // given
        Random randomWord = new Random(-10); // randomWord.nextInt(-10) = 0 ("hello")
        Session session = new Session(5, randomWord);
        ConsoleHangman.run(session, "a");

        // when
        String answerStatus = session.getAnswerStatus();
        int attempts = session.getAttempts();

        // then
        assertThat(answerStatus.equals(session.getRealWord()))
            .isFalse();
        assertThat(attempts)
            .isEqualTo(5);
    }

    @Test
    void statusCheckFirst() {
        // given
        Random randomWord = new Random(-10); // randomWord.nextInt(-10) = 0 ("hello")
        Session session = new Session(5, randomWord);
        session.makeMove("e");

        //when
        String answerStatus = session.getAnswerStatus();
        int attempts = session.getAttempts();

        //then
        assertThat(answerStatus)
            .isEqualTo("*e***");
        assertThat(attempts)
            .isZero();
    }

    @Test
    void statusCheckSecond() {
        // given
        Random randomWord = new Random(-10); // randomWord.nextInt(-10) = 0 ("hello")
        Session session = new Session(5, randomWord);
        session.makeMove("a");

        //when
        String answerStatus = session.getAnswerStatus();
        int attempts = session.getAttempts();

        //then
        assertThat(answerStatus)
            .isEqualTo("*****");
        assertThat(attempts)
            .isOne();
    }

    @Test
    void statusCheckThird() {
        // given
        Random randomWord = new Random(-10); // randomWord.nextInt(-10) = 0 ("hello")
        Session session = new Session(5, randomWord);
        session.makeMove("hey");

        //when
        String answerStatus = session.getAnswerStatus();
        int attempts = session.getAttempts();

        //then
        assertThat(answerStatus)
            .isEqualTo("*****");
        assertThat(attempts)
            .isZero();
    }

    @Test
    void surrenderCommandTest() {
        // given
        Random randomWord = new Random(-10); // randomWord.nextInt(-10) = 0 ("hello")
        Session session = new Session(5, randomWord);
        ConsoleHangman.run(session, "/gg");

        //when
        String answerStatus = session.getAnswerStatus();
        int attempts = session.getAttempts();

        //then
        assertThat(answerStatus.equals(session.getRealWord()))
            .isFalse();
        assertThat(attempts)
            .isZero();
    }
}
