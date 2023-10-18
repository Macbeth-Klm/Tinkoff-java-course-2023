package edu.hw2.task3;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class FaultyConnectionManagerTest {
    @Test
    void getFaultyConnection() {
        ConnectionManager faultyManager = new FaultyConnectionManager(new Random());
        assertEquals(FaultyConnection.class, faultyManager.getConnection().getClass());
    }
}
