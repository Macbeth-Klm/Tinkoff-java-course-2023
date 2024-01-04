package edu.project2.maze.rendering;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import java.util.List;

@SuppressWarnings("MagicNumber")
public class PrettyPrint implements Renderer {
    private static final String DOWN_WALL = "▃▃▃▃▃";
    private static final String SOLUTION_STEP = "\u001B[31m" + "●" + "\u001B[32m";

    private static final char RIGHT_WALL = '█';

    public PrettyPrint() {
    }

    @Override
    public String render(Maze maze) {
        var grid = maze.grid();
        StringBuilder renderedMaze = new StringBuilder((maze.height() + 2) * (maze.width() * 5) + 1);
        renderedMaze.append("\n").append(DOWN_WALL.repeat(maze.width())).append("\n");
        StringBuilder renderedCell = new StringBuilder(5);
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                renderedCell.append(" ".repeat(5));
                if (grid[i][j].isDownWall()) {
                    renderedCell.replace(0, renderedCell.length(), DOWN_WALL);
                }
                if (grid[i][j].isRightWall()) {
                    renderedCell.setCharAt(4, RIGHT_WALL);
                }
                if (j == 0) {
                    renderedCell.setCharAt(0, RIGHT_WALL);
                }
                renderedMaze.append(renderedCell);
                renderedCell.delete(0, renderedCell.length());
            }
            renderedMaze.append("\n");
        }
        return renderedMaze.toString();
    }

    @Override
    public String render(Maze maze, List<Cell> path) {
        var grid = maze.grid();
        StringBuilder renderedMaze = new StringBuilder((maze.height() + 2) * (maze.width() * 5) + 1);
        renderedMaze.append("\n").append(DOWN_WALL.repeat(maze.width())).append("\n");
        StringBuilder renderedCell = new StringBuilder(5);
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                renderedCell.append(" ".repeat(5));
                if (grid[i][j].isDownWall()) {
                    renderedCell.replace(0, renderedCell.length(), DOWN_WALL);
                }
                if (grid[i][j].isRightWall()) {
                    renderedCell.setCharAt(4, RIGHT_WALL);
                }
                if (j == 0) {
                    renderedCell.setCharAt(0, RIGHT_WALL);
                }
                if (path.contains(grid[i][j])) {
                    renderedCell.replace(2, 3, SOLUTION_STEP);
                }
                renderedMaze.append(renderedCell);
                renderedCell.delete(0, renderedCell.length());
            }
            renderedMaze.append("\n");
        }
        return renderedMaze.toString();
    }
}
