package edu.hw2.task2;

public class Square extends Rectangle {

    @Override
    public Rectangle setWidth(int width) {
        if (this.width != 0) {
            Rectangle newRect = new Rectangle();
            newRect = newRect.setWidth(width);
            newRect = newRect.setHeight(height);
            return newRect;
        }
        super.setHeight(width);
        super.setWidth(width);
        return this;
    }

    @Override
    public Rectangle setHeight(int height) {
        if (this.height != 0) {
            Rectangle newRect = new Rectangle();
            newRect = newRect.setWidth(width);
            newRect = newRect.setHeight(height);
            return newRect;
        }
        super.setHeight(height);
        super.setWidth(height);
        return this;
    }
}



