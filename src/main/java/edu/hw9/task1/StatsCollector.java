package edu.hw9.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StatsCollector implements AutoCloseable {
    private final ExecutorService threadPool;
    private final List<Statistics> statistics;

    public StatsCollector(int threadsCount) {
        threadPool = Executors.newFixedThreadPool(threadsCount);
        statistics = Collections.synchronizedList(new ArrayList<>());
    }

    public void push(String metricName, double[] inputData) {
        if (inputData.length == 0) {
            throw new IllegalArgumentException("Empty input data!");
        }
        threadPool.execute(() -> {
            double value = switch (metricName) {
                case "sum" -> Arrays.stream(inputData).sum();
                case "average" -> Arrays.stream(inputData).average().getAsDouble();
                case "max" -> Arrays.stream(inputData).max().getAsDouble();
                case "min" -> Arrays.stream(inputData).min().getAsDouble();
                default -> throw new IllegalArgumentException("Such metric does not exist!");
            };
            statistics.add(new Statistics(metricName, value));
        });
    }

    public List<Statistics> stats() {
        return statistics;
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public void close() throws InterruptedException {
        threadPool.shutdown();
        threadPool.awaitTermination(5000, TimeUnit.MILLISECONDS);
    }

    public record Statistics(String metricName, double value) {
    }
}
