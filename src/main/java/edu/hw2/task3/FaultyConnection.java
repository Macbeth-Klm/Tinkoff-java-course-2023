package edu.hw2.task3;

public class FaultyConnection implements Connection {
    private static final double FAULTY_PROBABILITY = 0.5;

    @Override
    public void execute(String command, double probability) {
        if (probability <= FAULTY_PROBABILITY) {
            throw new ConnectionException();
        }
    }

    @Override
    public void close() {
    }
}
