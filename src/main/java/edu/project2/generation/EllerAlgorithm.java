package edu.project2.generation;

import java.util.Arrays;
import java.util.Random;

public final class EllerAlgorithm implements Generator {

    private final Random doesAddWall = new Random();
    private int[][] intGrid;
    private Cell[][] cells;
    private int counter;

    public EllerAlgorithm() {
        counter = 0;
    }

    @Override
    public Maze generate(int height, int width) {
        intGrid = new int[height][width];
        cells = new Cell[height][width];
        for (int i = 0; i < height - 1; i++) {
            /* Шаг 1 */
            initRow(i);
            /* Шаг 2 */
            addingVerticalWalls(i);
            /* Шаг 3 */
            addingHorizontalWalls(i);
            /* Шаг 4.1*/
            preparingNewLine(i);
        }
        /* Шаг 4.2 */
        addingEndLine();
        return new Maze(height, width, cells);
    }

    private void initRow(int row) {
        for (int j = 0; j < intGrid[row].length; j++) {
            if (intGrid[row][j] == 0) {
                intGrid[row][j] = ++counter;
            }
            cells[row][j] = new Cell(row, j, false, false);
        }
    }

    private void addingVerticalWalls(int row) {
        for (int j = 0; j < intGrid[row].length - 1; j++) {
            if (doesAddWall.nextBoolean() || intGrid[row][j] == intGrid[row][j + 1]) {
                cells[row][j].setRightWall(true);
            } else {
                intGrid[row][j + 1] = intGrid[row][j];
            }
        }
        cells[row][intGrid[row].length - 1].setRightWall(true); // Создание стены в крайней правой клетке
    }

    private void addingHorizontalWalls(int row) {
        for (int j = 0; j < intGrid[row].length; j++) {
            if (doesAddWall.nextBoolean() && isNotOnlyOneCellInSet(row, j)) {
                cells[row][j].setDownWall(true);
            }
        }
    }

    private boolean isNotOnlyOneCellInSet(int row, int column) {
        for (int j = 1; column - j > -1; j++) {
            if (cells[row][column - j].isRightWall()) {
                break;
            } else if (!cells[row][column - j].isDownWall()) {
                return true;
            }
        }
        if (!cells[row][column].isRightWall()) {
            for (int j = 1; column + j < intGrid[row].length; j++) {
                if (cells[row][column + j].isRightWall()) {
                    break;
                } else if (!cells[row][column + j].isDownWall()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void preparingNewLine(int row) {
        for (int j = 0; j < intGrid[row].length; j++) {
            intGrid[row + 1][j] = (cells[row][j].isDownWall()) ? 0 : intGrid[row][j];
        }
    }

    private void addingEndLine() {
        int lastRow = intGrid.length - 1;
        initRow(lastRow);
        Arrays.stream(cells[lastRow])
            .forEach(c -> c.setDownWall(true));
        for (int j = 0; j < intGrid[lastRow].length - 2; j++) {
            if (doesAddWall.nextBoolean() && intGrid[lastRow][j] == intGrid[lastRow][j + 1]) {
                    cells[lastRow][j].setRightWall(true);
            }
            cells[lastRow][j].setDownWall(true);
        }
        cells[lastRow][cells[lastRow].length - 1].setRightWall(true);
    }
}
