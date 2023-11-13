package edu.project2.solution;

import edu.project2.Cell;
import edu.project2.Maze;
import java.util.List;

public interface Solver {
    List<Cell> solve(Maze maze, Cell start, Cell end);
}
