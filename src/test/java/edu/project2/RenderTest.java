package edu.project2;

import edu.project2.rendering.PrettyPrint;
import edu.project2.rendering.Renderer;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Random;
import java.util.Scanner;
import static org.assertj.core.api.Assertions.assertThat;

public class RenderTest {

    @Test
    void shouldReturnCorrectEllerAlgorithmMaze() {
        String initialConditions = """
            4
            5
            2
            """;
        Scanner scanner = new Scanner(new ByteArrayInputStream(initialConditions.getBytes()));
        MazeExplorer mazeExplorer = new MazeExplorer(scanner, new Random(10));
        Renderer renderer = new PrettyPrint();

        String resultRendering = renderer.render(mazeExplorer.getMaze());

        assertThat(resultRendering)
            .isEqualTo("""

                ▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃
                █   █▃▃▃▃▃          ▃▃▃▃█
                █▃▃▃▃▃▃▃▃▃    █    █    █
                █   █         █▃▃▃▃█    █
                █▃▃▃▃▃▃▃▃█▃▃▃▃▃▃▃▃▃▃▃▃▃▃█
                """
            );
    }

    @Test
    void shouldReturnCorrectBinaryTreeAlgorithmMaze() {
        String initialConditions = """
            4
            5
            1
            """;
        Scanner scanner = new Scanner(new ByteArrayInputStream(initialConditions.getBytes()));
        MazeExplorer mazeExplorer = new MazeExplorer(scanner, new Random(10));
        Renderer renderer = new PrettyPrint();

        String resultRendering = renderer.render(mazeExplorer.getMaze());

        assertThat(resultRendering)
            .isEqualTo("""

                ▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃
                █▃▃▃▃                   █
                █    ▃▃▃▃█    █    █    █
                █▃▃▃█         █▃▃▃▃█    █
                █▃▃▃▃▃▃▃▃█▃▃▃▃█▃▃▃▃▃▃▃▃▃█
                """
            );
    }

}
