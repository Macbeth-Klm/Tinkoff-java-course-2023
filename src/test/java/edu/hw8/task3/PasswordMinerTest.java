package edu.hw8.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordMinerTest {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<String, String> MD5_TO_USER_MAP = Map.of(
        "80457cf3a7b15afb8f491f8ae06680db", "daniil vladimirovich",
        "3491f0dc1059a35bb1681b3bd67cb0d5", "macbeth",
        "30e5a336059982c76b6c214d3634c038", "somebody"
    );

    @Test
    void singleThreadVersion() {
        PasswordMiner passwordMiner = new PasswordMiner(
            MD5_TO_USER_MAP,
            4
        );

        ConcurrentHashMap<String, String> result = passwordMiner.singleThreadMining();

        assertThat(result).containsExactlyInAnyOrderEntriesOf(
            Map.of(
                "daniil vladimirovich", "dv",
                "macbeth", "klm",
                "somebody", "heyo"
            )
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void multiThreadVersion(int threadCount) {
        PasswordMiner passwordMiner = new PasswordMiner(
            MD5_TO_USER_MAP,
            4
        );

        ConcurrentHashMap<String, String> result = passwordMiner.multiThreadMining(threadCount);

        assertThat(result).containsExactlyInAnyOrderEntriesOf(
            Map.of(
                "daniil vladimirovich", "dv",
                "macbeth", "klm",
                "somebody", "heyo"
            )
        );
    }

    @Test
    void printAverageTimePerThreadCount() {
        List<Long> executionTime = new ArrayList<>();
        PasswordMiner passwordMiner = new PasswordMiner(MD5_TO_USER_MAP, 4);
        for (int i = 1; i <= 4; i++) {
            var startTime = System.nanoTime();
            ConcurrentHashMap<String, String> result = passwordMiner.multiThreadMining(i);
            var endTime = System.nanoTime();
            executionTime.add((endTime - startTime) / 1_000_000);
        }
        long averageTime = 0;
        for (int i = 0; i < 2; i++) {
            averageTime += executionTime.get(i) - executionTime.get(i + 1);
        }
        averageTime = averageTime / 3;
        LOGGER.info("Среднее время уск-я решения" +
            " в зависимости от кол-ва потоков равно {} мс", averageTime);
    }
}
