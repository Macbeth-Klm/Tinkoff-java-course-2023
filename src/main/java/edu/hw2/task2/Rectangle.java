package edu.hw2.task2;

public class Rectangle {
    protected int width;
    protected int height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Неправильно введены стороны!");
        }
        this.width = width;
        this.height = height;
    }

    public Rectangle setWidth(int width) {
        if (width < 0) {
            throw new IllegalArgumentException("Ширина не может быть отрицательной!");
        }
        this.width = width;
        return this;
    }

    public Rectangle setHeight(int height) {
        if (height < 0) {
            throw new IllegalArgumentException("Высота не может быть отрицательной!");
        }
        this.height = height;
        return this;
    }

    public double area() {
        return width * height;
    }
}
