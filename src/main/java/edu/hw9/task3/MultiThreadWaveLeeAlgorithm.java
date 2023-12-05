package edu.hw9.task3;

import edu.project2.Cell;
import edu.project2.Maze;
import edu.project2.solution.Solver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MultiThreadWaveLeeAlgorithm implements Solver {
    private Cell end;
    private Maze maze;
    private int[][] dist;
    private List<Cell> path;

    public MultiThreadWaveLeeAlgorithm() {
    }

    @Override
    public List<Cell> solve(Maze maze, Cell root, Cell end) {
        this.end = end;
        this.maze = maze;
        dist = new int[maze.height()][maze.width()];
        dist[root.getRow()][root.getCol()] = 1;
        path = new ArrayList<>();
        try (var forkJoinPool = new ForkJoinPool()) {
            forkJoinPool.invoke(new MazeTask(root));
            return path.reversed();
        }
    }

    private class MazeTask extends RecursiveAction {
        private final Cell currentCell;

        private MazeTask(Cell currentCell) {
            this.currentCell = currentCell;
        }

        @Override
        protected void compute() {
            if (currentCell == end) {
                path.add(currentCell);
            } else {
                int row = currentCell.getRow();
                int col = currentCell.getCol();
                // верхний сосед
                if (row > 0
                    && !maze.getCell(row - 1, col).isDownWall()
                    && dist[row - 1][col] == 0) {
                    dist[row - 1][col] = dist[row][col] + 1;
                    var task = new MazeTask(maze.getCell(row - 1, col));
                    task.fork();
                    task.join();
                }
                // правый сосед
                if (col + 1 < maze.width()
                    && !maze.getCell(row, col).isRightWall()
                    && dist[row][col + 1] == 0) {
                    dist[row][col + 1] = dist[row][col] + 1;
                    var task = new MazeTask(maze.getCell(row, col + 1));
                    task.fork();
                    task.join();
                }
                // нижний сосед
                if (!maze.getCell(row, col).isDownWall()
                    && dist[row + 1][col] == 0) {
                    dist[row + 1][col] = dist[row][col] + 1;
                    var task = new MazeTask(maze.getCell(row + 1, col));
                    task.fork();
                    task.join();
                }
                // левый сосед
                if (col > 0
                    && !maze.getCell(row, col - 1).isRightWall()
                    && dist[row][col - 1] == 0) {
                    dist[row][col - 1] = dist[row][col] + 1;
                    var task = new MazeTask(maze.getCell(row, col - 1));
                    task.fork();
                    task.join();
                }
                if (path.contains(end)) {
                    var lastAddedCell = path.getLast();
                    if (dist[currentCell.getRow()][currentCell.getCol()]
                        == dist[lastAddedCell.getRow()][lastAddedCell.getCol()] - 1) {
                        path.add(currentCell);
                    }
                }
            }
        }
    }
}
