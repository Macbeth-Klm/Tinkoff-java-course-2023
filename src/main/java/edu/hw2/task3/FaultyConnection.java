package edu.hw2.task3;

import java.util.Random;

public class FaultyConnection implements Connection {
    private static final int RANDOM_BOUND = 2; // 0 - ConnectionException; 1 - command execution
    private final Random connectionRandom;

    public FaultyConnection() {
        connectionRandom = new Random();
    }

    public FaultyConnection(Random connectionRandom) {
        this.connectionRandom = connectionRandom;
    }

    @Override
    public void execute(String command) {
        if (connectionRandom.nextInt(RANDOM_BOUND) == 0) {
            throw new ConnectionException();
        }
    }

    @Override
    public void close() throws Exception {
    }
}
