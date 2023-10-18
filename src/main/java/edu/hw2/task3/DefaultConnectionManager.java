package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final int RANDOM_BOUND = 2; // 0 - FaultyConnection; 1 - StableConnection
    private final Random connectionManagerRandom;
    private final Random connectionRandom;

    public DefaultConnectionManager() {
        connectionManagerRandom = new Random();
        connectionRandom = new Random();
    }

    public DefaultConnectionManager(Random connectionManagerRandom, Random connectionRandom) {
        this.connectionManagerRandom = connectionManagerRandom;
        this.connectionRandom = connectionRandom;
    }

    @Override
    public Connection getConnection() {
        return (connectionManagerRandom.nextInt(RANDOM_BOUND) == 0)
            ? new FaultyConnection(connectionRandom)
            : new StableConnection();
    }
}
