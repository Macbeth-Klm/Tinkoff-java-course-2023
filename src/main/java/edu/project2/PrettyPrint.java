package edu.project2;

import edu.project2.generation.Maze;

public final class PrettyPrint {
    /*
     * "\u001B[40m" - Черный фон
     * "\u001B[47m" - Белый фон
     * "\u001B[30m" - Черный цвет
     * "\u001B[0m" - Reset
     */
    private PrettyPrint() {
    }

    public static void printMaze(Maze maze) {
        var grid = maze.getGrid();
        System.out.println("---".repeat(maze.getWidth()));
        StringBuilder sb = new StringBuilder(Integer.MAX_VALUE >> 1);
        for (int i = 0; i < maze.getHeight(); i ++) {
            for (int j = 0; j < maze.getWidth(); j ++) {
                sb.append("   ");
                if (grid[i][j].isDownWall()) {
                    sb.replace(0, sb.length(),"___");
                }
                if (grid[i][j].isRightWall()) {
                    sb.setCharAt(2, '|');
                }
                if (j == 0) {
                    sb.setCharAt(0, '|');
                }
                System.out.print(sb);
                sb.delete(0, sb.length());
            }
            System.out.println();
        }
    }
}
