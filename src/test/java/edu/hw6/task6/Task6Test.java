package edu.hw6.task6;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {
    @Test
    void shouldCorrectScanGivenPorts() {
        int httpPort = 80;

        Map<Integer, String> portsScan = Task6.scanGivenPorts(httpPort);

        assertThat(portsScan)
            .isNotEmpty()
            .containsExactlyInAnyOrderEntriesOf(Map.of(
                80, "TCP is busy"
            ));
    }

}
