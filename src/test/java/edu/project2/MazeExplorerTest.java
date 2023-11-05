package edu.project2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static org.assertj.core.api.Assertions.assertThat;

class MazeExplorerTest {
    @Test
    void shouldReturnCorrectPathWithLeeWaveAlgorithm() {
        String initialConditions = """
            4
            5
            2
            1
            1
            2
            5
            """;
        Scanner scanner = new Scanner(new ByteArrayInputStream(initialConditions.getBytes()));
        MazeExplorer mazeExplorer = new MazeExplorer(scanner, new Random(10));
        mazeExplorer.run();

        List<Cell> path = mazeExplorer.getPath();

        assertThat(path)
            .containsExactly(
                mazeExplorer.getMaze().getCell(0, 0),
                mazeExplorer.getMaze().getCell(1, 0),
                mazeExplorer.getMaze().getCell(1, 1),
                mazeExplorer.getMaze().getCell(1, 2),
                mazeExplorer.getMaze().getCell(2, 2),
                mazeExplorer.getMaze().getCell(3, 2),
                mazeExplorer.getMaze().getCell(3, 3),
                mazeExplorer.getMaze().getCell(3, 4),
                mazeExplorer.getMaze().getCell(2, 4),
                mazeExplorer.getMaze().getCell(1, 4)
            );
    }

    @Test
    void shouldReturnCorrectPathWithBinaryTreeAlgorithm() {
        String initialConditions = """
            4
            5
            1
            1
            1
            4
            1
            """;
        Scanner scanner = new Scanner(new ByteArrayInputStream(initialConditions.getBytes()));
        MazeExplorer mazeExplorer = new MazeExplorer(scanner, new Random(10));
        mazeExplorer.run();

        List<Cell> path = mazeExplorer.getPath();

        assertThat(path)
            .containsExactly(
                mazeExplorer.getMaze().getCell(0, 0),
                mazeExplorer.getMaze().getCell(0, 1),
                mazeExplorer.getMaze().getCell(0, 2),
                mazeExplorer.getMaze().getCell(1, 2),
                mazeExplorer.getMaze().getCell(2, 2),
                mazeExplorer.getMaze().getCell(2, 1),
                mazeExplorer.getMaze().getCell(3, 1),
                mazeExplorer.getMaze().getCell(3, 0)
            );
    }

    @ParameterizedTest
    @ValueSource(strings = {"""
        2
        1
        """, """
        1
        2
        """})
    void shouldThrowExceptionBecauseOfIncorrectMazeHeightOrWidth(String invalidInitialConditions) {
        Scanner scanner = new Scanner(new ByteArrayInputStream(invalidInitialConditions.getBytes()));

        IllegalArgumentException invalidMazeSize =
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                MazeExplorer mazeExplorer = new MazeExplorer(scanner, new Random(10));
            });

        Assertions.assertEquals("Неверно задан размер лабиринта!", invalidMazeSize.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"""
        4
        5
        2
        1
        1
        2
        10
        """, """
        4
        5
        2
        1
        -1
        2
        3
        """})
    void shouldThrowExceptionBecauseOfIncorrectGoalOrRootCellsCoordinates(String invalidCellsCoordinates) {
        Scanner scanner = new Scanner(new ByteArrayInputStream(invalidCellsCoordinates.getBytes()));
        MazeExplorer mazeExplorer = new MazeExplorer(scanner, new Random(10));

        IllegalArgumentException invalidCell =
            Assertions.assertThrows(IllegalArgumentException.class, mazeExplorer::run);

        Assertions.assertEquals("Элемента с такими координатами не существует!", invalidCell.getMessage());
    }

    @Test
    void shouldThrowExceptionBecauseOfIncrorrectGenerateMethod() {
        String initialConditions = """
            4
            5
            10
            """;
        Scanner scanner = new Scanner(new ByteArrayInputStream(initialConditions.getBytes()));

        IllegalArgumentException invalidGenerateMethod =
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                MazeExplorer mazeExplorer = new MazeExplorer(scanner, new Random(10));
            });

        Assertions.assertEquals("Неверно задан метод генерации!", invalidGenerateMethod.getMessage());
    }
}
