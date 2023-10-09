package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {
    @Test
    void knightBoardCaptureFirst() {
        // given
        int[][] chessBoard = new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        // when
        boolean result = Task8.knightBoardCapture(chessBoard);
        // then
        assertThat(result)
            .isEqualTo(true);
    }

    @Test
    void knightBoardCaptureSecond() {
        // given
        int[][] chessBoard = new int[][] {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        // when
        boolean result = Task8.knightBoardCapture(chessBoard);

        // then
        assertThat(result)
            .isEqualTo(false);
    }

    @Test
    void knightBoardCaptureThird() {
        // given invalid array rows
        final int[][] invalidColumnsChessBoard = new int[][] {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        };

        // when
        IllegalArgumentException resultException =
            Assertions.assertThrows(IllegalArgumentException.class, () ->
                Task8.knightBoardCapture(invalidColumnsChessBoard));

        // then
        Assertions.assertEquals("Массив некорректный!", resultException.getMessage());
    }

    @Test
    void knightBoardCaptureFourth() {
        // given invalid array rows
        final int[][] invalidRowsChessBoard = new int[][] {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0}
        };

        // when
        IllegalArgumentException resultException = Assertions.assertThrows(IllegalArgumentException.class, () ->
            Task8.knightBoardCapture(invalidRowsChessBoard));

        // then
        Assertions.assertEquals("Массив некорректный!", resultException.getMessage());
    }

    @Test
    void knightBoardCaptureFifth() {
        // given array containing invalid numbers
        final int[][] invalidNumbersChessBoard = new int[][] {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 9, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 3, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        };

        // when
        IllegalArgumentException resultException = Assertions.assertThrows(IllegalArgumentException.class, () ->
            Task8.knightBoardCapture(invalidNumbersChessBoard));

        // then
        Assertions.assertEquals("Массив некорректный!", resultException.getMessage());
    }
}
