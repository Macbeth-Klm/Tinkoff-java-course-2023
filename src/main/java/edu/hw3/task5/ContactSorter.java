package edu.hw3.task5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class ContactSorter {
    private ContactSorter() {
    }

    public static Contact[] parseContacts(List<String> names, @NotNull String sortingMethod) {
        if (names == null || names.isEmpty()) {
            return new Contact[0];
        }
        var isAsc = sortingMethod.equals("ASC");
        if (isAsc || sortingMethod.equals("DESC")) {
            Contact[] sortedContacts = new Contact[names.size()];
            for (int i = 0; i < sortedContacts.length; i++) {
                sortedContacts[i] = new Contact(names.get(i));
            }
            if (isAsc) {
                Arrays.sort(sortedContacts, Comparator
                    .comparing(Contact::getSecondName).thenComparing(Contact::getFirstName));
            } else {
                Arrays.sort(sortedContacts, Comparator
                    .comparing(Contact::getSecondName).thenComparing(Contact::getFirstName).reversed());
            }
            return sortedContacts;
        } else {
            throw new IllegalArgumentException("Неправильно задан метод сортировки!");
        }
    }
}
