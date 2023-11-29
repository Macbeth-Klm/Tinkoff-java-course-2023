package edu.hw7.task3and3point5;

import java.util.HashMap;
import java.util.Map;

public class SynchronizedDatabase implements PersonDatabase {
    private final Map<Integer, Person> idTable = new HashMap<>();
    private final Map<String, Person> nameTable = new HashMap<>();
    private final Map<String, Person> addressTable = new HashMap<>();
    private final Map<String, Person> phoneTable = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        idTable.put(person.id(), person);
        nameTable.put(person.name(), person);
        addressTable.put(person.address(), person);
        phoneTable.put(person.phoneNumber(), person);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = idTable.remove(id);
        if (person != null) {
            nameTable.remove(person.name());
            addressTable.remove(person.address());
            phoneTable.remove(person.phoneNumber());
        }
    }

    @Override
    public synchronized Person findByName(String name) {
        return nameTable.get(name);
    }

    @Override
    public synchronized Person findByAddress(String address) {
        return addressTable.get(address);
    }

    @Override
    public synchronized Person findByPhone(String phone) {
        return phoneTable.get(phone);
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
