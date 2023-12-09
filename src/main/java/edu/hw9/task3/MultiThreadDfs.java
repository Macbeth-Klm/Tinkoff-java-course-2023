package edu.hw9.task3;

import edu.project2.Cell;
import edu.project2.Maze;
import edu.project2.solution.Solver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MultiThreadDfs implements Solver {
    private Cell end;
    private Maze maze;
    private boolean[][] visited;

    public MultiThreadDfs() {
    }

    @Override
    public List<Cell> solve(Maze maze, Cell root, Cell end) {
        if (root == end) {
            throw new IllegalArgumentException("root is equal to end!");
        }
        this.end = end;
        this.maze = maze;
        visited = new boolean[maze.height()][maze.width()];
        try (var forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(new MazeTask(root)).reversed();
        }
    }

    private class MazeTask extends RecursiveTask<List<Cell>> {
        private final Cell cell;

        private MazeTask(Cell cell) {
            this.cell = cell;
        }

        @Override
        protected List<Cell> compute() {
            List<Cell> localPath = new ArrayList<>();
            if (cell == end) {
                localPath.add(cell);
                return localPath;
            }
            visited[cell.getRow()][cell.getCol()] = true;
            List<Cell> nextCells = nextEnableCells(maze, cell.getRow(), cell.getCol());
            List<MazeTask> tasks = new ArrayList<>();
            nextCells.stream()
                .filter(c -> !visited[c.getRow()][c.getCol()])
                .forEach(c -> {
                    var task = new MazeTask(c);
                    task.fork();
                    tasks.add(task);
                });
            for (var task : tasks) {
                var previousPath = task.join();
                if (previousPath.contains(end)) {
                    localPath.addAll(previousPath);
                    localPath.add(cell);
                    break;
                }
            }
            return localPath;
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
}
