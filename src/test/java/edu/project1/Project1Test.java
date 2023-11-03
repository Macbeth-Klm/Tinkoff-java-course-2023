package edu.project1;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Random;
import java.util.Scanner;
import static org.assertj.core.api.Assertions.assertThat;

public class Project1Test {
    static String answers = """
        a
        b
        e
        o
        l
        h
        """;

    @Test
    void shouldWinWithTwoAttempts() {
        Scanner scanner = new Scanner(new ByteArrayInputStream(answers.getBytes()));
        Random wordIndex = new Random(-10); // realWord = "hello"
        ConsoleHangman consoleHangman = new ConsoleHangman(scanner, wordIndex);

        consoleHangman.run();
        String answerStatus = consoleHangman.getSession().getAnswerStatus();
        int attempts = consoleHangman.getSession().getAttempts();

        assertThat(answerStatus.equals(consoleHangman.getSession().getRealWord()))
            .isTrue();
        assertThat(attempts)
            .isEqualTo(2);
        assertThat(consoleHangman.getSession().getMaxAttempts())
            .isEqualTo(4);
    }

    @Test void shouldLoseDueToOverAttempts() {
        Scanner scanner = new Scanner(new ByteArrayInputStream(answers.getBytes()));
        Random wordIndex = new Random(-5); // realWord = "index"
        ConsoleHangman consoleHangman = new ConsoleHangman(scanner, wordIndex);

        consoleHangman.run();
        String answerStatus = consoleHangman.getSession().getAnswerStatus();
        int attempts = consoleHangman.getSession().getAttempts();

        assertThat(answerStatus.equals(consoleHangman.getSession().getRealWord()))
            .isFalse();
        assertThat(attempts)
            .isEqualTo(consoleHangman.getSession().getMaxAttempts());
        assertThat(consoleHangman.getSession().getMaxAttempts())
            .isEqualTo(5);
    }

    @Test
    void shouldCorrectChangeStatusWhenGuessing() {
        Random randomWord = new Random(-3); // realWord = "indivisibility"
        Scanner scanner = new Scanner(new ByteArrayInputStream("y".getBytes()));
        Session session = new Session(scanner, randomWord);

        session.makeMove();
        String answerStatus = session.getAnswerStatus();
        int attempts = session.getAttempts();

        assertThat(answerStatus)
            .isEqualTo("*************y");
        assertThat(attempts)
            .isZero();
    }

    @Test
    void shouldCorrectChangeStatusWhenMistake() {
        Random randomWord = new Random(1); // realWord = "trust"
        Scanner scanner = new Scanner(new ByteArrayInputStream("a".getBytes()));
        Session session = new Session(scanner, randomWord);
        session.makeMove();

        String answerStatus = session.getAnswerStatus();
        int attempts = session.getAttempts();

        assertThat(answerStatus)
            .isEqualTo("*****");
        assertThat(attempts)
            .isOne();
    }

    @Test
    void shouldNotChangeStatusWhenIncorrectAnswerIsEntered() {
        Random randomWord = new Random(-10); // realWord = "hello"
        Scanner scanner = new Scanner(new ByteArrayInputStream("ajdskl".getBytes()));
        Session session = new Session(scanner, randomWord);

        session.makeMove();
        String answerStatus = session.getAnswerStatus();
        int attempts = session.getAttempts();

        assertThat(answerStatus)
            .isEqualTo("*****");
        assertThat(attempts)
            .isZero();
    }

    @Test
    void shouldNotChangeStatusWhenLetterWhichHaveBeenEnteredBeforeIsEntered() {
        String newAnswers = """
            a
            a
            """;
        Random randomWord = new Random(-10); // realWord = "hello"
        Scanner scanner = new Scanner(new ByteArrayInputStream(newAnswers.getBytes()));
        Session session = new Session(scanner, randomWord);

        session.makeMove();
        session.makeMove();
        String answerStatus = session.getAnswerStatus();
        int attempts = session.getAttempts();

        assertThat(answerStatus)
            .isEqualTo("*****");
        assertThat(attempts)
            .isOne();
        assertThat(session.getAnswerHistory())
            .containsExactly('a');
    }

    @Test
    void checkingStatusWhenSurrenderCommandIsEntered() {
        Random randomWord = new Random(-10); // realWord = "hello"
        Scanner scanner = new Scanner(new ByteArrayInputStream("/gg".getBytes()));
        ConsoleHangman consoleHangman = new ConsoleHangman(scanner, randomWord);

        consoleHangman.run();
        String answerStatus = consoleHangman.getSession().getAnswerStatus();
        int attempts = consoleHangman.getSession().getAttempts();

        //then
        assertThat(answerStatus.equals(consoleHangman.getSession().getRealWord()))
            .isFalse();
        assertThat(attempts)
            .isZero();
    }
}
