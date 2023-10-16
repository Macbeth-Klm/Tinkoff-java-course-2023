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
            edu.project1.Session session = new edu.project1.Session(5, "indivisibility");
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
        edu.project1.Session session = new edu.project1.Session(5, "hello");
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
        edu.project1.Session session = new edu.project1.Session(5, "hello");
        edu.project1.ConsoleHangman.run(session, "a");

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
        edu.project1.Session session = new edu.project1.Session(5, "hello");
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
        edu.project1.Session session = new edu.project1.Session(5, "hello");
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
        edu.project1.Session session = new edu.project1.Session(5, "hello");
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
        edu.project1.Session session = new edu.project1.Session(5, "hello");
        edu.project1.ConsoleHangman.run(session, "/gg");

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
