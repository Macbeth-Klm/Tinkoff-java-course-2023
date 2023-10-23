package edu.hw3.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class BracketClusteringTest {

    @Test
    void clusterizeTestFirst() {
        // given
        String input = "()()()";

        // when
        List<String> result = BracketClustering.clusterize(input);

        // then
        assertThat(result)
            .hasSize(3)
            .containsExactly("()", "()", "()");
    }

    @Test
    void clusterizeTestSecond() {
        // given
        String input = "((()))";

        // when
        List<String> result = BracketClustering.clusterize(input);

        // then
        assertThat(result)
            .hasSize(1)
            .containsExactly("((()))");
    }

    @Test
    void clusterizeTestThird() {
        // given
        String input = "((()))(())()()(()())";

        // when
        List<String> result = BracketClustering.clusterize(input);

        // then
        assertThat(result)
            .hasSize(5)
            .containsExactly("((()))", "(())", "()", "()", "(()())");
    }

    @Test
    void clusterizeTestFourth() {
        // given
        String input = "((())())(()(()()))";

        // when
        List<String> result = BracketClustering.clusterize(input);

        // then
        assertThat(result)
            .hasSize(2)
            .containsExactly("((())())", "(()(()()))");
    }

    @Test
    void emptyString() {
        // given
        String input = null;

        // when
        NullPointerException resultException =
            Assertions.assertThrows(NullPointerException.class, () -> {
                List<String> result = BracketClustering.clusterize(input);
            });

        // then
        Assertions.assertEquals("Строка пустая!", resultException.getMessage());
    }

    @Test
    void incorrectString() {
        // given
        String input = "(abc)";

        // when
        IllegalArgumentException resultException =
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                List<String> result = BracketClustering.clusterize(input);
            });

        // then
        Assertions.assertEquals("Строка некорректного формата!", resultException.getMessage());
    }

    @Test
    void unbalancedCluster() {
        // given
        String input = "(()))";

        // when
        IllegalArgumentException resultException =
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                List<String> result = BracketClustering.clusterize(input);
            });

        // then
        Assertions.assertEquals("Кластер несбалансированный!", resultException.getMessage());
    }
}
