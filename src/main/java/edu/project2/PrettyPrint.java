package edu.project2;

import java.util.List;

@SuppressWarnings({"RegexpSinglelineJava", "MagicNumber"})
public final class PrettyPrint {
    /*
     * "\u001B[31m" - Красный цвет текста
     * "\u001B[0m" - ANSI reset
     */
    private static final String DOWN_WALL = "▃▃▃▃▃";
    private static final String SOLUTION_STEP = "\u001B[31m" + "●" + "\u001B[0m";
    private static final char RIGHT_WALL = '█';

    private PrettyPrint() {
    }

    public static void printMaze(Maze maze) {
        var grid = maze.grid();
        System.out.println(DOWN_WALL.repeat(maze.width()));
        StringBuilder sb = new StringBuilder(5); // Cell = 5 символов
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                sb.append(" ".repeat(5));
                if (grid[i][j].isDownWall()) {
                    sb.replace(0, sb.length(), DOWN_WALL);
                }
                if (grid[i][j].isRightWall()) {
                    sb.setCharAt(4, RIGHT_WALL);
                }
                if (j == 0) {
                    sb.setCharAt(0, RIGHT_WALL);
                }
                System.out.print(sb);
                sb.delete(0, sb.length());
            }
            System.out.println();
        }
    }

    public static void printSolution(Maze maze, List<Cell> solution) {
        var grid = maze.grid();
        System.out.println(DOWN_WALL.repeat(maze.width()));
        StringBuilder sb = new StringBuilder(5); // 5 символов может находиться в одном Cell
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                sb.append(" ".repeat(5));
                if (grid[i][j].isDownWall()) {
                    sb.replace(0, sb.length(), DOWN_WALL);
                }
                if (grid[i][j].isRightWall()) {
                    sb.setCharAt(4, RIGHT_WALL);
                }
                if (j == 0) {
                    sb.setCharAt(0, RIGHT_WALL);
                }
                if (solution.contains(grid[i][j])) {
                    sb.replace(2, 3, SOLUTION_STEP);
                }
                System.out.print(sb);
                sb.delete(0, sb.length());
            }
            System.out.println();
        }
    }
}
