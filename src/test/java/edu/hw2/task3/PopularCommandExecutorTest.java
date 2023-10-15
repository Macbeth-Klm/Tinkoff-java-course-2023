package edu.hw2.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PopularCommandExecutorTest {
    /*
    Test1: DefaultConnectionManager(probability > 0.3) + StableConnection
    Test2: DefaultConnectionManager(probability <= 0.3) + FaultyConnection(probability > 0.5) + success
    Test3: DefaultConnectionManager(probability <= 0.3) + FaultyConnection(probability <= 0.5) + fail
    Test4: FaultyConnectionManager + FaultyConnection(probability > 0.5) + success
    Test5: FaultyConnectionManager + FaultyConnection(probability <= 0.5) + fail
     */
    @Test
    void updatePackagesFirst() {
        ConnectionManager defaultManager = new DefaultConnectionManager(0.31); //StableConnection
        PopularCommandExecutor executor = new PopularCommandExecutor(defaultManager, 5);
        assertDoesNotThrow(() -> executor.updatePackages(0.3));
    }

    @Test
    void updatePackagesSecond() {
        ConnectionManager defaultManager = new DefaultConnectionManager(0.29); //FaultyConnection
        PopularCommandExecutor executor = new PopularCommandExecutor(defaultManager, 5);
        assertDoesNotThrow(() -> executor.updatePackages(0.3));
    }

    @Test
    void updatePackagesThird() {
        ConnectionManager defaultManager = new DefaultConnectionManager(0.29); //FaultyConnection
        PopularCommandExecutor executor = new PopularCommandExecutor(defaultManager, 2);
        Assertions.assertThrows(ConnectionException.class, () -> executor.updatePackages(0.1));
    }

    @Test
    void updatePackagesFourth() {
        ConnectionManager faultyManager = new FaultyConnectionManager(); //FaultyConnection
        PopularCommandExecutor executor = new PopularCommandExecutor(faultyManager, 2);
        assertDoesNotThrow(() -> executor.updatePackages(0.45));
    }

    @Test
    void updatePackagesFifth() {
        ConnectionManager faultyManager = new FaultyConnectionManager(); //FaultyConnection
        PopularCommandExecutor executor = new PopularCommandExecutor(faultyManager, 3);
        Assertions.assertThrows(ConnectionException.class, () -> executor.updatePackages(0.15));
    }
}
