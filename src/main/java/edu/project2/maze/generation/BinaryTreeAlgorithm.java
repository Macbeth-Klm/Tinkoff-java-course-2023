package edu.project2.maze.generation;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import java.util.Random;

public class BinaryTreeAlgorithm implements Generator {

    private final Random doesBreakRightWall;
    private Cell[][] cells;

    public BinaryTreeAlgorithm(Random doesBreakRightWallWall) {
        this.doesBreakRightWall = doesBreakRightWallWall;
    }

    @Override
    public Maze generate(int height, int width) {
        initMazeGrid(height, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 && j != width - 1) {
                    cells[i][j].setRightWall(false);
                }
                if (j == width - 1 && i != 0) {
                    cells[i - 1][j].setDownWall(false);
                }
                if (j < width - 1 && i > 0) {
                    if (doesBreakRightWall.nextBoolean()) {
                        cells[i][j].setRightWall(false);
                    } else {
                        cells[i - 1][j].setDownWall(false);
                    }
                }
            }
        }
        return new Maze(height, width, cells);
    }

    public void initMazeGrid(int height, int width) {
        cells = new Cell[height][width];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j, true, true);
            }
        }
    }
}
