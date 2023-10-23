package edu.hw3.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class WordCounterTest {
    @Test
    void freqDictTestFirst() {
        // given
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("bb");
        list.add("a");
        list.add("bb");

        // when
        Map<String, Integer> result = WordCounter.freqDict(list);

        // then
        assertThat(result)
            .hasSize(2)
            .containsKey("a")
            .containsKey("bb");

        for (String key : result.keySet()) {
            Assertions.assertEquals(2, result.get(key));
        }
    }

    @Test
    void freqDictTestSecond() {
        // given
        List<String> list = new ArrayList<>();
        list.add("код");
        list.add("код");
        list.add("код");
        list.add("bug");

        // when
        Map<String, Integer> result = WordCounter.freqDict(list);

        // then
        assertThat(result)
            .hasSize(2)
            .containsKey("код")
            .containsKey("bug");

        assertThat(result.get("код"))
            .isEqualTo(3);

        assertThat(result.get("bug"))
            .isEqualTo(1);
    }

    @Test
    void freqDictTestThird() {
        // given
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);

        // when
        Map<Integer, Integer> result = WordCounter.freqDict(list);

        // then
        assertThat(result)
            .hasSize(2)
            .containsKey(1)
            .containsKey(2);

        assertThat(result.get(1))
            .isEqualTo(2);

        assertThat(result.get(2))
            .isEqualTo(2);
    }

    @Test
    void emptyList() {
        // given
        List<Integer> list = null;

        // when
        NullPointerException resultException =
            Assertions.assertThrows(NullPointerException.class, () -> {
                Map<Integer, Integer> result = WordCounter.freqDict(list);
            });

        // then
        Assertions.assertEquals("Список пустой!", resultException.getMessage());
    }

    @Test
    void emptyListElement() {
        // given
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("код");
        list.add("код");
        list.add("bug");

        // when
        Map<String, Integer> result = WordCounter.freqDict(list);

        // then
        assertThat(result)
            .hasSize(3)
            .containsKey("")
            .containsKey("код")
            .containsKey("bug");

        assertThat(result.get(""))
            .isEqualTo(1);

        assertThat(result.get("код"))
            .isEqualTo(2);

        assertThat(result.get("bug"))
            .isEqualTo(1);
    }
}
