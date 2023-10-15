package edu.hw2.task2;

public class Square extends Rectangle {

    @Override
    public Rectangle setWidth(int width) {
        if (this.width != 0) {
            Rectangle newRect = new Rectangle();
            newRect.setWidth(width);
            newRect.setHeight(height);
            return newRect;
        }
        super.setWidth(width);
        super.setHeight(width);
        return this;
    }

    @Override
    public Rectangle setHeight(int height) {
        if (this.height != 0) {
            Rectangle newRect = new Rectangle();
            newRect.setWidth(width);
            newRect.setHeight(height);
            return newRect;
        }
        super.setWidth(height);
        super.setHeight(height);
        return this;
    }
}



