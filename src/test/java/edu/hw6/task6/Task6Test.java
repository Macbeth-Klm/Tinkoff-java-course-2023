package edu.hw6.task6;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {
    @Test
    void iDontKnowHowToTestIt() {
        // Я не знал, как это тестировал, поэтому решил возвращать из метода номера занятых портов
        List<Integer> busyPorts = Task6.scanPorts();

        assertThat(busyPorts)
            .containsExactlyInAnyOrder(
                80,
                123,
                135,
                137,
                138,
                139,
                445,
                1900,
                5040,
                5050,
                5353,
                5355,
                7680,
                27700,
                27720,
                27721,
                36645,
                42050
            );
    }

}
