package edu.hw3.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.PriorityQueue;
import static org.assertj.core.api.Assertions.assertThat;

public class StockMarketTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -100})
    void shouldThrowExceptionBecauseOfIncorrectStockPrice(int price) {
        IllegalArgumentException incorrectPriceException =
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                Stock stock = new Stock(price);
            });
        Assertions.assertEquals("Цена акции некорректная!", incorrectPriceException.getMessage());
    }

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

    @Test
    void shouldThrowExceptionBecauseOfEmptyQueue() {
        DefaultStockMarket defaultStockMarket = new DefaultStockMarket();

        NullPointerException emptyQueueException =
            Assertions.assertThrows(NullPointerException.class, () -> {
                Stock mostValuableStock = defaultStockMarket.mostValuableStock();
            });
        Assertions.assertEquals("Акций на данный момент нет!", emptyQueueException.getMessage());
    }
}
