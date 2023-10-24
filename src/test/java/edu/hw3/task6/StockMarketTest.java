package edu.hw3.task6;

import org.junit.jupiter.api.Test;
import java.util.PriorityQueue;
import static org.assertj.core.api.Assertions.assertThat;

public class StockMarketTest {

    @Test
    void shouldAddStockInStockMarket() {
        DefaultStockMarket stockMarket = new DefaultStockMarket();
        Stock stock = new Stock(1200);

        stockMarket.add(stock);

        assertThat(stockMarket.getPriorityQueueStock())
            .containsExactly(stock);
    }

    @Test
    void shouldRemoveStockFromMarketPlace() {
        DefaultStockMarket stockMarket = new DefaultStockMarket();
        Stock stock1 = new Stock(1200);
        Stock stock2 = new Stock(1500);
        Stock stock3 = new Stock(1000);
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);

        stockMarket.remove(stock1);

        assertThat(stockMarket.getPriorityQueueStock())
            .doesNotContain(stock1);
    }

    @Test
    void shouldReturnSortedQueueByPrice() {
        DefaultStockMarket stockMarket = new DefaultStockMarket();
        Stock stock1 = new Stock(1200);
        Stock stock2 = new Stock(1500);
        Stock stock3 = new Stock(1000);
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);

        PriorityQueue<Stock> priorityQueue = stockMarket.getPriorityQueueStock();

        assertThat(priorityQueue)
            .containsExactly(stock2, stock1, stock3);
    }

    @Test
    void shouldReturnMostValuableStock() {
        DefaultStockMarket stockMarket = new DefaultStockMarket();
        Stock stock1 = new Stock(1200);
        Stock stock2 = new Stock(1500);
        Stock stock3 = new Stock(1000);
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);

        Stock mostValuableStock = stockMarket.mostValuableStock();

        assertThat(mostValuableStock)
            .isEqualTo(stock2);
    }

}
