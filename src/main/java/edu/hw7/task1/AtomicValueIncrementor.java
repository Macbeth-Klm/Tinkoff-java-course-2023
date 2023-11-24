package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class AtomicValueIncrementor {
    private static final Logger LOGGER = LogManager.getLogger();

    private AtomicValueIncrementor() {
    }

    public static int incrementValue(int startValue, int finalValue) {
        AtomicInteger value = new AtomicInteger(startValue);
        int firstThreadIterationCount = finalValue / 2;
        int secondThreadIterationCount = (finalValue % 2 == 0) ? finalValue / 2 : firstThreadIterationCount + 1;
        Thread firstThread = new Thread(() -> {
            for (int i = 0; i < firstThreadIterationCount; i++) {
                value.incrementAndGet();
            }
        });
        Thread secondThread = new Thread(() -> {
            for (int i = 0; i < secondThreadIterationCount; i++) {
                value.incrementAndGet();
            }
        });
        firstThread.start();
        secondThread.start();
        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            LOGGER.error("Error while joing threads", e);
            throw new RuntimeException(e);
        }
        return value.get();
    }
}
