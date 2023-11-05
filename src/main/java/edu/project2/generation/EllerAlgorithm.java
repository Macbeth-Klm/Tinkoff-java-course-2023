package edu.project2.generation;

import edu.project2.Cell;
import edu.project2.Maze;
import java.util.Arrays;
import java.util.Random;

public final class EllerAlgorithm implements Generator {

    private final Random doesAddWall;
    private int[][] intGrid;
    private Cell[][] cells;
    private int counter;

    public EllerAlgorithm(Random doesAddWall) {
        this.doesAddWall = doesAddWall;
        counter = 0;
    }

    @Override
    public Maze generate(int height, int width) {
        intGrid = new int[height][width];
        cells = new Cell[height][width];
        for (int i = 0; i < height - 1; i++) {
            initRow(i);
            addingVerticalWalls(i);
            addingHorizontalWalls(i);
            preparingNewLine(i);
        }
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
                mergeSets(row, j);
            }
        }
        cells[row][intGrid[row].length - 1].setRightWall(true); // Создание стены в крайней правой клетке
    }

    private void mergeSets(int row, int col) {
        int goal = intGrid[row][col];
        int condition = intGrid[row][col + 1];
        for (int i = 0; i < intGrid.length; i++) {
            for (int j = 0; j < intGrid[row].length; j++) {
                if (intGrid[i][j] == condition) {
                    intGrid[i][j] = goal;
                }
            }
        }
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
            if (intGrid[row][column - j] != intGrid[row][column]) {
                break;
            } else if (!cells[row][column - j].isDownWall()) {
                return true;
            }
        }
        if (column < cells[row].length - 1) {
            for (int j = 1; column + j < cells[row].length; j++) {
                if (intGrid[row][column + j] == intGrid[row][column]) {
                    if (!cells[row][column + j].isDownWall()) {
                        return true;
                    }
                } else {
                    break;
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
        addingVerticalWalls(lastRow);
        for (int j = 0; j < intGrid[lastRow].length - 1; j++) {
            if (intGrid[lastRow][j] != intGrid[lastRow][j + 1]) {
                cells[lastRow][j].setRightWall(false);
                mergeSets(lastRow, j);
            }
        }
        cells[lastRow][cells[lastRow].length - 1].setRightWall(true);
    }
}
