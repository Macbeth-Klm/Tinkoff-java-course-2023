package edu.hw3.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class WordCounterTest {
    @Test
    void shouldCountFreqDictString() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("bb");
        list.add("a");
        list.add("bb");
        list.add("c");

        Map<String, Integer> result = WordCounter.freqDict(list);

        assertThat(result).containsExactlyInAnyOrderEntriesOf(
            Map.of(
                "a", 2,
                "bb", 2,
                "c", 1
            ));
    }

    @Test
    void shouldCountFreqDictInteger() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);

        Map<Integer, Integer> result = WordCounter.freqDict(list);

        assertThat(result).containsExactlyInAnyOrderEntriesOf(
            Map.of(
                1, 2,
                2, 2
            ));
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void shouldThrowExceptionBecauseOfEmptyList(List<Integer> list) {
        IllegalArgumentException resultException =
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                Map<Integer, Integer> result = WordCounter.freqDict(list);
            });

        Assertions.assertEquals("Список пустой!", resultException.getMessage());
    }

    @Test
    void shouldCountFreqDictWithEmptyElementInTheList() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("код");
        list.add("код");
        list.add("bug");

        Map<String, Integer> result = WordCounter.freqDict(list);

        assertThat(result).containsExactlyInAnyOrderEntriesOf(
            Map.of(
                "", 1,
                "код", 2,
                "bug", 1
            ));
    }
}
