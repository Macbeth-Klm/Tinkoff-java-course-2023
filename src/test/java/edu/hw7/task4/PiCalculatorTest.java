package edu.hw7.task4;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PiCalculatorTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @ParameterizedTest
    @ValueSource(longs = {10_000_000L, 100_000_000L, 1_000_000_000L})
    void shouldCorrectCalculatePiWithSingleThread(long totalPoints) {
        double pi = PiCalculator.oneThreadApproximatePi(totalPoints);

        assertTrue(3.1 < pi && pi < 3.18);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void shouldCorrectCalculatePiWithSeveralThreads(int threadCount) {
        long totalPoints = 10_000_000L;

        double pi = PiCalculator.multiThreadApproximatePi(totalPoints, threadCount);

        assertTrue(3.1 < pi && pi < 3.18);
    }

    @Test
    void printAverageTimeAndAbsoluteError() {
        List<Long> totalPoints = List.of(10_000_000L, 100_000_000L, 1_000_000_000L);
        for (Long totalPoint : totalPoints) {
            List<Long> executionTime = new ArrayList<>();
            for (int i = 1; i <= 4; i++) {
                var startTime = System.nanoTime();
                double pi = PiCalculator.multiThreadApproximatePi(totalPoint, i);
                var endTime = System.nanoTime();
                executionTime.add((endTime - startTime) / 1_000_000);
                LOGGER.info(
                    "Уровень абс. погрешности для {} потоков и {} симуляций равен {}",
                    i,
                    totalPoint,
                    Math.abs(Math.PI - pi)
                );
            }
            long averageTime = 0;
            for (int i = 0; i < 2; i++) {
                averageTime += executionTime.get(i) - executionTime.get(i + 1);
            }
            averageTime = averageTime / 3;
            LOGGER.info("Среднее время уск-я решения" +
                " в зависимости от кол-ва потоков для {} симуляций равно {} мс", totalPoint, averageTime);
        }
    }
}
