package edu.project2;

import edu.project2.generation.EllerAlgorithm;
import edu.project2.generation.Generator;
import edu.project2.generation.Maze;

public class Main {
    public static void main(String[] args) {
        Generator generator = new EllerAlgorithm();
        Maze maze = generator.generate(14, 15);
        PrettyPrint.printMaze(maze);
    }
}
