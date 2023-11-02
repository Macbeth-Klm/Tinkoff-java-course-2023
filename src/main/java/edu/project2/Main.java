package edu.project2;

import edu.project2.generation.EllerAlgorithm;
import edu.project2.generation.Generator;
import edu.project2.generation.Maze;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner DEFAULT_SCANNER = new Scanner(System.in);
    private static final Random DEFAULT_RANDOM = new Random();
    public static void main(String[] args) {
        run(DEFAULT_SCANNER, DEFAULT_RANDOM);
    }
    public static void run(Scanner scanner, Random random) {
        System.out.print("Введите высоту лабиринта: ");
        int height = scanner.nextInt();
        System.out.print("Введите ширину лабиринта: ");
        int width = scanner.nextInt();
        Generator generator = new EllerAlgorithm(random);
        Maze maze = generator.generate(height, width);
        PrettyPrint.printMaze(maze);
    }
}
