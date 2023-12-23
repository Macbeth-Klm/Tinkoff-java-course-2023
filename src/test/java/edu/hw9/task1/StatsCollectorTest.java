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
        List<StatsCollector.Statistics> result;

        try (var statsCollector = new StatsCollector(2)) {
            Thread producer = new Thread(() -> statsCollector.push("metric", new double[] {1, 2, 3, 4}));
            producer.start();
            producer.join();
            result = statsCollector.stats();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertThat(result)
            .containsExactlyInAnyOrder(
                new StatsCollector.Statistics("metric", 10, 2.5, 4, 1)
            );
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
                statsCollector.push("metric1", new double[] {1, 2, 3, 4});
                statsCollector.push("metric2", new double[] {1, 2, 3, 4});
                statsCollector.push("metric3", new double[] {3, 5, 8, 4});
            };
            Runnable secondProducerTasks = () -> {
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                statsCollector.push("metric4", new double[] {5, 6, 7, 4, 8});
                statsCollector.push("metric5", new double[] {3, 5, 8, 4});
                statsCollector.push("metric6", new double[] {1, 2, 10, 4, 5, 20});
                statsCollector.push("metric7", new double[] {1, 2, 3, 4});
            };
            producers.execute(firstProducerTasks);
            producers.execute(secondProducerTasks);
            result = statsCollector.stats();
        }

        assertThat(result)
            .containsExactlyInAnyOrder(
                new StatsCollector.Statistics("metric1", 10, 2.5, 4, 1),
                new StatsCollector.Statistics("metric2", 10, 2.5, 4, 1),
                new StatsCollector.Statistics("metric3", 20, 5, 8, 3),
                new StatsCollector.Statistics("metric4", 30, 6, 8, 4),
                new StatsCollector.Statistics("metric5", 20, 5, 8, 3),
                new StatsCollector.Statistics("metric6", 42, 7, 20, 1),
                new StatsCollector.Statistics("metric7", 10, 2.5, 4, 1)
            );
    }
}
