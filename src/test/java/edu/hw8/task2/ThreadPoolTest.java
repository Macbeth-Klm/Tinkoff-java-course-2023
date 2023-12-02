package edu.hw8.task2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ThreadPoolTest {
    @Test
    void shouldCorrectCalculateFibonacciNumbers() throws Exception {
        List<Integer> result = new CopyOnWriteArrayList<>();

        try (ThreadPool threadPool = new FixedThreadPool(4)) {
            threadPool.execute(() -> result.add(FibonacciNumbers.calculate(1)));
            threadPool.execute(() -> result.add(FibonacciNumbers.calculate(2)));
            threadPool.execute(() -> result.add(FibonacciNumbers.calculate(3)));
            threadPool.execute(() -> result.add(FibonacciNumbers.calculate(4)));
            threadPool.execute(() -> result.add(FibonacciNumbers.calculate(5)));
            threadPool.execute(() -> result.add(FibonacciNumbers.calculate(6)));
            threadPool.execute(() -> result.add(FibonacciNumbers.calculate(7)));
            threadPool.execute(() -> result.add(FibonacciNumbers.calculate(40)));
            threadPool.start();
            threadPool.shutdown();

            assertThat(result)
                .containsExactlyInAnyOrder(2, 1, 1, 5, 3, 8, 13, 102334155);
        }
    }
}
