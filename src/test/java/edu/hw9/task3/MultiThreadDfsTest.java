package edu.hw9.task3;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import edu.project2.maze.generation.EllerAlgorithm;
import edu.project2.maze.generation.Generator;
import edu.project2.maze.rendering.PrettyPrint;
import edu.project2.maze.rendering.Renderer;
import edu.project2.maze.solution.Solver;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MultiThreadDfsTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void shouldReturnCorrectPath() {
        Generator generator = new EllerAlgorithm(new Random(10));
        Maze maze = generator.generate(4, 5);
        Renderer renderer = new PrettyPrint();
        LOGGER.info(renderer.render(maze));
        Cell root = maze.getCell(0, 0);
        Cell goal = maze.getCell(1, 4);

        Solver solver = new MultiThreadDfs();
        var path = solver.solve(maze, root, goal);
        LOGGER.info(renderer.render(maze, path));

        assertThat(path)
            .containsExactly(
                maze.getCell(0, 0),
                maze.getCell(1, 0),
                maze.getCell(1, 1),
                maze.getCell(1, 2),
                maze.getCell(2, 2),
                maze.getCell(3, 2),
                maze.getCell(3, 3),
                maze.getCell(3, 4),
                maze.getCell(2, 4),
                maze.getCell(1, 4)
            );
    }

    @Test
    void shouldReturnEmptyPathInsteadOfPathBecauseOfLackOfSolutionBetweenGivenCells() {
        Generator generator = new EllerAlgorithm(new Random(10));
        Maze maze = generator.generate(4, 5);
        maze.getCell(3, 3).setRightWall(true); // Создаём изолированную область в идеальном лабиринте
        Renderer renderer = new PrettyPrint();
        LOGGER.info(renderer.render(maze));
        Cell root = maze.getCell(0, 0);
        Cell goal = maze.getCell(1, 4);

        Solver solver = new MultiThreadDfs();
        var path = solver.solve(maze, root, goal);

        assertThat(path)
            .isEmpty();
    }

    @Test
    void shouldThrowExceptionBecauseRootIsEqualToGoal() {
        Generator generator = new EllerAlgorithm(new Random(10));
        Maze maze = generator.generate(4, 5);
        Cell root = maze.getCell(0, 0);
        Cell goal = maze.getCell(0, 0);

        Solver solver = new MultiThreadDfs();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var path = solver.solve(maze, root, goal);
        });
    }
}
