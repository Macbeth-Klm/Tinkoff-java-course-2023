package edu.hw2.task2;

public class Rectangle {
    protected final int width;
    protected final int height;

    public Rectangle(int width, int height) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Неверно заданы размеры!");
        }
        this.height = height;
        this.width = width;
    }

    public Rectangle setWidth(int width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Ширина не может быть отрицательной!");
        }
        return new Rectangle(width, height);
    }

    public Rectangle setHeight(int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Высота не может быть отрицательной!");
        }
        return new Rectangle(width, height);
    }

    public double area() {
        return width * height;
    }
}
