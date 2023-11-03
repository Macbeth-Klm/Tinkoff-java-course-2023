package edu.hw3.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class BracketClusteringTest {

    @Test
    void shouldClusterizeBrackets() {
        String input = "((()))(())()()(()())";

        List<String> result = BracketClustering.clusterize(input);

        assertThat(result)
            .hasSize(5)
            .containsExactly("((()))", "(())", "()", "()", "(()())");
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = "")
    void shouldThrowExceptionBecauseOfEmptyString(String inputString) {
        NullPointerException resultException =
            Assertions.assertThrows(NullPointerException.class, () -> {
                List<String> result = BracketClustering.clusterize(inputString);
            });

        Assertions.assertEquals("Строка пустая!", resultException.getMessage());
    }

    @Test
    void shouldThrowExceptionBecauseOfIncorrectStringFormat() {
        String input = "(abc)";

        IllegalArgumentException resultException =
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                List<String> result = BracketClustering.clusterize(input);
            });

        Assertions.assertEquals("Строка некорректного формата!", resultException.getMessage());
    }

    @Test
    void shouldThrowExceptionBecauseOfUnbalancedCluster() {
        String input = "(()))";

        IllegalArgumentException resultException =
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                List<String> result = BracketClustering.clusterize(input);
            });

        Assertions.assertEquals("Кластер несбалансированный!", resultException.getMessage());
    }
}
