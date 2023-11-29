package edu.hw7.task3and3point5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDatabase implements PersonDatabase {
    private final Map<Integer, Person> idTable = new HashMap<>();
    private final Map<String, Person> nameTable = new HashMap<>();
    private final Map<String, Person> addressTable = new HashMap<>();
    private final Map<String, Person> phoneTable = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        readWriteLock.writeLock().lock();
        try {
            idTable.put(person.id(), person);
            nameTable.put(person.name(), person);
            addressTable.put(person.address(), person);
            phoneTable.put(person.phoneNumber(), person);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        readWriteLock.writeLock().lock();
        try {
            Person person = idTable.remove(id);
            if (person != null) {
                nameTable.remove(person.name());
                addressTable.remove(person.address());
                phoneTable.remove(person.phoneNumber());
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public Person findByName(String name) {
        readWriteLock.readLock().lock();
        try {
            return nameTable.get(name);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public Person findByAddress(String address) {
        readWriteLock.readLock().lock();
        try {
            return addressTable.get(address);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public Person findByPhone(String phone) {
        readWriteLock.readLock().lock();
        try {
            return phoneTable.get(phone);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Map<Integer, Person> getIdTable() {
        return idTable;
    }

    public Map<String, Person> getNameTable() {
        return nameTable;
    }

    public Map<String, Person> getAddressTable() {
        return addressTable;
    }

    public Map<String, Person> getPhoneTable() {
        return phoneTable;
    }
}
