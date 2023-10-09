package edu.hw1;

public class Task8 {
    public static boolean knightBoardCapture(int[][] chessBoard) {
        if (chessBoard.length != 8) {
            throw new IllegalArgumentException("Массив некорректного размера!");
        } else {
            for (int [] row : chessBoard) {
                if (row.length != 8) {
                    throw new IllegalArgumentException("Массив некорректного размера!");
                }
                for (int el : row) {
                    if (el != 0 && el != 1) {
                        throw new IllegalArgumentException("Массив содержит некорректные данные!");
                    }
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessBoard[i][j] == 1) {
                    if (j - 2 > 0 && i - 1 > 0 && chessBoard[i - 1][j - 2] == 1
                        || j - 2 > 0 && i + 1 < 8 && chessBoard[i + 1][j - 2] == 1
                        || j - 1 > 0 && i - 2 > 0 && chessBoard[i - 2][j - 1] == 1
                        || j - 1 > 0 && i + 2 < 8 && chessBoard[i + 2][j - 1] == 1
                        || j + 1 < 8 && i - 2 > 0 && chessBoard[i - 2][j + 1] == 1
                        || j + 1 < 8 && i + 2 < 8 && chessBoard[i + 2][j + 1] == 1
                        || j + 2 < 8 && i - 1 > 0 && chessBoard[i - 1][j + 2] == 1
                        || j + 2 < 8 && i + 1 < 8 && chessBoard[i + 1][j + 2] == 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
