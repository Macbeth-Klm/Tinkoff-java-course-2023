package edu.hw3.task7;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

class NullComparatorTest {
    static TreeMap<String, String> tree = new TreeMap<>(new NullComparator<>());

    @BeforeAll
    public static void fillMap() {
        tree.put("a", "1");
        tree.put("b", "2");
        tree.put(null, "Hello");
        tree.put("Hello", null);
        tree.put("z", "3");
    }

    @Test
    void shouldBeTreeMapWithNullableKey() {
        assertThat(tree.firstKey())
            .isNull();
        assertThat(tree.toString())
            .isEqualTo("{null=Hello, Hello=null, a=1, b=2, z=3}");
    }

    @Test
    void shouldBeTreeMapWithNullableValue() {
        assertThat(tree)
            .containsValue(null);
    }
}
