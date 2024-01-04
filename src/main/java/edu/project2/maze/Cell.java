package edu.project2.maze;

public class Cell {
    private final int row;
    private final int col;
    private boolean rightWall;
    private boolean downWall;

    public Cell(int row, int col, boolean rightWall, boolean downWall) {
        this.row = row;
        this.col = col;
        this.rightWall = rightWall;
        this.downWall = downWall;
    }

    public void setRightWall(boolean rightWall) {
        this.rightWall = rightWall;
    }

    public void setDownWall(boolean downWall) {
        this.downWall = downWall;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isRightWall() {
        return rightWall;
    }

    public boolean isDownWall() {
        return downWall;
    }
}
