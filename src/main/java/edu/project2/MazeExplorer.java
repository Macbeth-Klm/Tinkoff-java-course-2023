package edu.project2;

import edu.project2.generation.EllerAlgorithm;
import edu.project2.generation.Generator;
import edu.project2.rendering.PrettyPrint;
import edu.project2.rendering.Renderer;
import edu.project2.solution.LeeWaveAlgorithm;
import edu.project2.solution.Solver;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeExplorer {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Scanner scanner;
    private final Maze maze;
    private List<Cell> path;

    public MazeExplorer(Scanner scanner, Random random) {
        this.scanner = scanner;
        LOGGER.info("Введите высоту лабиринта (минимальная высота равна 2): ");
        int height = scanner.nextInt();
        LOGGER.info("Введите ширину лабиринта (минимальная ширина равна 2): ");
        int width = scanner.nextInt();

        // Добавить выбор алгоритма генерации

        if (height < 2 || width < 2) {
            throw new IllegalArgumentException("Неверно задан размер лабиринта!");
        }
        Generator generator = new EllerAlgorithm(random);
        maze = generator.generate(height, width);
    }

    public void run() {
        Renderer renderer = new PrettyPrint();
        LOGGER.info(renderer.render(maze));

        LOGGER.info("Введите строку начальной точки: ");
        int row = scanner.nextInt();
        LOGGER.info("Введите столбец начальной точки: ");
        int col = scanner.nextInt();
        Cell root = maze.getCell(row - 1, col - 1);

        // Добавить выбор алгоритма решения

        LOGGER.info("Введите строку целевой точки: ");
        row = scanner.nextInt();
        LOGGER.info("Введите столбец целевой точки: ");
        col = scanner.nextInt();
        Cell goal = maze.getCell(row - 1, col - 1);

        Solver solver = new LeeWaveAlgorithm();
        path = solver.solve(maze, root, goal);
        LOGGER.info(renderer.render(maze, path));
    }

    public Maze getMaze() {
        return maze;
    }

    public List<Cell> getPath() {
        return path;
    }
}
