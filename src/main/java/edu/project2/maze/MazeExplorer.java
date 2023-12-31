package edu.project2.maze;

import edu.project2.maze.generation.BinaryTreeAlgorithm;
import edu.project2.maze.generation.EllerAlgorithm;
import edu.project2.maze.generation.Generator;
import edu.project2.maze.rendering.PrettyPrint;
import edu.project2.maze.rendering.Renderer;
import edu.project2.maze.solution.LeeWaveAlgorithm;
import edu.project2.maze.solution.Solver;
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
        if (height < 2 || width < 2) {
            throw new IllegalArgumentException("Неверно задан размер лабиринта!");
        }
        LOGGER.info("Введите номер метода генерации (1 - алгоритм бинарного дерева, 2 - алгоритм Эллера): ");
        int method = scanner.nextInt();
        Generator generator;
        if (method == 1) {
            generator = new BinaryTreeAlgorithm(random);
        } else if (method == 2) {
            generator = new EllerAlgorithm(random);
        } else {
            throw new IllegalArgumentException("Неверно задан метод генерации!");
        }
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
        LOGGER.info("Введите строку целевой точки: ");
        row = scanner.nextInt();
        LOGGER.info("Введите столбец целевой точки: ");
        col = scanner.nextInt();
        Cell goal = maze.getCell(row - 1, col - 1);

        Solver solver = new LeeWaveAlgorithm();
        path = solver.solve(maze, root, goal);
        if (path != null) {
            LOGGER.info(renderer.render(maze, path));
        } else {
            LOGGER.info("Данный лабиринт не имеет решения между данными координатами!");
        }
    }

    public Maze getMaze() {
        return maze;
    }

    public List<Cell> getPath() {
        return path;
    }
}
