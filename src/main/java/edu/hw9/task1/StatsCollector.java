package edu.hw9.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
            var sum = Arrays.stream(inputData).sum();
            var average = Arrays.stream(inputData).average().getAsDouble();
            var max = Arrays.stream(inputData).max().getAsDouble();
            var min = Arrays.stream(inputData).min().getAsDouble();
            statistics.add(new Statistics(metricName, sum, average, max, min));
        });
    }

    public List<Statistics> stats() {
        return Collections.unmodifiableList(statistics);
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public void close() {
        threadPool.close();
    }

    public record Statistics(String metricName, double sum, double average, double max, double min) {
    }
}
