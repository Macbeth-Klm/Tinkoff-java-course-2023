package edu.project1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Project1Test {
    /*
        Для тестирования был создан конструктор Session(int maxAttempts, String realString),
        так как метод Math.random() не подлежит тестированию.

        Также часть методов перегружены по количеству параметров для тестирования. Методы отличаются
        способами ввода данных. Это сделано для избежания тестирования ввода из консоли.
    */

    @Test
    void invalidStringLength() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Session session = new edu.project1.Session(5, 2);
        });
    }

    @Test
    void winCheck() {
        // given
        String[] playerAnswers = new String[] {
            "a",
            "b",
            "e",
            "o",
            "l",
            "h"
        };
        Session session = new edu.project1.Session(5, 0);
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
    void loseBecauseOfMistakes() {
        // given
        Session session = new Session(5, 0);
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
        Session session = new Session(5, 0);
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
        Session session = new Session(5, 0);
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
        Session session = new Session(5, 0);
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
        Session session = new Session(5, 0);
        ConsoleHangman.run(session, "/gg");

        //when
        String answerStatus = session.getAnswerStatus();
        int attempts = session.getAttempts();

        //then
        assertThat(answerStatus)
            .isEqualTo("*****");
        assertThat(attempts)
            .isZero();
    }
}
