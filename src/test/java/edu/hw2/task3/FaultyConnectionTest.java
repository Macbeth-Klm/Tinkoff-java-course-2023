package edu.hw2.task3;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class FaultyConnectionTest {
    /*
        new Random(-1).nextInt(2) = 0;
        new Random(1).nextInt(2) = 1;
    */
    @Test
    void commandExecution() {
        try (Connection faultyConnection = new FaultyConnection(new Random(1))) {
            assertDoesNotThrow(() -> faultyConnection.execute("execute"));
        } catch (Exception ignored) {
        }
    }

    @Test
    void throwingConnectionException() {
        try (Connection faultyConnection = new FaultyConnection(new Random(-1))) {
            assertThrows(ConnectionException.class, () -> faultyConnection.execute("execute"));
        } catch (Exception ignored) {
        }
    }
}
