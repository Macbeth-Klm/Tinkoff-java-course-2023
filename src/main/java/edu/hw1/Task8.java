package edu.hw1;

public final class Task8 {
    private final static int ARRAY_LENGTH = 8;

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] chessBoard) {
        if (!isBoardCorrect(chessBoard)) {
            throw new IllegalArgumentException("Массив некорректный!");
        }

        for (int i = 0; i < ARRAY_LENGTH; i++) {
            for (int j = 0; j < ARRAY_LENGTH - 1; j++) {
                if (chessBoard[i][j] == 1 && doesKnightCapture(chessBoard, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isBoardCorrect(int[][] chessBoard) {
        if (chessBoard.length != ARRAY_LENGTH) {
            return false;
        } else {
            for (int[] row : chessBoard) {
                if (row.length != ARRAY_LENGTH) {
                    return false;
                }
                for (int el : row) {
                    if (el != 0 && el != 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean doesKnightCapture(int[][] chessBoard, int i, int j) {
        return j - 2 > 0 && i + 1 < ARRAY_LENGTH && chessBoard[i + 1][j - 2] == 1
                || j + 2 < ARRAY_LENGTH && i + 1 < ARRAY_LENGTH && chessBoard[i + 1][j + 2] == 1
                || j - 1 > 0 && i + 2 < ARRAY_LENGTH && chessBoard[i + 2][j - 1] == 1
                || j + 1 < ARRAY_LENGTH && i + 2 < ARRAY_LENGTH && chessBoard[i + 2][j + 1] == 1;
    }
}
