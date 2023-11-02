package edu.project2.generation;

public class Cell {
    private int row;
    private int col;
    private boolean rightWall;
    private boolean downWall;

    public Cell(int row, int col, boolean rightWall, boolean downWall) {
        this.row = row;
        this.col = col;
        this.rightWall = rightWall;
        this.downWall = downWall;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isRightWall() {
        return rightWall;
    }

    public void setRightWall(boolean rightWall) {
        this.rightWall = rightWall;
    }

    public boolean isDownWall() {
        return downWall;
    }

    public void setDownWall(boolean downWall) {
        this.downWall = downWall;
    }
}
