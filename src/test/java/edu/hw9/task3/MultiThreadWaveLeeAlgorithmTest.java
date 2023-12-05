package edu.hw9.task3;

import edu.project2.Cell;
import edu.project2.Maze;
import edu.project2.generation.EllerAlgorithm;
import edu.project2.generation.Generator;
import edu.project2.rendering.PrettyPrint;
import edu.project2.rendering.Renderer;
import edu.project2.solution.Solver;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MultiThreadWaveLeeAlgorithmTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void shouldReturnCorrectPath() {
        Generator generator = new EllerAlgorithm(new Random(10));
        Maze maze = generator.generate(4, 5);
        Renderer renderer = new PrettyPrint();
        LOGGER.info(renderer.render(maze));

        Cell root = maze.getCell(0, 0);
        LOGGER.info("Введите строку целевой точки: ");
        Cell goal = maze.getCell(1, 4);

        Solver solver = new MultiThreadWaveLeeAlgorithm();

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
        Renderer renderer = new PrettyPrint();
        LOGGER.info(renderer.render(maze));
        maze.getCell(3, 3).setRightWall(true); // Создаём изолированную область в идеальном лабиринте

        Cell root = maze.getCell(0, 0);
        LOGGER.info("Введите строку целевой точки: ");
        Cell goal = maze.getCell(1, 4);

        Solver solver = new MultiThreadWaveLeeAlgorithm();

        var path = solver.solve(maze, root, goal);

        assertThat(path)
            .isEmpty();
    }
}
