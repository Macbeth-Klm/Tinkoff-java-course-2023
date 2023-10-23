package edu.hw3.task6;

import java.util.PriorityQueue;

public class StockMarketPlace implements StockMarket {
    private PriorityQueue<Stock> priorityQueueStock;

    public StockMarketPlace() {
        /**Акции расположены в порядке убывания цены*/
        priorityQueueStock = new PriorityQueue<>((o1, o2) -> (-1) * Integer.compare(o1.price(), o2.price()));
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
