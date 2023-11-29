package edu.hw7.task3and3point5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class ReadWriteLockDatabaseTest {
    private static final Logger LOGGER = LogManager.getLogger();
    private ReadWriteLockDatabase database;
    private Person result;

    @BeforeEach
    void initDatabase() {
        database = new ReadWriteLockDatabase();
    }

    @Test
    public void shouldFindPersonByNameWhileAnotherThreadAddingPersons() {
        Person person = new Person(1, "Popov Ilia", "St. Petersburg", "someNumber");

        Thread addThread = new Thread(() -> database.add(person));
        Thread findThread = new Thread(() -> {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            result = database.findByName("Popov Ilia");
        });
        addThread.start();
        findThread.start();
        try {
            addThread.join();
            findThread.join();
        } catch (InterruptedException e) {
            LOGGER.error("Error while joing threads", e);
            throw new RuntimeException(e);
        }

        assertThat(result)
            .isEqualTo(person);
    }

    @Test
    public void shouldFindPersonByAddressWhileAnotherThreadDeletingThatPerson() {
        Person person = new Person(1, "Popov Ilia", "St. Petersburg", "someNumber");
        database.add(person);

        Thread deleteThread = new Thread(() -> {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            database.delete(1);
        });
        Thread findThread = new Thread(() -> result = database.findByAddress("St. Petersburg"));
        deleteThread.start();
        findThread.start();
        try {
            deleteThread.join();
            findThread.join();
        } catch (InterruptedException e) {
            LOGGER.error("Error while joing threads", e);
            throw new RuntimeException(e);
        }

        assertThat(result)
            .isEqualTo(person);
        assertThat(database.getIdTable().isEmpty()
            && database.getNameTable().isEmpty()
            && database.getAddressTable().isEmpty()
            && database.getPhoneTable().isEmpty())
            .isTrue();
    }

    @Test
    public void shouldNotFindPersonByAddressAfterAnotherThreadDeletingThatPerson() {
        Person person = new Person(1, "Popov Ilia", "St. Petersburg", "someNumber");
        database.add(person);

        Thread deleteThread = new Thread(() -> database.delete(1));
        Thread findThread = new Thread(() -> {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            result = database.findByAddress("St. Petersburg");
        });
        deleteThread.start();
        findThread.start();
        try {
            deleteThread.join();
            findThread.join();
        } catch (InterruptedException e) {
            LOGGER.error("Error while joing threads", e);
            throw new RuntimeException(e);
        }

        assertNull(result);
    }

    @Test
    void shouldFindPersonByPhoneNumberWhileAnotherThreadAddingThatPerson() {
        Person person = new Person(1, "Popov Ilia", "St. Petersburg", "someNumber");

        Thread addThread = new Thread(() -> database.add(person));
        Thread findThread = new Thread(() -> {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            result = database.findByPhone("someNumber");
        });
        addThread.start();
        findThread.start();
        try {
            addThread.join();
            findThread.join();
        } catch (InterruptedException e) {
            LOGGER.error("Error while joing threads", e);
            throw new RuntimeException(e);
        }

        assertThat(result)
            .isEqualTo(person);
    }
}
