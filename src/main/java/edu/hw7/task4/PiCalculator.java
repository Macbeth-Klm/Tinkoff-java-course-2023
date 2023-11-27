package edu.hw7.task4;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public final class PiCalculator {
    private static final Point CIRCLE_CENTER = new Point(1, 1);
    private static final double CIRCLE_RADIUS = 1;
    private static final double SQUARE_SIZE = 2;

    private PiCalculator() {
    }

    @SuppressWarnings("MagicNumber")
    public static double oneThreadApproximatePi(long totalPoints) {
        long circlePoints = getCirclePointsCount(totalPoints);
        return 4 * ((double) circlePoints / totalPoints);
    }

    @SuppressWarnings("MagicNumber")
    public static double multiThreadApproximatePi(long totalPoints, int threadCount) {
        long threadTotalPoint = totalPoints / threadCount;
        long circlePoints;
        try (var executorService = Executors.newFixedThreadPool(threadCount)) {
            Callable<Long> calculateCirclePoints = () -> getCirclePointsCount(threadTotalPoint);
            var tasks = Stream.generate(() -> calculateCirclePoints).limit(threadCount).toList();
            List<Future<Long>> futuresCirclePoints = executorService.invokeAll(tasks);
            circlePoints = futuresCirclePoints.stream().map(f -> {
                try {
                    return f.get();
                } catch (Exception e) {
                    throw new RuntimeException("Error while get value from the Future objects!");
                }
            }).reduce(Long::sum).orElse(0L);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error while threads execute tasks!");
        }
        return 4 * ((double) circlePoints / totalPoints);
    }

    private static long getCirclePointsCount(long totalPoints) {
        long result = 0;
        for (long i = 0; i < totalPoints; i++) {
            Point point = new Point(
                ThreadLocalRandom.current().nextDouble(SQUARE_SIZE),
                ThreadLocalRandom.current().nextDouble(SQUARE_SIZE)
            );
            if (circleContainsPoint(point)) {
                result++;
            }
        }
        return result;
    }

    private static boolean circleContainsPoint(Point point) {
        double d = Math.sqrt(
            Math.pow(point.x() - CIRCLE_CENTER.x(), 2) + Math.pow(point.y() - CIRCLE_CENTER.y(), 2)
        );
        return d <= CIRCLE_RADIUS;
    }

    private record Point(double x, double y) {
    }
}
