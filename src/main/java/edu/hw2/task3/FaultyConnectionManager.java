package edu.hw2.task3;

import java.util.Random;

public class FaultyConnectionManager implements ConnectionManager {
    private final Random connectionProbability;

    public FaultyConnectionManager() {
        connectionProbability = new Random();
    }

    public FaultyConnectionManager(Random connectionProbability) {
        this.connectionProbability = connectionProbability;
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection(connectionProbability);
    }
}
