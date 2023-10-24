package edu.hw3.task5;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.jetbrains.annotations.NotNull;

public final class ContactSorter {
    private ContactSorter() {
    }

    public static Contact[] parseContacts(List<String> names, @NotNull String sortingMethod) {
        if (names == null || names.isEmpty()) {
            return new Contact[0];
        }
        Set<Contact> sortedContacts = switch (sortingMethod) {
            case "ASC" -> new TreeSet<>((o1, o2) -> {
                if (o1.getSecondName().isEmpty()) {
                    if (o2.getSecondName().isEmpty()) {
                        return CharSequence.compare(o1.getFirstName(), o2.getFirstName());
                    } else {
                        return CharSequence.compare(o1.getFirstName(), o2.getSecondName());
                    }
                } else {
                    if (o2.getSecondName().isEmpty()) {
                        return CharSequence.compare(o1.getSecondName(), o2.getFirstName());
                    } else {
                        return CharSequence.compare(o1.getSecondName(), o2.getSecondName());
                    }
                }
            });
            case "DESC" -> new TreeSet<>((o1, o2) -> {
                if (o1.getSecondName().isEmpty()) {
                    if (o2.getSecondName().isEmpty()) {
                        return (-1) * CharSequence.compare(o1.getFirstName(), o2.getFirstName());
                    } else {
                        return (-1) * CharSequence.compare(o1.getFirstName(), o2.getSecondName());
                    }
                } else {
                    if (o2.getSecondName().isEmpty()) {
                        return (-1) * CharSequence.compare(o1.getSecondName(), o2.getFirstName());
                    } else {
                        return (-1) * CharSequence.compare(o1.getSecondName(), o2.getSecondName());
                    }
                }
            });
            default -> throw new IllegalArgumentException("Неправильно задан метод сортировки!");
        };
        for (String name : names) {
            Contact contact = new Contact(name);
            sortedContacts.add(contact);
        }
        return sortedContacts.toArray(new Contact[0]);
    }
}
