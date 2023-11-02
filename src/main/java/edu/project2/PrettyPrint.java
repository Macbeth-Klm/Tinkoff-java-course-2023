package edu.project2;

import edu.project2.generation.Maze;

public final class PrettyPrint {
    /*
     * "\u001B[42m" - Зелёный фон
     * "\u001B[40m" - Черный фон
     * "\u001B[47m" - Белый фон
     * "\u001B[30m" - Черный цвет
     * "\u001B[0m" - Reset
     */
    private static final String RESET = "\u001B[0m";
    private static final String GREEN_BACKGROUND = "\u001B[42m";
    private static final String BLACK_BACKGROUND = "\u001B[40m";
    private static final String RED_TEXT = "\u001B[31m";

    private PrettyPrint() {
    }
    public static void newPrettyPrint(Maze maze) {
        System.out.print(GREEN_BACKGROUND + "   " + RESET + "\n" + BLACK_BACKGROUND + "   " + RESET);
        System.out.println(GREEN_BACKGROUND + "   " + RESET);
    }

    public static void printMaze(Maze maze) {
        var grid = maze.getGrid();
        System.out.println("▃▃▃▃▃".repeat(maze.getWidth()));
        StringBuilder sb = new StringBuilder(Integer.MAX_VALUE >> 2);
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                sb.append("     ");
                if (grid[i][j].isDownWall()) {
                    sb = new StringBuilder("▃▃▃▃▃");
                }
                if (grid[i][j].isRightWall()) {
                    sb.setCharAt(4, '█');
                }
                if (j == 0) {
                    sb.setCharAt(0, '█');
                }
                System.out.print(sb);
                sb.delete(0, sb.length());
            }
            System.out.println();
        }
    }
}
