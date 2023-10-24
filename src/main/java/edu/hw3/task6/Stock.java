package edu.hw3.task6;

public class Stock {
    private int price;

    public Stock(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена акции некорректная!");
        }
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
