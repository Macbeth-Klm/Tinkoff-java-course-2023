package edu.project2;

public record Maze(int height, int width, Cell[][] grid) {

    public Cell getCell(int row, int col) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == row && j == col) {
                    return grid[i][j];
                }
            }
        }
        throw new IllegalArgumentException("Элемента с такими координатами не существует!");
    }
}
