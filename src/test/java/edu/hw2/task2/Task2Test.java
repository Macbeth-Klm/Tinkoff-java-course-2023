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
    void invalidRectangleWidthSetterArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Rectangle rect = new Rectangle();
            rect = rect.setWidth(-10);
        });
    }

    @Test
    void invalidRectangleHeightSetterArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Rectangle rect = new Rectangle();
            rect = rect.setHeight(-10);
        });
    }

    @Test
    void squareAreaByHeight() {
        Rectangle square = new Square();
        square = square.setHeight(15);
        assertThat(square.area()).isEqualTo(225.0);
    }

    @Test
    void squareAreaByWidth() {
        Rectangle square = new Square();
        square = square.setWidth(20);
        assertThat(square.area()).isEqualTo(400.0);
    }

    @Test
    void invalidSquareWidthSetterArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Rectangle square = new Square();
            square = square.setWidth(-10);
        });
    }

    @Test
    void invalidSquareHeightSetterArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Rectangle square = new Square();
            square = square.setHeight(-10);
        });
    }

}
