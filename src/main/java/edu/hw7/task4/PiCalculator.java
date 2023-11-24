package edu.hw7.task4;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public final class PiCalculator {
    private static final Point CIRCLE_CENTER = new Point(1, 1);
    private static final double CIRCLE_RADIUS = 1;
    private static final double SQUARE_SIZE = 2;

    private record Point(double x, double y) {
    }

    private static class PiCalculatorThread extends Thread {
        private long circlePoints;
        private final long totalPoints;

        public PiCalculatorThread(long totalPoints) {
            this.totalPoints = totalPoints;
        }

        @Override
        public void run() {
            circlePoints = getCirclePointsCount(totalPoints);
        }

        public long getCirclePoints() {
            return circlePoints;
        }
    }

    private PiCalculator() {
    }

    public static double oneThreadApproximatePi(long totalPoints) {
        long circlePoints = getCirclePointsCount(totalPoints);
        return 4 * ((double) circlePoints / totalPoints);
    }

    public static double multiThreadApproximatePi(long totalPoints, int threadCount) {
        long threadTotalPoint = totalPoints / threadCount;
        PiCalculatorThread[] threads = new PiCalculatorThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new PiCalculatorThread(threadTotalPoint);
            threads[i].start();
        }
        try {
            for (int i = 0; i < threadCount; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Error while joing threads", e);
        }
        long circlePoints = Arrays.stream(threads)
            .map(PiCalculatorThread::getCirclePoints)
            .reduce(Long::sum).orElse(0L);
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
}
