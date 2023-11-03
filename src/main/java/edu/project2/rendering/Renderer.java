package edu.project2.rendering;

import edu.project2.Cell;
import edu.project2.Maze;
import java.util.List;

public interface Renderer {
    String render(Maze maze);

    String render(Maze maze, List<Cell> path);
}
