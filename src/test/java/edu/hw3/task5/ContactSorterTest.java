package edu.hw3.task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ContactSorterTest {

    static List<String> names = List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes");

    @Test
    void shouldParseContactsWithAscMethod() {
        Contact[] sortedContacts = ContactSorter.parseContacts(names, "ASC");

        assertThat(sortedContacts)
            .hasSize(4);
        assertThat(sortedContacts[0].getFullName())
            .isEqualTo("Thomas Aquinas");
        assertThat(sortedContacts[1].getFullName())
            .isEqualTo("Rene Descartes");
        assertThat(sortedContacts[2].getFullName())
            .isEqualTo("David Hume");
        assertThat(sortedContacts[3].getFullName())
            .isEqualTo("John Locke");
    }

    @Test
    void shouldParseContactsWithDescMethod() {
        Contact[] sortedContacts = ContactSorter.parseContacts(names, "DESC");

        assertThat(sortedContacts)
            .hasSize(4);
        assertThat(sortedContacts[0].getFullName())
            .isEqualTo("John Locke");
        assertThat(sortedContacts[1].getFullName())
            .isEqualTo("David Hume");
        assertThat(sortedContacts[2].getFullName())
            .isEqualTo("Rene Descartes");
        assertThat(sortedContacts[3].getFullName())
            .isEqualTo("Thomas Aquinas");
    }

    @Test
    void shouldParseContactsWithoutSecondNameWithAscMethod() {
        List<String> names = List.of(
            "John Locke",
            "Thomas Aquinas",
            "David Hume",
            "Rene Descartes",
            "Antonio"
        );

        Contact[] sortedContacts = ContactSorter.parseContacts(names, "ASC");

        assertThat(sortedContacts)
            .hasSize(5);
        assertThat(sortedContacts[0].getFullName())
            .isEqualTo("Antonio");
        assertThat(sortedContacts[1].getFullName())
            .isEqualTo("Thomas Aquinas");
        assertThat(sortedContacts[2].getFullName())
            .isEqualTo("Rene Descartes");
        assertThat(sortedContacts[3].getFullName())
            .isEqualTo("David Hume");
        assertThat(sortedContacts[4].getFullName())
            .isEqualTo("John Locke");
    }

    @Test
    void shouldParseContactsWithoutSecondNameWithDescMethod() {
        List<String> names = List.of(
            "John Locke",
            "Thomas Aquinas",
            "David Hume",
            "Rene Descartes",
            "Antonio"
        );

        Contact[] sortedContacts = ContactSorter.parseContacts(names, "DESC");

        assertThat(sortedContacts)
            .hasSize(5);
        assertThat(sortedContacts[0].getFullName())
            .isEqualTo("John Locke");
        assertThat(sortedContacts[1].getFullName())
            .isEqualTo("David Hume");
        assertThat(sortedContacts[2].getFullName())
            .isEqualTo("Rene Descartes");
        assertThat(sortedContacts[3].getFullName())
            .isEqualTo("Thomas Aquinas");
        assertThat(sortedContacts[4].getFullName())
            .isEqualTo("Antonio");

    }

    @Test
    void shouldThrowExceptionBecauseOfIncorrectSortingMethod() {
        IllegalArgumentException methodException =
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                Contact[] sortedContacts = ContactSorter.parseContacts(names, "KEK");
            });

        Assertions.assertEquals("Неправильно задан метод сортировки!", methodException.getMessage());
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void shouldReturnEmptyArrayBecauseOfEmptyArgumentList(List<String> inputNames) {
        Contact[] sortedContacts = ContactSorter.parseContacts(inputNames, "ASC");

        assertThat(sortedContacts)
            .isEmpty();
    }
}
