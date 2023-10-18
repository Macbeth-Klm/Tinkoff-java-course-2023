package edu.hw2.task3;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class DefaultConnectionManagerTest {
    /*
        new Random(-1).nextInt(2) = 0;
        new Random(1).nextInt(2) = 1;
    */
    @Test
    void getStableConnection() {
        ConnectionManager defaultManager = new DefaultConnectionManager(new Random(1), new Random());
        assertEquals(StableConnection.class, defaultManager.getConnection().getClass());
    }

    @Test
    void getFaultyConnection() {
        ConnectionManager defaultManager = new DefaultConnectionManager(new Random(-1), new Random());
        assertEquals(FaultyConnection.class, defaultManager.getConnection().getClass());
    }
}
