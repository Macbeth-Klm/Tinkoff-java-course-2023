package edu.hw9.task1;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StatsCollectorTest {
    @Test
    void shouldReturnStatisticsWhenOneProducer() {
        try (var statsCollector = new StatsCollector(2)) {
            Thread producer = new Thread(() -> statsCollector.push("sum", new double[] {1, 2, 3, 4}));
            producer.start();
            producer.join();
            assertThat(statsCollector.stats())
                .containsExactlyInAnyOrder(
                    new StatsCollector.Statistics("sum", 10)
                );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldReturnStatisticsWhenThereIsSeveralProducers() {
        var countDownLatch = new CountDownLatch(2);
        List<StatsCollector.Statistics> result;

        try (
            var statsCollector = new StatsCollector(2);
            ExecutorService producers = Executors.newFixedThreadPool(2)
        ) {
            Runnable firstProducerTasks = () -> {
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                statsCollector.push("sum", new double[] {1, 2, 3, 4});
                statsCollector.push("average", new double[] {1, 2, 3, 4});
                statsCollector.push("sum", new double[] {3, 5, 8, 4});
            };
            Runnable secondProducerTasks = () -> {
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                statsCollector.push("min", new double[] {5, 6, 7, 4, 8});
                statsCollector.push("average", new double[] {3, 5, 8, 4});
                statsCollector.push("max", new double[] {1, 2, 10, 4, 5, 21});
                statsCollector.push("sum", new double[] {1, 2, 3, 4});
            };
            producers.execute(firstProducerTasks);
            producers.execute(secondProducerTasks);

            result = statsCollector.stats();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertThat(result)
            .containsExactlyInAnyOrder(
                new StatsCollector.Statistics("sum", 10),
                new StatsCollector.Statistics("average", 2.5),
                new StatsCollector.Statistics("sum", 20),
                new StatsCollector.Statistics("min", 4),
                new StatsCollector.Statistics("average", 5),
                new StatsCollector.Statistics("max", 21),
                new StatsCollector.Statistics("sum", 10)
            );
    }
}
