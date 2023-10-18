package edu.hw2.task3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class PopularCommandExecutorTest {
    static Arguments[] connectionManagers() {
        /*
        new Random(-9).nextInt(2): {0, 0, 1, 0, 0}
        new Random(-15).nextInt(2): {0, 1, 0, 1, 1}
         */
        return new Arguments[] {
            Arguments.of(new DefaultConnectionManager(new Random(-1), new Random(-9))),
            Arguments.of(new FaultyConnectionManager(new Random(-15)))
        };
    }

    @ParameterizedTest
    @MethodSource("connectionManagers")
    void checkingUpdatePackages(ConnectionManager manager) {
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, 3);
        assertDoesNotThrow(executor::updatePackages);
    }

    @Test
    void checkingSuccessTryExecute() {
        // new Random(-10).nextInt(2): {0, 0, 0, 0, 1}
        ConnectionManager connectionManager = new DefaultConnectionManager(new Random(-1), new Random(-10));
        PopularCommandExecutor executor = new PopularCommandExecutor(connectionManager, 5);
        assertDoesNotThrow(() -> executor.tryExecute("git init"));
    }

    @Test
    void checkingFailTryExecute() {
        // new Random(-10).nextInt(2): {0, 0, 0, 0, 1}
        ConnectionManager connectionManager = new FaultyConnectionManager(new Random(-10));
        PopularCommandExecutor executor = new PopularCommandExecutor(connectionManager, 4);
        assertThrows(ConnectionException.class, () -> executor.tryExecute("git init"));
    }
}
