package edu.project4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FractalInfernoTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void comparingTheRunningTimeOfASingleThreadedAndMultiThreadedProgram() {
        List<Long> executionTime = new ArrayList<>();
        FractalInferno fractalInferno = new FractalInferno();
        var startTime = System.nanoTime();
        var image = fractalInferno.singleThreadRender(
            1080,
            1920,
            50,
            25,
            250_000,
            1,
            NonLinearType.SPHERE,
            NonLinearType.HEART
        );
        var endTime = System.nanoTime();
        executionTime.add((endTime - startTime) / 1_000_000);

        startTime = System.nanoTime();
        image = fractalInferno.singleThreadRender(
            1080,
            1920,
            50,
            25,
            50_000,
            1,
            NonLinearType.SPHERE,
            NonLinearType.HEART
        );
        endTime = System.nanoTime();
        executionTime.add((endTime - startTime) / 1_000_000);
        LOGGER.info(
            "Время работы однопоточной программы равно {} мс, многопоточной - {} мс",
            executionTime.get(0),
            executionTime.get(1)
        );
    }

    @Test
    void shouldCreateCorrectFractalImageWithSingleThreadRender() {
        FractalInferno fractalInferno = new FractalInferno();

        var image = fractalInferno.singleThreadRender(
            1089,
            1920,
            50,
            25,
            50_000,
            1,
            NonLinearType.SPHERE,
            NonLinearType.HEART
        );

        assertThat(image)
            .isNotNull()
            .hasNoNullFieldsOrProperties();
    }

    @Test
    void shouldCreateCorrectFractalImageWithMultiThreadRender() {
        FractalInferno fractalInferno = new FractalInferno();

        var image = fractalInferno.multiThreadRender(
            1089,
            1920,
            50,
            25,
            50_000,
            1,
            NonLinearType.SPHERE,
            NonLinearType.HEART
        );

        assertThat(image)
            .isNotNull()
            .hasNoNullFieldsOrProperties();
    }

    @Test
    void shouldCreateCorrectJpgFile() throws IOException {
        FractalInferno fractalInferno = new FractalInferno();

        var image = fractalInferno.multiThreadRender(
            1089,
            1920,
            50,
            25,
            50_000,
            1,
            NonLinearType.SPHERE,
            NonLinearType.HEART
        );
        image.logarithmicGammaCorrection(0.2);
        image.createFile("src/test/java/edu/project4/test");
        Path imagePath = Path.of("src/test/java/edu/project4/test.jpg");

        assertThat(imagePath)
            .exists()
            .isNotEmptyFile();
        Files.delete(imagePath);
    }

    @Test
    void shouldThrowExceptionBecauseOfInvalidArguments() {
        FractalInferno fractalInferno = new FractalInferno();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var image = fractalInferno.singleThreadRender(
                -1,
                1920,
                50,
                25,
                250_000,
                1,
                NonLinearType.SPHERE,
                NonLinearType.HEART
            );
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var image = fractalInferno.singleThreadRender(
                1080,
                0,
                50,
                25,
                250_000,
                1,
                NonLinearType.SPHERE,
                NonLinearType.HEART
            );
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var image = fractalInferno.singleThreadRender(
                1080,
                1920,
                -1,
                25,
                250_000,
                1,
                NonLinearType.SPHERE,
                NonLinearType.HEART
            );
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var image = fractalInferno.singleThreadRender(
                1080,
                1920,
                50,
                -5,
                250_000,
                1,
                NonLinearType.SPHERE,
                NonLinearType.HEART
            );
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var image = fractalInferno.singleThreadRender(
                1080,
                1920,
                50,
                25,
                0,
                1,
                NonLinearType.SPHERE,
                NonLinearType.HEART
            );
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var image = fractalInferno.singleThreadRender(
                1080,
                1920,
                50,
                25,
                250_000,
                0,
                NonLinearType.SPHERE,
                NonLinearType.HEART
            );
        });
    }
}
