package edu.project2.maze.generation;

import edu.project2.maze.Maze;

public interface Generator {
    Maze generate(int height, int width);
}
