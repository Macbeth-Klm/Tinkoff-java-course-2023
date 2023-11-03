package edu.project2.solution;

import edu.project2.Cell;
import edu.project2.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LeeWaveAlgorithm implements Solver {
    private final Queue<Cell> queue = new ArrayDeque<>();

    @Override
    public List<Cell> solve(Maze maze, Cell rootCell, Cell goalCell) {
        queue.add(rootCell);
        int[][] dist = new int[maze.height()][maze.width()];
        boolean[][] visited = new boolean[maze.height()][maze.width()];
        while (!queue.isEmpty()) {
            Cell currentCell = queue.remove();
            if (currentCell == goalCell) {
                break;
            }
            List<Cell> nextCells = nextEnableCells(maze, currentCell.getRow(), currentCell.getCol());
            if (!nextCells.isEmpty()) {
                for (Cell cell : nextCells) {
                    if (!visited[cell.getRow()][cell.getCol()]) {
                        queue.add(cell);
                        dist[cell.getRow()][cell.getCol()] = dist[currentCell.getRow()][currentCell.getCol()] + 1;
                    }
                }
            }
            visited[currentCell.getRow()][currentCell.getCol()] = true;
        }
        List<Cell> path = new ArrayList<>();
        Cell curCell = goalCell;
        while (curCell != rootCell) {
            path.add(curCell);
            List<Cell> nextCells = nextEnableCells(maze, curCell.getRow(), curCell.getCol());
            for (Cell cell : nextCells) {
                if (dist[cell.getRow()][cell.getCol()] + 1 == dist[curCell.getRow()][curCell.getCol()]) {
                    curCell = cell;
                    break;
                }
            }
        }
        path.add(rootCell);
        return path.reversed();
    }

    private List<Cell> nextEnableCells(Maze maze, int row, int col) {
        List<Cell> nextCells = new ArrayList<>();
        // верхний сосед
        if (row > 0 && !maze.getCell(row - 1, col).isDownWall()) {
            nextCells.add(maze.getCell(row - 1, col));
        }
        // правый сосед
        if (col + 1 < maze.width() && !maze.getCell(row, col).isRightWall()) {
            nextCells.add(maze.getCell(row, col + 1));
        }
        // нижний сосед
        if (!maze.getCell(row, col).isDownWall()) {
            nextCells.add(maze.getCell(row + 1, col));
        }
        // левый сосед
        if (col > 0 && !maze.getCell(row, col - 1).isRightWall()) {
            nextCells.add(maze.getCell(row, col - 1));
        }
        return nextCells;
    }
}
