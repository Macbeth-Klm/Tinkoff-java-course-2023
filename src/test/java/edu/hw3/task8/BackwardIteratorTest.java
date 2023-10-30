package edu.hw3.task8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.assertThat;

class BackwardIteratorTest {

    @ParameterizedTest
    @EmptySource
    @NullSource
    void shouldThrowExceptionBecauseOfCollectionIsEmpty(List<Integer> list) {
        IllegalArgumentException emptyCollectionException =
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                BackwardIterator<Integer, List<Integer>> backwardIterator = new BackwardIterator<>(list);
            });

        Assertions.assertEquals("Коллекция пустая!", emptyCollectionException.getMessage());
    }

    @Test
    void shouldThrowExceptionBecauseOfElementDoesNotExist() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        BackwardIterator<String, List<String>> backwardIterator = new BackwardIterator<>(list);
        backwardIterator.next();

        NoSuchElementException elementDoesNotExistException =
            Assertions.assertThrows(NoSuchElementException.class, backwardIterator::next);

        Assertions.assertEquals("Достигнуто начало коллекции!", elementDoesNotExistException.getMessage());
    }

    @Test
    void shouldReturnCorrectElements() {
        BackwardIterator<Integer, List<Integer>> backwardIterator = new BackwardIterator<>(List.of(1, 2, 3));
        List<Integer> returnedElements = new ArrayList<>();

        while (backwardIterator.hasNext()) {
            returnedElements.add(backwardIterator.next());
        }

        assertThat(returnedElements)
            .hasSize(3)
            .containsExactly(3, 2, 1);
    }

}
