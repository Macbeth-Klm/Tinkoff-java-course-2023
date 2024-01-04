package edu.project2.maze;

public record Maze(int height, int width, Cell[][] grid) {

    public Cell getCell(int row, int col) {
        if (row < 0 || row >= height
            || col < 0 || col >= width) {
            throw new IllegalArgumentException("Элемента с такими координатами не существует!");
        }
        return grid[row][col];
    }
}
