package edu.hw7.task3and3point5;

import java.util.HashMap;
import java.util.List;
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

        idTable.put(person.id(), person);
        nameTable.put(person.name(), person);
        addressTable.put(person.address(), person);
        phoneTable.put(person.phoneNumber(), person);

        readWriteLock.writeLock().unlock();
    }

    @Override
    public void delete(int id) {
        readWriteLock.writeLock().lock();

        Person person = idTable.remove(id);
        if (person != null) {
            nameTable.remove(person.name());
            addressTable.remove(person.address());
            phoneTable.remove(person.phoneNumber());
        }

        readWriteLock.writeLock().unlock();
    }

    @Override
    public List<Person> findByName(String name) {
        readWriteLock.readLock().lock();
        try {
            return nameTable.entrySet().stream()
                .filter(e -> e.getKey().equals(name))
                .map(Map.Entry::getValue)
                .toList();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        readWriteLock.readLock().lock();
        try {
            return addressTable.entrySet().stream()
                .filter(e -> e.getKey().equals(address))
                .map(Map.Entry::getValue)
                .toList();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        readWriteLock.readLock().lock();
        try {
            return phoneTable.entrySet().stream()
                .filter(e -> e.getKey().equals(phone))
                .map(Map.Entry::getValue)
                .toList();
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
