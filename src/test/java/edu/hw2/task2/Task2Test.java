package edu.hw2.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);

        assertThat(rect.area()).isEqualTo(200.0);
    }

    @Test
    void invalidWidthArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Rectangle rect = new Square();
            rect = rect.setWidth(-10);
        });
    }

    @Test
    void invalidHeightArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Rectangle rect = new Rectangle();
            rect = rect.setHeight(-10);
        });
    }
}
