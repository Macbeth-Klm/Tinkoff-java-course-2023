package edu.project2;

import edu.project2.generation.EllerAlgorithm;
import edu.project2.generation.Generator;
import edu.project2.solution.LeeWaveAlgorithm;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner DEFAULT_SCANNER = new Scanner(System.in);
    private static final Random DEFAULT_RANDOM = new Random();

    private Main() {
    }

    public static void main(String[] args) {
        run(DEFAULT_SCANNER, DEFAULT_RANDOM);
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void run(Scanner scanner, Random random) {
        System.out.print("Введите высоту лабиринта: ");
        int height = scanner.nextInt();
        System.out.print("Введите ширину лабиринта: ");
        int width = scanner.nextInt();

        Generator generator = new EllerAlgorithm(random);
        Maze maze = generator.generate(height, width);
        PrettyPrint.printMaze(maze);

        System.out.print("Введите строку начальной точки: ");
        int row = scanner.nextInt();
        System.out.print("Введите столбец начальной точки: ");
        int col = scanner.nextInt();
        Cell rootCell = maze.getCell(row, col);

        System.out.print("Введите строку целевой точки: ");
        row = scanner.nextInt();
        System.out.print("Введите столбец целевой точки: ");
        col = scanner.nextInt();
        Cell goalCell = maze.getCell(row, col);

        LeeWaveAlgorithm leeWaveAlgorithm = new LeeWaveAlgorithm();
        List<Cell> solution = leeWaveAlgorithm.solve(maze, rootCell, goalCell);
        PrettyPrint.printSolution(maze, solution);
    }
}
