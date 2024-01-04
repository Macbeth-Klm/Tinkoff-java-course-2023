package edu.project2.maze.rendering;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import java.util.List;

public interface Renderer {
    String render(Maze maze);

    String render(Maze maze, List<Cell> path);
}
