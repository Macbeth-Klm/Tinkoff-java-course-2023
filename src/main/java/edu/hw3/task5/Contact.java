package edu.hw3.task5;

public class Contact {
    private final String firstName;
    private final String secondName;

    public Contact(String fullName) {
        String[] names = fullName.split("\s");
        switch (names.length) {
            case 1:
                firstName = names[0];
                secondName = "";
                break;
            case 2:
                firstName = names[0];
                secondName = names[1];
                break;
            default:
                throw new IllegalArgumentException("Неверная строка!");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }
}
