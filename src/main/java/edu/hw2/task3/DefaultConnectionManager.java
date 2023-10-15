package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {
    private static final double FAULTY_PROBABILITY = 0.3;

    private final double managerProbability;

    public DefaultConnectionManager(double managerProbability) {

        this.managerProbability = managerProbability;
    }

    @Override
    public Connection getConnection() {

        if (managerProbability <= FAULTY_PROBABILITY) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
