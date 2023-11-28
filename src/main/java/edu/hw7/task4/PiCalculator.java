package edu.hw7.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public final class PiCalculator {
    private static final double CIRCLE_RADIUS = 1;

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
            var tasks = new ArrayList<>(Stream.generate(() -> calculateCirclePoints).limit(threadCount).toList());
            long remainingPoints = totalPoints % threadCount;
            if (remainingPoints != 0) {
                tasks.add(() -> getCirclePointsCount(remainingPoints));
            }
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
                ThreadLocalRandom.current().nextDouble(CIRCLE_RADIUS),
                ThreadLocalRandom.current().nextDouble(CIRCLE_RADIUS)
            );
            if (circleContainsPoint(point)) {
                result++;
            }
        }
        return result;
    }

    private static boolean circleContainsPoint(Point point) {
        return Math.pow(point.x(), 2) + Math.pow(point.y(), 2) <= CIRCLE_RADIUS;
    }

    private record Point(double x, double y) {
    }
}
