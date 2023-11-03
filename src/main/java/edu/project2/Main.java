package edu.project2;

import edu.project2.generation.EllerAlgorithm;
import edu.project2.generation.Generator;
import edu.project2.rendering.PrettyPrint;
import edu.project2.rendering.Renderer;
import edu.project2.solution.LeeWaveAlgorithm;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Scanner DEFAULT_SCANNER = new Scanner(System.in);
    private static final Random DEFAULT_RANDOM = new Random();
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        run(DEFAULT_SCANNER, DEFAULT_RANDOM);
    }

    public static void run(Scanner scanner, Random random) {
        LOGGER.info("Введите высоту лабиринта: ");
        int height = scanner.nextInt();
        LOGGER.info("Введите ширину лабиринта: ");
        int width = scanner.nextInt();

        Generator generator = new EllerAlgorithm(random);
        Renderer renderer = new PrettyPrint();
        Maze maze = generator.generate(height, width);
        LOGGER.info(renderer.render(maze));

        LOGGER.info("Введите строку начальной точки: ");
        int row = scanner.nextInt();
        LOGGER.info("Введите столбец начальной точки: ");
        int col = scanner.nextInt();
        Cell rootCell = maze.getCell(row - 1, col - 1);

        LOGGER.info("Введите строку целевой точки: ");
        row = scanner.nextInt();
        LOGGER.info("Введите столбец целевой точки: ");
        col = scanner.nextInt();
        Cell goalCell = maze.getCell(row - 1, col - 1);

        LeeWaveAlgorithm leeWaveAlgorithm = new LeeWaveAlgorithm();
        List<Cell> solution = leeWaveAlgorithm.solve(maze, rootCell, goalCell);
        LOGGER.info(renderer.render(maze, solution));

    }
}
