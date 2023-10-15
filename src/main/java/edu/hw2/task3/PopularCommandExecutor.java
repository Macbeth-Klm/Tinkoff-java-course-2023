package edu.hw2.task3;

public class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    private final double probabilityStep = 0.1;

    PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages(double connectionProbability) throws Exception {
        tryExecute("apt update && apt upgrade -y", connectionProbability);
    }

    private void tryExecute(String command, double connectionProbability) throws Exception {
        Connection currentConnection = manager.getConnection();
        double currentProbability = connectionProbability;
        for (int i = 0; i < maxAttempts; i++) {
            try (currentConnection) {
                currentConnection.execute(command, currentProbability);
                break;
            } catch (ConnectionException e) {
                if (i == maxAttempts - 1) {
                    throw new ConnectionException("Не удалось выполнить команду!", e);
                }
            } finally {
                currentProbability += probabilityStep;
            }
        }
    }

}
