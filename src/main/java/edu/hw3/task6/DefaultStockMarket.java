package edu.hw3.task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class DefaultStockMarket implements StockMarket {
    private final PriorityQueue<Stock> priorityQueueStock;

    /** Акции расположены в порядке убывания цены */
    public DefaultStockMarket() {
        priorityQueueStock = new PriorityQueue<>(Comparator.comparingInt(Stock::getPrice).reversed());
    }

    @Override
    public void add(Stock stock) {
        priorityQueueStock.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        priorityQueueStock.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        if (priorityQueueStock.isEmpty()) {
            throw new NullPointerException("Акций на данный момент нет!");
        }
        return priorityQueueStock.peek();
    }

    public PriorityQueue<Stock> getPriorityQueueStock() {
        return priorityQueueStock;
    }
}
