package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public record Maze(int height, int width, Cell[][] grid) {

    public Cell getCell(int row, int col) {
        if (row < 0 || row >= height
            || col < 0 || col >= width) {
            throw new IllegalArgumentException("Элемента с такими координатами не существует!");
        }
        return grid[row][col];
    }

    public boolean isPerfect() {
        Queue<Cell> queue = new ArrayDeque<>();
        queue.add(this.getCell(0, 0));
        boolean[][] visited = new boolean[height][width];
        int visitedCount = 0;
        while (!queue.isEmpty()) {
            Cell currentCell = queue.remove();
            // Проверка на петлю
            if (visited[currentCell.getRow()][currentCell.getCol()]) {
                return false;
            }
            visited[currentCell.getRow()][currentCell.getCol()] = true;
            List<Cell> nextCells = new ArrayList<>();
            var row = currentCell.getRow();
            var col = currentCell.getCol();
            if (row > 0 && !this.getCell(row - 1, col).isDownWall()) {
                nextCells.add(this.getCell(row - 1, col));
            }
            if (col + 1 < width && !this.getCell(row, col).isRightWall()) {
                nextCells.add(this.getCell(row, col + 1));
            }
            if (!this.getCell(row, col).isDownWall()) {
                nextCells.add(this.getCell(row + 1, col));
            }
            if (col > 0 && !this.getCell(row, col - 1).isRightWall()) {
                nextCells.add(this.getCell(row, col - 1));
            }
            if (!nextCells.isEmpty()) {
                for (Cell cell : nextCells) {
                    if (!visited[cell.getRow()][cell.getCol()]) {
                        queue.add(cell);
                    }
                }
            }
            visitedCount++;
        }
        return visitedCount == height * width;
    }
}
