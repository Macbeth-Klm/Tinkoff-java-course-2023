package edu.hw2.task3;

public class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) {
        for (int i = 0; i < maxAttempts; i++) {
            Connection currentConnection = manager.getConnection();
            try (currentConnection) {
                currentConnection.execute(command);
                return;
            } catch (Exception e) {
                if (i == maxAttempts - 1) {
                    throw new ConnectionException("Не удалось выполнить команду!", e);
                }
            }
        }
    }
}
