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
            Arguments.of(new Rectangle(8, 2)),
            Arguments.of(new Square(4))
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
    void invalidRectangleConstructorArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Rectangle rect = new Rectangle(-10, 5);
        });
    }

    @Test
    void invalidRectangleWidthSetterArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Rectangle rect = new Rectangle(30, 20);
            rect = rect.setWidth(-10);
        });
    }

    @Test
    void invalidRectangleHeightSetterArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Rectangle rect = new Rectangle(50, 40);
            rect = rect.setHeight(-10);
        });
    }

    @Test
    void squareAreaFirst() {
        Rectangle square = new Square(20);
        square = square.setWidth(15);
        assertThat(square.area()).isEqualTo(300.0);
    }

    @Test
    void squareAreaSecond() {
        Rectangle square = new Square(30);
        assertThat(square.area()).isEqualTo(900.0);
    }
}
