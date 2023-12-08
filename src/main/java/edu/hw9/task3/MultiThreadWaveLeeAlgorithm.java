package edu.hw9.task3;

import edu.project2.Cell;
import edu.project2.Maze;
import edu.project2.solution.Solver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class MultiThreadWaveLeeAlgorithm implements Solver {
    private Maze maze;
    private int[][] dist;
    private final BlockingQueue<Cell> queue = new LinkedBlockingQueue<>();

    public MultiThreadWaveLeeAlgorithm() {
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public List<Cell> solve(Maze maze, Cell root, Cell end) {
        if (root == end) {
            return null;
        }
        if (!maze.isPerfect()) {
            return null;
        }
        this.maze = maze;
        dist = new int[maze.height()][maze.width()];
        dist[root.getRow()][root.getCol()] = 1;
        try (var threadPool = Executors.newFixedThreadPool(4)) {
            queue.put(root);
            Cell cell;
            do {
                cell = queue.take();
                var task = new SolverTask(cell);
                threadPool.execute(task);
            } while (dist[end.getRow()][end.getCol()] == 0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Cell> path = new ArrayList<>();
        Cell curCell = end;
        while (curCell != root) {
            path.add(curCell);
            List<Cell> nextCells = nextEnableCells(maze, curCell.getRow(), curCell.getCol());
            for (Cell cell : nextCells) {
                if (dist[cell.getRow()][cell.getCol()] + 1 == dist[curCell.getRow()][curCell.getCol()]) {
                    curCell = cell;
                    break;
                }
            }
        }
        path.add(root);
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

    private class SolverTask implements Runnable {
        private final Cell cell;

        private SolverTask(Cell cell) {
            this.cell = cell;
        }

        @Override
        public void run() {
            List<Cell> cells = nextEnableCells(maze, cell.getRow(), cell.getCol());
            cells.stream()
                .filter(c -> dist[c.getRow()][c.getCol()] == 0)
                .forEach(c -> {
                    dist[c.getRow()][c.getCol()] = dist[cell.getRow()][cell.getCol()] + 1;
                    try {
                        queue.put(c);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
        }
    }
}
